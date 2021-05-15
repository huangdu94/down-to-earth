package club.huangdu94.pattern.structure.adapter.example2.adapter;

import club.huangdu94.pattern.structure.adapter.example2.sdcard.SDCard;
import club.huangdu94.pattern.structure.adapter.example2.tfcard.TFCard;

/**
 * SD适配TF（也可以说是SD兼容TF，相当于读卡器）
 * 实现SDCard接口，并将要适配的对象作为适配器的属性引入
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class SDAdapterTF implements SDCard {
    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    public TFCard getTfCard() {
        return tfCard;
    }

    public void setTfCard(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card ");
        return tfCard.readTF();
    }

    @Override
    public int writeSD(String msg) {
        System.out.println("adapter write tf card");
        return tfCard.writeTF(msg);
    }
}
