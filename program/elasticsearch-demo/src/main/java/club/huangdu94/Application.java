package club.huangdu94;

import club.huangdu94.opt.IndexAdd;

import java.io.IOException;

/**
 * 程序入口,模仿SpringBoot
 *
 * @author duhuang@iflytek.com
 * @version 2020/1/30 15:26
 */
public class Application {
    public static void main(String[] args) {
        //IndexAdd.addOneProductByJson();
        try {
            IndexAdd.addOneProductByXContentBuilder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
