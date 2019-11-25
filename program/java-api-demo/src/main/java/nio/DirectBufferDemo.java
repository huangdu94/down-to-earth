package nio;


import java.nio.ByteBuffer;

/**
 * @author duhuang@iflytek
 * @version 2019/11/20 14:24
 */
public class DirectBufferDemo {
    public static void main(String[] args) {
        ByteBuffer directBuffer= ByteBuffer.allocateDirect(10);
    }
}
