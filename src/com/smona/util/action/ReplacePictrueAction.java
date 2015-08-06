package com.smona.util.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.smona.util.common.Logger;

public class ReplacePictrueAction implements IAction {

    private static final String subbfixSource = "/replace/bubble.png";
    private static final String subbfixTarget = "/temp/Launcher/Launcher.xml";
    private static final String targetNode = "bubble-pic";

    @Override
    public void action(String path) {
        byte[] b = new byte[1024 * 8];
        String source = path + subbfixSource;
        String target = path + "/temp/" + getTargetPath(path);
        try {
            FileInputStream br = new FileInputStream(new File(source));
            FileOutputStream bw = new FileOutputStream(new File(target));
            int index = -1;
            while ((index = br.read(b)) != -1) {
                bw.write(b);
            }
            bw.flush();
            println("ReplacePictrueAction success target=" + target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            printDetail(e.toString());
            println("ReplacePictrueAction failed: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            printDetail(e.toString());
            println("ReplacePictrueAction failed: " + e.toString());
        }
    }

    private String getTargetPath(String path) {
        String desPath = path + subbfixTarget;
        String result = null;
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new BufferedReader(new InputStreamReader(
                    new FileInputStream(desPath), "UTF-8")));
            List list = document.selectNodes("//" + targetNode);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                result = element.getText();
                println("ReplacePictrueAction result=" + result);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            printDetail(e.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            printDetail(e.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            printDetail(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            printDetail(e.toString());
        }
        return result;
    }

    private static void println(String msg) {
        Logger.printDetail(msg);
    }

    private static void printDetail(String msg) {
        Logger.printDetail(msg);
    }
}
