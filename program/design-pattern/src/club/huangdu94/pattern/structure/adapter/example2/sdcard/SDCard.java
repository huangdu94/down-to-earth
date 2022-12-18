package club.huangdu94.pattern.structure.adapter.example2.sdcard;

/**
 * SD卡 接口
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public interface SDCard {
    //读取SD卡方法
    String readSD();

    //写入SD卡功能
    int writeSD(String msg);
}
