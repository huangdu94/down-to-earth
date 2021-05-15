package club.huangdu94.pattern.structure.adapter.example2.computer;

import club.huangdu94.pattern.structure.adapter.example2.sdcard.SDCard;

/**
 * thinkpad 电脑实现类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class ThinkpadComputer implements Computer {
    @Override
    public String readSD(SDCard sdCard) {
        if (sdCard == null) { throw new NullPointerException("sd card null"); }
        return sdCard.readSD();
    }

    @Override
    public int writeSD(SDCard sdCard, String msg) {
        if (sdCard == null) { throw new NullPointerException("sd card null"); }
        return sdCard.writeSD(msg);
    }
}
