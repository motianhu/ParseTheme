package com.smona.util.parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.smona.util.common.Logger;
import com.smona.util.parse.rule.AbstractRule;
import com.smona.util.parse.rule.RuleReplace;

public class DomParse implements IParse {

    List<AbstractRule> rules;

    public DomParse() {
        loadRules();
    }

    private void loadRules() {
        rules = new ArrayList<AbstractRule>();
        AbstractRule rule = new RuleReplace();
        rule.setNode("intent");
        rules.add(rule);
    }

    @Override
    public void startParse(String filePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File(filePath));
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node suibian = employees.item(i);
                if ("suibian".equals(suibian.getNodeName())) {
                    parseSuibian(suibian);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Logger.printDetail(e.toString());
        } catch (SAXException e) {
            e.printStackTrace();
            Logger.printDetail(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Logger.printDetail(e.toString());
        }
    }

    private void parseSuibian(Node node) {
    }

}
