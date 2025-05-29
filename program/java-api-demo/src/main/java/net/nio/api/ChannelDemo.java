package net.nio.api;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author duhuang@iflytek
 * @version 2019/11/20 11:11
 */
public class ChannelDemo {
    public static void main(String[] args) {
        File file = new File("E:\\test\\channelDemo.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("文件创建成功.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            FileChannel outChannel = outputStream.getChannel();
            ByteBuffer outBuffer1 = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
            ByteBuffer outBuffer2 = ByteBuffer.wrap("world".getBytes(StandardCharsets.UTF_8));
            outChannel.write(outBuffer1);
            outChannel.write(outBuffer2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream inputStream = new FileInputStream(file)) {
            FileChannel inChannel = inputStream.getChannel();
            ByteBuffer inBuffer = ByteBuffer.allocate(100);
            inChannel.read(inBuffer);
            System.out.println(new String(inBuffer.array(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
