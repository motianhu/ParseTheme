package com.smona.util.parse.rule;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import com.smona.util.common.ResultCode;

public class RuleCheckGrid extends AbstractRule {

    @SuppressWarnings({ "rawtypes" })
    @Override
    public int executeRule(Element element) {
        int result = ResultCode.OK_CODE;
        List grids = element.elements("grid");
        println("grid count: " + grids.size());
        String value = ((Element) grids.get(0)).getText();
        int[] size = parseGrid(value, 2);
        println(value);

        List cells = element.selectNodes("shortcut/cell");
        println("cell count: " + cells.size());
        Iterator iter = cells.iterator();
        while (iter.hasNext()) {
            Element cell = (Element) iter.next();
            int[] xy = parseGrid(cell.getText(), 4);
            boolean x = xy[0] + xy[2] <= size[0];
            boolean y = xy[1] + xy[3] <= size[1];
            println(cell.getText());
            if (x && y) {
                continue;
            } else {
                result = ResultCode.CHECK_ERROR_CODE;
                printReport("Check problem: " + cell.getText());
            }
        }
        return result;
    }

    private int[] parseGrid(String value, int length) {
        int[] size = new int[length];
        String[] values = value.split(",");
        for (int i = 0; i < length; i++) {
            size[i] = Integer.valueOf(values[i]);
        }
        return size;
    }
}
