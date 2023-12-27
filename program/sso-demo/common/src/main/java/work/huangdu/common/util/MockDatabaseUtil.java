package work.huangdu.common.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import work.huangdu.common.entity.ClientInfoDO;

/**
 * 模拟数据库工具类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
public class MockDatabaseUtil {
    public static final Set<String> T_TOKEN = new HashSet<>();
    public static final Map<String, List<ClientInfoDO>> T_CLIENT_INFO = new HashMap<>();
    public static final Map<String, String> T_USER_INFO = new HashMap<>();

    static {
        T_USER_INFO.put("zhangsan", "666");
    }
}
