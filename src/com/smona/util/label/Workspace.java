package com.smona.util.label;

import java.util.List;

import org.dom4j.Element;

import com.smona.util.label.workspace.Bubble;
import com.smona.util.label.workspace.Desktop;
import com.smona.util.label.workspace.DisplayOptions;
import com.smona.util.label.workspace.Dock;
import com.smona.util.label.workspace.Font;
import com.smona.util.label.workspace.Indicator;

public class Workspace extends Container {
    public static final String NAME = "workspace";

    @Override
    public void parseChild(Element self) {
        @SuppressWarnings("unchecked")
        List<Element> list = self.elements();
        Label label;
        for (Element child : list) {
            label = null;
            String name = child.getName();
            if (Dock.NAME.equals(name)) {
                label = new Dock();
            } else if (Desktop.NAME.equals(name)) {
                label = new Desktop();
            } else if (Indicator.NAME.equals(name)) {
                label = new Indicator();
            } else if (DisplayOptions.NAME.equals(name)) {
                label = new DisplayOptions();
            } else if (Font.NAME.equals(name)) {
                label = new Font();
            } else if (Bubble.NAME.equals(name)) {
                label = new Bubble();
            }

            if (label != null) {
                label.parse(child);
                addChild(label);
            }
        }
    }

}
