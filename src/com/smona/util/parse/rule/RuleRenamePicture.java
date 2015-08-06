package com.smona.util.parse.rule;

import org.dom4j.Element;

import com.smona.util.common.ResultCode;

public class RuleRenamePicture extends AbstractRule {
    @Override
    public int executeRule(Element element) {

        return ResultCode.OK_CODE;
    }
}
