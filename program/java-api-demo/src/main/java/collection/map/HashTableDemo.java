package collection.map;

import java.util.Hashtable;

/**
 * HashTable使用方法几乎与HashMap相同，但是它是线程安全的，处理性能较低
 *
 * @author DuHuang
 * @date 2019/11/10 13:05
 */
public class HashTableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("1","hello");
        hashtable.put("2","world");
        System.out.println(hashtable);
    }
}
