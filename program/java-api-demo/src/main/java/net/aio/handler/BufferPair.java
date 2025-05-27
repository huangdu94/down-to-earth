package net.aio.handler;

import java.nio.ByteBuffer;

/**
 * BufferPair
 *
 * @author huangdu
 * @version 2025/5/28
 */
public class BufferPair {
    private final ByteBuffer readBuffer;
    private final ByteBuffer writeBuffer;

    public BufferPair() {
        this(1024, 1024);
    }

    public BufferPair(int readBufferBytes, int writeBufferBytes) {
        this.readBuffer = ByteBuffer.allocate(1024);
        this.writeBuffer = ByteBuffer.allocate(1024);
    }

    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }

    public ByteBuffer getWriteBuffer() {
        return writeBuffer;
    }
}
