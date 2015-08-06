package com.smona.util.parse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.smona.util.common.Logger;
import com.smona.util.parse.rule.AbstractRule;
import com.smona.util.parse.rule.RuleReplace;

public class Dom4jParse implements IParse {

    List<AbstractRule> rules;

    public Dom4jParse() {
        loadRules();
    }

    private static final String REPLACE = "#Intent;action=gionee.intent.action.SET_THEME;category=android.intent.category.LAUNCHER;component=com.gionee.amisystem/com.gionee.change.activity.OnlineActivity;end";
    private static final String REPLACE1 = "#Intent;action=gionee.intent.action.SET_THEME;category=android.intent.category.LAUNCHER;component=com.gionee.amisystem/com.gionee.change.activity.ChangeGridActivity;end";
    private static final String TARGET = "#Intent;action=android.intent.action.MAIN;category=android.intent.category.LAUNCHER;component=com.gionee.amisystem/com.gionee.change.activity.ChangeGridActivity;end";
    private static final String THEME = "随变主题";
    private static final String THEME1 = "随变秀秀";
    private static final String THEME_PARK = "主题公园";
    
    private static final String COMPONENT = "com.gionee.amisystem/com.gionee.change.activity.OnlineActivity";
    private static final String TARGET_COMPONENT = "com.gionee.amisystem/com.gionee.change.activity.ChangeGridActivity";

    private void loadRules() {
        rules = new ArrayList<AbstractRule>();

        RuleReplace intent = new RuleReplace();
        intent.setNode("intent");
        intent.addReplace(REPLACE);
        intent.addReplace(REPLACE1);
        intent.setTarget(TARGET);
        rules.add(intent);

        RuleReplace title = new RuleReplace();
        title.setNode("original-title");
        title.addReplace(THEME);
        title.addReplace(THEME1);
        title.setTarget(THEME_PARK);
        rules.add(title);
        
        RuleReplace component = new RuleReplace();
        component.setNode("component");
        component.addReplace(COMPONENT);
        component.setTarget(TARGET_COMPONENT);
        rules.add(component);
        /*
         * AbstractRule checkGrid = new RuleCheckGrid();
         * checkGrid.setNode("panel"); rules.add(checkGrid); AbstractRule rename
         * = new RuleRenamePicture(); rename.setNode("desktop");
         * rules.add(rename);
         */
    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    public void startParse(String filePath) {
        for (AbstractRule rule : rules) {
            SAXReader saxReader = new SAXReader();
            Document document;
            try {
                document = saxReader.read(new BufferedReader(
                        new InputStreamReader(new FileInputStream(filePath),
                                "UTF-8")));
                List list = document.selectNodes("//" + rule.getNode());
                Iterator iter = list.iterator();
                while (iter.hasNext()) {
                    Element element = (Element) iter.next();
                    rule.executeRule(element);
                }
                XMLWriter writer = new XMLWriter(new FileWriter(filePath));
                document.setXMLEncoding("UTF-8");
                writer.write(document);
                writer.close();
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
        }
    }

    private void printDetail(String content) {
        Logger.printDetail(content);
    }
}
