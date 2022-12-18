package club.huangdu94.pattern.structure.adapter.example2.computer;

import club.huangdu94.pattern.structure.adapter.example2.sdcard.SDCard;

/**
 * 电脑 接口
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public interface Computer {
    String readSD(SDCard sdCard);

    int writeSD(SDCard sdCard, String msg);
}
