package com.smona.util.label;

import java.util.List;

import org.dom4j.Element;

import com.smona.util.common.Logger;

public abstract class Label {
    private String value;
    private String name;
    private List<Label> childs;

    public List<Label> getChilds() {
        return childs;
    }
    
    public void addChild(Label label) {
        childs.add(label);
    }
    
    public void setChilds(List<Label> childs) {
        this.childs = childs;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void parse(Element self) {
        parseSelf(self);
        parseChild(self);
    }

    abstract public void parseSelf(Element self);

    abstract public void parseChild(Element self);

    abstract public boolean isEquals(Label label);
    
    public void display() {
        if(value == null) {
            return;
        }
        printDetail(value);
        if (childs == null) {
            return;
        }
        for (Label child : childs) {
            child.display();
        }
    }
    
    public static void printDetail(String content) {
        Logger.printDetail(content);
    }
    
    public static void printReport(String content) {
        Logger.printReport(content);
    }
    
    public String toString() {
        return "Name: " + name + ", Value: " + value + ", Childs is Null:  " + (childs == null);
    }
}
