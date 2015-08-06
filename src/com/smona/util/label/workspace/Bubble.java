package com.smona.util.label.workspace;

import java.util.List;

import org.dom4j.Element;

import com.smona.util.label.Container;
import com.smona.util.label.Label;
import com.smona.util.label.Leaf;

public class Bubble extends Container {
    public static final String NAME = "bubble";

    @Override
    public void parseChild(Element self) {
        @SuppressWarnings("unchecked")
        List<Element> list = self.elements();
        Label label;
        for (Element child : list) {
            label = new Leaf();
            label.parse(child);
            addChild(label);
        }
    }

}
