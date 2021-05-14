package com.iflytek.pattern.create.builder.example1.entity.frame.impl;

import com.iflytek.pattern.create.builder.example1.entity.frame.IFrame;

/**
 * 合金框架
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class AlloyFrame implements IFrame {
    @Override
    public void frame() {
        System.out.println("合金框架.");
    }
}
