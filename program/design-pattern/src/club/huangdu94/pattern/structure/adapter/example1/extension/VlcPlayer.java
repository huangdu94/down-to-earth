package club.huangdu94.pattern.structure.adapter.example1.extension;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 15:29
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
    }
}
