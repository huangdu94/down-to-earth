package club.huangdu94.pattern.structure.bridge.example2.color.impl;

import club.huangdu94.pattern.structure.bridge.example2.color.IColor;

/**
 * 蓝色
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class Blue implements IColor {
    @Override
    public String use() {
        return "蓝";
    }
}
