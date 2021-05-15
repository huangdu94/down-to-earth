package com.iflytek.pattern.structure.adapter.example1.extension;

/**
 * 更高级的媒体播放器
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 15:27
 */
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);

    void playMp4(String fileName);
}
