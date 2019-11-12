package file_stream.autocloseable;

import java.nio.channels.ClosedChannelException;

/**
 * AutoCloseableDemo自定义资源类
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 10:19
 */
public class MyResourse implements AutoCloseable {

    public void run() throws Exception{
        throw new Exception(this.toString()+"我遇到异常了");
    }

    @Override
    public void close() throws ClosedChannelException{
        System.out.println("我被关闭了");
        throw new ClosedChannelException();
    }
}
