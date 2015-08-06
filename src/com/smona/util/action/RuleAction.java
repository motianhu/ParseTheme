package com.smona.util.action;

import com.smona.util.common.Logger;
import com.smona.util.parse.XMLParse;

public class RuleAction implements IAction {

    @Override
    public void action(String path) {
        try {
            executeRules(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeRules(String path) throws Exception {
        String filePath = path + "/temp/Launcher/Launcher.xml";
        XMLParse parse = new XMLParse();
        printDetail("************************executeRules start************************");
        printDetail("path: " + filePath);
        parse.startParseXML(filePath);
        printDetail("************************executeRules end************************");
        printDetail("");
        printDetail("");
    }

    private static void printDetail(String msg) {
        Logger.printDetail(msg);
    }
}
