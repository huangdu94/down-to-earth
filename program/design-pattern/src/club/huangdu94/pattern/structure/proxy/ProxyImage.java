package club.huangdu94.pattern.structure.proxy;

/**
 * 跟装饰模式区别在于：
 * 装饰模式构造方法参数传了自己的同类，可以理解为自己代理自己......；
 * 装饰器模式为了增强功能，而代理模式是为了加以控制。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:06
 */
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
