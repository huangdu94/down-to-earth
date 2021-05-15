package club.huangdu94.pattern.structure.bridge.example2.color.impl;

import club.huangdu94.pattern.structure.bridge.example2.color.IColor;

/**
 * 黄色
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class Yellow implements IColor {
    @Override
    public String use() {
        return "黄";
    }
}
