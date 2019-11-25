package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author duhuang@iflytek
 * @version 2019/11/20 11:25
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) {
        File file = new File("E:\\test\\channelDemo.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            FileChannel channel = inputStream.getChannel();
            //将文件映射到内存
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            //CharBuffer buffer = mappedByteBuffer.asCharBuffer();
            //IntBuffer buffer = mappedByteBuffer.asIntBuffer();
            ByteBuffer buffer = mappedByteBuffer;
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
