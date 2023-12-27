package work.huangdu.common.entity;

import lombok.Data;

/**
 * 客户端登录信息封装
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
@Data
public class ClientInfoDO {
    /**
     * 客户端的登出接口url
     */
    private String clientUrl;
    /**
     * 客户端的cookie key
     */
    private String jsessionId;
}
