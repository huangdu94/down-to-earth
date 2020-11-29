package com.iflytek.pattern.structure.bridge;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:22
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius + ", x: " + x + ", " + y + "]");
    }
}
