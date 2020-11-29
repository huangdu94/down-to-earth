package com.iflytek.pattern.structure.adapter;

import com.iflytek.pattern.structure.adapter.extension.AdvancedMediaPlayer;
import com.iflytek.pattern.structure.adapter.extension.Mp4Player;
import com.iflytek.pattern.structure.adapter.extension.VlcPlayer;
import com.iflytek.pattern.structure.adapter.origin.MediaPlayer;

/**
 * 适配器，用于扩展原播放器功能
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 15:36
 */
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer player;

    // 此处可以融合别的设计模式
    public MediaAdapter(String audioType) {
        switch (audioType.toLowerCase()) {
            case "mp4":
                player = new Mp4Player();
                break;
            case "vlc":
                player = new VlcPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType.toLowerCase()) {
            case "mp4":
                player.playMp4(fileName);
                break;
            case "vlc":
                player.playVlc(fileName);
        }
    }
}
