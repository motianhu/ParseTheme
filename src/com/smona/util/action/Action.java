package com.smona.util.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.smona.util.common.Logger;
import com.smona.util.parse.ZipFileAction;

public class Action implements IAction {

    private List<IAction> actions = null;

    public Action() {
        actions = new ArrayList<IAction>();
    }
    
    public void addAction(IAction action) {
        actions.add(action);
    }

    @Override
    public void action(String path) {
        listZipFiles(path);
    }

    private void listZipFiles(String path) {
        String fileDir = path + "/resource/";
        File rootFile = new File(fileDir);
        File[] files = rootFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }

            if (file.isFile() && file.getName().endsWith(".gnz")) {
                actionZipFile(file.getName(), path);
            }
        }
    }

    private void actionZipFile(String fileName, String path) {
        try {
            printReport("File: " + fileName);
            unzipFile(fileName, path);
            executeAction(path);
            zip(fileName, path);
            clearFiles(path);
            printReport("Status: success!");
        } catch (Exception e) {
            e.printStackTrace();
            printReport("Status: failed!");
            printDetail(e.toString());
        }
    }

    private void clearFiles(String path) throws Exception {
        ZipFileAction action = new ZipFileAction();
        printDetail("************************delAllFile start************************");
        boolean result = action.delAllFile(path + "/temp");
        printDetail(result + "************************delAllFile end************************");
    }

    private void executeAction(String path) throws Exception {
        for(IAction action: actions) {
            printDetail("************************executeAction start************************" + action);
            action.action(path);
            printDetail("************************executeAction end************************");
        }
    }

    private void zip(String fileName, String path) throws Exception {
        ZipFileAction action = new ZipFileAction();
        printDetail("------------------------zip start------------------------");
        action.zip(path + "/temp", path + "/target/" + fileName);
        printDetail("------------------------zip end------------------------");
        printDetail("");
        printDetail("");
    }

    private ZipFileAction unzipFile(String fileName, String path)
            throws Exception {
        ZipFileAction action = new ZipFileAction();
        printDetail("========================unZip start========================");
        action.unZip(path + "/resource/" + fileName, path + "/temp");
        printDetail("========================unZip end========================");
        printDetail("");
        printDetail("");
        return action;
    }

    private static void printDetail(String msg) {
        Logger.printDetail(msg);
    }

    private static void printReport(String msg) {
        Logger.printReport(msg);
    }

}
