package com.smona.util.parse.rule;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.smona.util.common.Logger;
import com.smona.util.common.ResultCode;

public class RuleReplace extends AbstractRule {

    private List<String> mReplaces;
    private String mTarget;

    public RuleReplace() {
        mReplaces = new ArrayList<String>();
    }

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        this.mTarget = target;
    }

    public void addReplace(String replace) {
        mReplaces.add(replace);
    }

    @Override
    public int executeRule(Element element) {
        String value = element.getText();
        if (isReplace(value)) {
            element.setText(mTarget);
            return ResultCode.MODIFY_CODE;
        }
        return ResultCode.OK_CODE;
    }

    private boolean isReplace(String value) {
        for (String replace : mReplaces) {
            if (replace.equals(value)) {
                Logger.printReport(mTarget + " replace " + value);
                return true;
            }
        }
        return false;
    }
}
