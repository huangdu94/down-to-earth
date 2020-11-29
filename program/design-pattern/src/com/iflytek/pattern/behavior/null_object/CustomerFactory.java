package com.iflytek.pattern.behavior.null_object;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:58
 */
public class CustomerFactory {
    public static final String[] names = {"Rob", "Joe", "Julie"};

    public static AbstractCustomer getCustomer(String name){
        for (String s : names) {
            if (s.equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}
