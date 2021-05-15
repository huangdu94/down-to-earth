package club.huangdu94.pattern.structure.adapter.example2.tfcard;

/**
 * TF卡 实现类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class TFCardImpl implements TFCard {
    @Override
    public String readTF() {
        return "tf card reade msg : hello word tf card";
    }

    @Override
    public int writeTF(String msg) {
        System.out.println("tf card write a msg : " + msg);
        return 1;
    }
}
