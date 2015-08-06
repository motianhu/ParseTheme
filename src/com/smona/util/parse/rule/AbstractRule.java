package com.smona.util.parse.rule;

import org.dom4j.Element;

import com.smona.util.common.Logger;
import com.smona.util.common.ResultCode;

public abstract class AbstractRule implements IRule {

    private String mNode;

    public void setNode(String node) {
        mNode = node;
    }

    public String getNode() {
        return mNode;
    }

    @Override
    public int executeRule(Element element) {
        return ResultCode.OK_CODE;
    }

    protected void println(String msg) {
        Logger.printDetail(msg);
    }

    protected void printReport(String msg) {
        Logger.printReport(msg);
    }
}
