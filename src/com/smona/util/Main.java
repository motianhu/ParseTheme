package com.smona.util;

import com.smona.util.action.Action;
import com.smona.util.action.CompareAction;
import com.smona.util.action.IAction;
import com.smona.util.action.ReplacePictrueAction;
import com.smona.util.action.RuleAction;
import com.smona.util.common.Logger;

public class Main {
    public static void main(String[] args) {
        String encode = System.getProperty("file.encoding");
        println(encode);
        Logger.init();
        String path = System.getProperty("user.dir");
        println(path);
        action(path);
    }

    private static void action(String path) {
        Action action = new Action();
        /*IAction rule = new RuleAction();
        action.addAction(rule);
        IAction compare = new CompareAction();
        action.addAction(compare);*/
        
        IAction replace = new ReplacePictrueAction();
        action.addAction(replace);
        action.action(path);
    }

    private static void println(String msg) {
        Logger.printDetail(msg);
    }
}
