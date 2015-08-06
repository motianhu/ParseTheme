package com.smona.util.label;

import java.util.List;

import org.dom4j.Element;

public class Suibian extends Container {
    public static final String NAME = "suibian";

    public void parseChild(Element self) {
        @SuppressWarnings("unchecked")
        List<Element> list = self.elements();
        Label label = null;
        for (Element child : list) {
            label = null;
            String name = child.getName();
            if (Workspace.NAME.equals(name)) {
                label = new Workspace();
            } else {
                label = new Leaf();
            }

            label.parse(child);
            addChild(label);
        }
    }
}
