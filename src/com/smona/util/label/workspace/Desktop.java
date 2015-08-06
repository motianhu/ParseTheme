package com.smona.util.label.workspace;

import java.util.List;

import org.dom4j.Element;

import com.smona.util.label.Container;
import com.smona.util.label.Label;
import com.smona.util.label.Panel;

public class Desktop extends Container {
    public static final String NAME = "desktop";

    @Override
    public void parseChild(Element self) {
        @SuppressWarnings("unchecked")
        List<Element> list = self.elements();
        Label label;
        for (Element child : list) {
            label = new Panel();
            label.parse(child);
            addChild(label);
        }
    }

    @Override
    public boolean isEquals(Label label) {
        boolean result = true;
        List<Label> newChilds = label.getChilds();
        List<Label> oldChilds = getChilds();
        if (newChilds == oldChilds) {
            printDetail("Desktop newChilds: " + newChilds + ", oldChilds: " + oldChilds);
            return result;
        }
        if (newChilds.size() == 0 && oldChilds.size() == 0) {
            printDetail("Desktop newChilds.size(): " + newChilds.size()
                    + ", oldChilds.size() : " + oldChilds.size());
            return result;
        }

        boolean isEqualPanel = true;
        for (Label oldChild : oldChilds) {
            String oldName = oldChild.getName();
            isEqualPanel = true;
            for (Label newChild : newChilds) {
                String newName = newChild.getName();
                if (oldChild instanceof Panel) {
                    if (newChild instanceof Panel) {
                        isEqualPanel = oldChild.isEquals(newChild);
                        if (isEqualPanel) {
                            printDetail("Desktop oldChild: " + oldChild
                                    + ", newChild: " + newChild);
                            break;
                        }
                    }
                } else if (oldName.equals(newName)) {
                    printDetail("Desktop oldChild: " + oldChild + ", newChild: "
                            + newChild);
                    result = oldChild.isEquals(newChild);
                    if (!result) {
                        return false;
                    }
                }
            }
            if (!isEqualPanel) {
                return false;
            }
        }
        return result;
    }
}
