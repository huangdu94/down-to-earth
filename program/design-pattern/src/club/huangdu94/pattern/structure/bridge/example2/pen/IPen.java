package club.huangdu94.pattern.structure.bridge.example2.pen;

import club.huangdu94.pattern.structure.bridge.example2.color.IColor;

/**
 * 笔接口
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public interface IPen {
    void draw(IColor color);
}
