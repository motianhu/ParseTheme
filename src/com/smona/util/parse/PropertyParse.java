package com.smona.util.parse;

import java.util.ArrayList;
import java.util.List;

public class PropertyParse {

    List<String> modify;

    public PropertyParse() {
        modify = new ArrayList<String>();
        modify.add("zh_CN");
        modify.add("en_US");
    }

    public void startParseProperty(String content, String filePath) {
        PropertyRead parse = new PropertyRead();
        for (String key : modify) {
            parse.setContent(content);
            parse.setKey(key);
            parse.startParse(filePath + "/language.properties");
        }

        PropertyRead since = new PropertyRead();
        since.setContent("" + System.currentTimeMillis());
        since.setKey("since");
        since.startParse(filePath + "/since.properties");
    }
}
