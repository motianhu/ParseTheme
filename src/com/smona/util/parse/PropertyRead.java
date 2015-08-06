package com.smona.util.parse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyRead implements IParse {

    private String key;
    private String content;

    @Override
    public void startParse(String filePath) {
        Properties properties = new Properties();
        try {
            InputStream inStream = new FileInputStream(filePath);
            properties.load(inStream);
            inStream.close();
            OutputStream fos = new FileOutputStream(filePath);
            properties.setProperty(key, content);
            properties.store(fos, "");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
