package com.smona.util.label;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class Container extends Label {

    @Override
    public void parseSelf(Element self) {
        setChilds(new ArrayList<Label>());
        setName(self.getName());
    }

    @Override
    public void parseChild(Element self) {

    }

    @Override
    public boolean isEquals(Label label) {
        boolean result = true;
        List<Label> newChilds = label.getChilds();
        List<Label> oldChilds = getChilds();
        if (newChilds == oldChilds) {
            printDetail("newChilds: " + newChilds + ", oldChilds: " + oldChilds);
            return result;
        }
        if (newChilds.size() == 0 && oldChilds.size() == 0) {
            printDetail("newChilds.size(): " + newChilds.size() + ", oldChilds.size() : " + oldChilds.size() );
            return result;
        }

        for (Label oldChild : oldChilds) {
            String oldName = oldChild.getName();
            for (Label newChild : newChilds) {
                String newName = newChild.getName();
                if(oldName.equals(newName)) {
                    printDetail("oldChild: " + oldChild + ", newChild: " + newChild);
                    result = oldChild.isEquals(newChild);
                    if(!result) {
                        return false;
                    }
                }
            }
            
        }
        return result;
    }

}
