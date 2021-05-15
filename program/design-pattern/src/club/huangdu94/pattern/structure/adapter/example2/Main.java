package club.huangdu94.pattern.structure.adapter.example2;

import club.huangdu94.pattern.structure.adapter.example2.adapter.SDAdapterTF;
import club.huangdu94.pattern.structure.adapter.example2.computer.Computer;
import club.huangdu94.pattern.structure.adapter.example2.computer.ThinkpadComputer;
import club.huangdu94.pattern.structure.adapter.example2.sdcard.SDCard;
import club.huangdu94.pattern.structure.adapter.example2.sdcard.SDCardImpl;
import club.huangdu94.pattern.structure.adapter.example2.tfcard.TFCard;
import club.huangdu94.pattern.structure.adapter.example2.tfcard.TFCardImpl;

/**
 * 适配器模式
 * 适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁。
 * 这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
 * 这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。
 * 举个真实的例子，读卡器是作为内存卡和笔记本之间的适配器。
 * 您将内存卡插入读卡器，再将读卡器插入笔记本，这样就可以通过笔记本来读取内存卡。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class Main {
    public static void main(String[] args) {
        Computer thinkpadComputer = new ThinkpadComputer();
        SDCard sdCard = new SDCardImpl();
        System.out.println(thinkpadComputer.readSD(sdCard));
        System.out.println(thinkpadComputer.writeSD(sdCard, "hello"));

        TFCard tfCard = new TFCardImpl();
        SDAdapterTF adapter = new SDAdapterTF(tfCard);
        System.out.println(thinkpadComputer.readSD(adapter));
        System.out.println(thinkpadComputer.writeSD(adapter, "hello"));
    }
}
