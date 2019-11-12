package file_stream.file;

import java.io.File;

/**
 * 递归练习：
 * 1：编写一段程序，要求实现1+2+3+4+...100并输出结果.(在这段代码中不得出现for,while关键字)
 * 2：假如1块钱可以买1瓶汽水，3个瓶盖可以换一瓶汽水2个空瓶可以换一瓶汽水，编一段程序计算20块钱总共可以买到多少瓶汽水？
 * 3：删除给定的File表示的文件或目录
 *
 * @author Bean
 */
public class RecursionDemo {
    public static void main(String[] args) {
//		File file=new File("a");
//		delete(file);
//		System.out.println(add(100));
        System.out.println(change(20));
    }

    public static void delete(File f) {
        /*
        for(File sub:subs) {
        	System.out.println(sub);
        }相当于
        for(int i=0:i<subs.length;i++) {
        	File sub=subs[i];
        	System.out.println(sub);
        }
        */
        if (f.isDirectory()) {
            //先将该目录中所有子项删除
            File[] subs = f.listFiles();
            if (subs != null) {
                for (File file : subs) {
                    delete(file);//递归调用
                }
            }
        }
        f.delete();
    }

    public static int add(int num) {
        if (num < 1) {
            return -1;
        } else if (num == 1) {
            return 1;
        } else {
            return num + add(num - 1);
        }
    }

    public static int change(int number) {
        return change(number, number, number);
    }

    public static int change(int number, int caps, int bottles) {
        if (number == 0) {
            return 0;
        }
        int addNumber = caps / 3 + bottles / 2; //这一轮所换瓶数
        int remainCaps = addNumber + caps % 3; //这一轮剩余瓶盖数
        int remainBottles = addNumber + bottles % 2; //这一轮剩余瓶子数
        return number + change(addNumber, remainCaps, remainBottles);
    }
}
