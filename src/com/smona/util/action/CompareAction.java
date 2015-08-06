package com.smona.util.action;

import java.io.File;

import com.smona.util.common.Logger;
import com.smona.util.compare.IFileCompare;
import com.smona.util.compare.XMLFileCompare;

public class CompareAction implements IAction {
    @Override
    public void action(String path) {
        String source = path + "/temp/Launcher/Launcher.xml";
        String target = path + "/launcher";
        File targetDir = new File(target);
        if(!targetDir.exists()) {
            return;
        }
        File[] files = targetDir.listFiles();
        for(File file: files) {
            compare(source, file.getAbsolutePath());
        }
    }

    private void compare(String source, String target) {
        printDetail(source);
        printDetail(target);
        IFileCompare compare = new XMLFileCompare();
        compare.compareFile(source, target);
    }

    
    public static void printDetail(String msg) {
        Logger.printDetail(msg);
    }

    public static void printReport(String msg) {
        Logger.printReport(msg);
    }
}
