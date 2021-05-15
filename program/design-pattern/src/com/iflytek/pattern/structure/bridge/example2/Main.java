package com.iflytek.pattern.structure.bridge.example2;

import com.iflytek.pattern.structure.bridge.example2.color.IColor;
import com.iflytek.pattern.structure.bridge.example2.color.impl.Blue;
import com.iflytek.pattern.structure.bridge.example2.color.impl.Red;
import com.iflytek.pattern.structure.bridge.example2.color.impl.Yellow;
import com.iflytek.pattern.structure.bridge.example2.pen.IPen;
import com.iflytek.pattern.structure.bridge.example2.pen.impl.MatiniPen;
import com.iflytek.pattern.structure.bridge.example2.pen.impl.PicassoPen;
import com.iflytek.pattern.structure.bridge.example2.pen.impl.RubensPen;

/**
 * 桥接模式
 * 如果你有m支不同牌子的画笔，n种颜料，那么可以画出m * n种线条；而蜡笔想要做到同样的效果就需要m * n只。
 * 前面的画笔就是桥接模式（笔不同型号是一个变化维度，不同颜色的颜料是一个变化维度，两个维度不相互影响）；
 * 而后面的画笔就是多重继承（笔型号和颜料一起影响蜡笔）。理解以后我们将其抽象成代码描述：
 * pen类是一个抽象类，里面有一个方法draw()。我们想要使用画笔，则需要放入一种颜色，然后执行draw方法将这种颜色画出来。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:19
 */
public class Main {
    public static void main(String[] args) {
        IColor red = new Red();
        IColor yellow = new Yellow();
        IColor blue = new Blue();
        IPen matniPen = new MatiniPen();
        IPen picassoPen = new PicassoPen();
        IPen rubensPen = new RubensPen();
        matniPen.draw(red);
        matniPen.draw(yellow);
        matniPen.draw(blue);
        picassoPen.draw(red);
        picassoPen.draw(yellow);
        picassoPen.draw(blue);
        rubensPen.draw(red);
        rubensPen.draw(yellow);
        rubensPen.draw(blue);
    }
}
