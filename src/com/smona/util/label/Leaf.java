package com.smona.util.label;

import org.dom4j.Element;

public class Leaf extends Label {

    @Override
    public void parseSelf(Element self) {
        setName(self.getName());
        setValue(self.getText());
    }

    @Override
    public void parseChild(Element self) {

    }

    @Override
    public boolean isEquals(Label label) {
        String content = getValue();
        return (content == label.getValue())
                || ((content != null && label.getValue() != null) && (content
                        .equals(label.getValue())));
    }

}
