package club.huangdu94.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Product 实体类
 *
 * @author duhuang@iflytek.com
 * @version 2020/1/30 15:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    /**
     * 商品名
     */
    private String productName;
    /**
     * 生产日期
     */
    private Date createTime;
    /**
     * 价格
     */
    private Double price;
}
