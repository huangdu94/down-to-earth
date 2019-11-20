package com.iflytek.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author duhuang@iflytek
 * @version 2019/11/20 10:22
 */
public class BufferDemo {
    public static void main(String[] args) {
        // Buffer创建两种方法
        // 1.
        ByteBuffer buffer = ByteBuffer.allocate(15);
        // 2.
        //byte[] arr = new byte[15];
        //ByteBuffer buffer = ByteBuffer.wrap(arr);
        showBufferState(buffer);
        // Buffer中存入值
        for (byte i = 0; i < 10; i++) {
            buffer.put(i);
        }
        showBufferState(buffer);
        buffer.flip();
        showBufferState(buffer);
        for (int i = 0; i < 5; i++) {
            System.out.println(buffer.get());
        }
        showBufferState(buffer);
        buffer.flip();
        showBufferState(buffer);
        for (int i = 0; i < 5; i++) {
            System.out.println(buffer.get());
        }
        showBufferState(buffer);
        //buffer.rewind();
        buffer.clear();
        showBufferState(buffer);
    }

    private static void showBufferState(Buffer buffer) {
        int capacity = buffer.capacity();
        int limit = buffer.limit();
        int position = buffer.position();
        System.out.println("capacity:" + capacity + ",position:" + position + ",limit:" + limit);
    }
}
