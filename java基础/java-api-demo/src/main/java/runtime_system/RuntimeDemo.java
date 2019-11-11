package runtime_system;

import java.io.IOException;

/**
 * Runtime类单例模式
 * 表示运行时，一个JVM中只存在一个Runtime实例，可以获取JVM相关信息
 *
 * @author DuHuang 2019/11/3 13:22
 */
public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        //获得Runtime实例
        Runtime runtime = Runtime.getRuntime();
        //可用CPU数量
        System.out.println("availableProcessors:" + runtime.availableProcessors());
        //获取内存相关信息
        System.out.println("maxMemory:" + bTransferMb(runtime.maxMemory()));
        System.out.println("totalMemory:" + bTransferMb(runtime.totalMemory()));
        System.out.println("freeMemory:" + bTransferMb(runtime.freeMemory()));
        //制造垃圾
        createTrash();
        //获取内存相关信息
        System.out.println("maxMemory:" + bTransferMb(runtime.maxMemory()));
        System.out.println("totalMemory:" + bTransferMb(runtime.totalMemory()));
        System.out.println("freeMemory:" + bTransferMb(runtime.freeMemory()));
        //通知gc回收垃圾
        runtime.gc();
        //获取内存相关信息
        System.out.println("maxMemory:" + bTransferMb(runtime.maxMemory()));
        System.out.println("totalMemory:" + bTransferMb(runtime.totalMemory()));
        System.out.println("freeMemory:" + bTransferMb(runtime.freeMemory()));
    }

    /**
     * byte 转换为 MB
     *
     * @param b 字节数
     * @return MB
     */
    private static double bTransferMb(long b) {
        return b / 1024.0 / 1024.0;
    }

    /**
     * 通过不断改变字符串来制造垃圾
     */
    private static void createTrash() {
        String str = "";
        for (int i = 0; i < 3000; i++) {
            str += "H";
        }
    }
}
