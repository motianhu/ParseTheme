package com.smona.util.compare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.smona.util.common.Logger;
import com.smona.util.label.Label;
import com.smona.util.label.Suibian;

public class XMLFileCompare implements IFileCompare {

    private Label oldLabel;
    private Label newLabel;

    @Override
    public void compareFile(String oldFile, String newFile) {
        initLabel();
        readXML(oldFile, oldLabel);
        readXML(newFile, newLabel);
        compare();
        display();
    }

    private void initLabel() {
        oldLabel = new Suibian();
        newLabel = new Suibian();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void readXML(String oldFile, Label label) {
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new BufferedReader(new InputStreamReader(
                    new FileInputStream(oldFile), "UTF-8")));
            List<Element> list = document.selectNodes("suibian");
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                label.parse(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void compare() {
        boolean result = oldLabel.isEquals(newLabel);
        printDetail(String.valueOf(result));
    }

    private void display() {
        if (oldLabel != null) {
            oldLabel.display();
        }

        if (newLabel != null) {
            newLabel.display();
        }
    }

    public static void printDetail(String content) {
        Logger.printDetail(content);
    }

    public static void printReport(String content) {
        Logger.printReport(content);
    }
}
