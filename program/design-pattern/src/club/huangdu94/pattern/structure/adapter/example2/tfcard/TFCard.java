package club.huangdu94.pattern.structure.adapter.example2.tfcard;

/**
 * TF卡 接口
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public interface TFCard {
    String readTF();

    int writeTF(String msg);
}
