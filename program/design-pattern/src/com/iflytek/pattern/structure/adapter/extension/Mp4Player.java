package com.iflytek.pattern.structure.adapter.extension;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 15:30
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
