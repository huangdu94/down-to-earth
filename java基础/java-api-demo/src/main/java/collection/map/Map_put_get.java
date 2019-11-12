package collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Map（查找表）
 * Map在实际开发中是一种非常常用的数据结构.
 * 存储元素看起来像是一个"多行两列的表格".
 * Map中每个元素有两部分组成，分别为：key,value俗称：键值对.
 * 而从Map中查询数据的原则是根据key查找对应的value.
 * 所以Map要求key不允许重复（Map中不允许有key的equals比较为true.）
 * <p>
 * Map是一个接口，规定了Map这和总数据结构操作的相关方法.
 * 常用实现类：java.util.HashMap(俗称：散列表，哈希表）
 * TreeMap
 *
 * @author Bean
 */
public class Map_put_get {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        /*
         * V put(K k,V v)
         * 将给定的key-value对存入Map中.
         * 由于Map中不允许出现重复的key,所以若使用Map中已经存在的key存入一个新的value时，是做替换value操作，那么put方法的返回值就为该key原来对应的value，即：返回被替换的value
         * 若指定的key在Map中不存在，则存入后返回值为null.
         */
        map.put("语文", 80);
        map.put("数学", 100);
        map.put("英语", 95);
        map.put("物理", 100);
        map.put("化学", 100);
        System.out.println(map);

        //重复的key则是替换value操作
        int num = map.put("语文", 75);
        System.out.println(num);
        System.out.println(map);

        /*
         * V get(K k)
         * 根据给定的key获取对应的value，若给定的key在Map中不存在，则返回值为null.
         */

        num = map.get("数学");
        System.out.println(num);

        /*
         * V remove(K k)
         * 将给定的key所对应的键值对从当前Map中删除，返回值为该key所对应的value值.
         */
        num = map.remove("化学");
        System.out.println(num);
        System.out.println(map);

        System.out.println(map.containsKey("体育"));
        System.out.println(map.containsValue(97));
    }
}
