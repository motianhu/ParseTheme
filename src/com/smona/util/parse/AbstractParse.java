package com.smona.util.parse;

import java.util.ArrayList;
import java.util.List;

import com.smona.util.parse.rule.AbstractRule;
import com.smona.util.parse.rule.RuleReplace;

public abstract class AbstractParse implements IParse {

    protected List<AbstractRule> rules;

    public AbstractParse() {
        loadRules();
    }

    private void loadRules() {
        rules = new ArrayList<AbstractRule>();
        AbstractRule replace = new RuleReplace();
        replace.setNode("grid");
        rules.add(replace);
        /*
         * AbstractRule checkGrid = new RuleCheckGrid();
         * checkGrid.setNode("panel"); rules.add(checkGrid); AbstractRule rename
         * = new RuleRenamePicture(); checkGrid.setNode("desktop");
         * rules.add(rename);
         */
    }

    @Override
    public void startParse(String filePath) {

    }

}
