package club.huangdu94.pattern.structure.adapter.example2.sdcard;

/**
 * SD卡 实现类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        return "sd card read a msg :hello word SD";
    }

    @Override
    public int writeSD(String msg) {
        System.out.println("sd card write msg : " + msg);
        return 1;
    }
}
