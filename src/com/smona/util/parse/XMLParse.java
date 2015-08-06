package com.smona.util.parse;

public class XMLParse {

    private IParse parse;

    public void startParseXML(String filePath) {
        // parse = new DomParse();
        parse = new Dom4jParse();
        parse.startParse(filePath);
    }
}
