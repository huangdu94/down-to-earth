package com.iflytek.pattern.behavior.null_object;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:56
 */
public class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}
