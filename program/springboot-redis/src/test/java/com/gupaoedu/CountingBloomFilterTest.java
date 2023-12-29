package com.gupaoedu;

import orestes.bloomfilter.CountingBloomFilter;
import orestes.bloomfilter.FilterBuilder;

/**
 * @Author: qingshan
 * @Date: 2018/12/10 21:26
 * @Description: 咕泡学院，只为更好的你
 * https://github.com/Baqend/Orestes-Bloomfilter
 */
public class CountingBloomFilterTest {
    public static void main(String[] args) {
        CountingBloomFilter<String> cbf = new FilterBuilder(1000,
                0.01).buildCountingBloomFilter();
        cbf.add("http://gupaoedu.com");
        cbf.add("http://alibaba.com");
        cbf.add("http://baidu.com");

        cbf.remove("http://baidu.com");

        System.out.println(cbf.contains("http://gupaoedu.com")); //true
        System.out.println(cbf.contains("http://alibaba.com")); //true
        System.out.println(cbf.contains("http://baidu.com")); //false
    }
}
