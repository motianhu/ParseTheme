package com.smona.util.label;

import java.util.List;

import org.dom4j.Element;

import com.smona.util.label.workspace.Shortcut;

public class Panel extends Container {
    public static final String NAME = "panel";

    @Override
    public void parseChild(Element self) {
        @SuppressWarnings("unchecked")
        List<Element> list = self.elements();
        Label label;
        for (Element child : list) {
            String name = child.getName();
            if (Shortcut.NAME.equals(name)) {
                label = new Shortcut();
            } else {
                label = new Leaf();
            }
            label.parse(child);
            addChild(label);
        }
    }

    @Override
    public boolean isEquals(Label label) {
        boolean result = true;
        List<Label> newChilds = label.getChilds();
        List<Label> oldChilds = getChilds();;
        if (newChilds == oldChilds) {
            printDetail("newChilds: " + newChilds + ", oldChilds: " + oldChilds);
            return result;
        }
        if (newChilds.size() == 0 && oldChilds.size() == 0) {
            printDetail("newChilds.size(): " + newChilds.size()
                    + ", oldChilds.size() : " + oldChilds.size());
            return result;
        }

        boolean isEqualShortcut = true;
        for (Label oldChild : oldChilds) {
            String oldName = oldChild.getName();
            isEqualShortcut = true;
            for (Label newChild : newChilds) {
                String newName = newChild.getName();
                if (oldChild instanceof Shortcut) {
                    if (newChild instanceof Shortcut) {
                        isEqualShortcut = oldChild.isEquals(newChild);
                        if (isEqualShortcut) {
                            printDetail("oldChild: " + oldChild
                                    + ", newChild: " + newChild);
                            break;
                        }
                    }
                } else if (oldName.equals(newName)) {
                    printDetail("oldChild: " + oldChild + ", newChild: "
                            + newChild);
                    result = oldChild.isEquals(newChild);
                    if (!result) {
                        return false;
                    }
                }
            }
            if (!isEqualShortcut) {
                return false;
            }
        }
        return result;
    }
}
