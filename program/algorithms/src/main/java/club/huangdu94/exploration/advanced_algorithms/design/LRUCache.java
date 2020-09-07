package club.huangdu94.exploration.advanced_algorithms.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶:
 * 你是否可以在O(1) 时间复杂度内完成这两种操作？
 * 示例:
 * LRUCache cache = new LRUCache( 2 // 缓存容量 );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/4 15:55
 */
public class LRUCache {
    /*private final int capacity;
    private final Map<Integer, Integer> cache;
    private final Queue<Integer> optQueue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.optQueue = new LinkedList<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            optQueue.remove(key);
            optQueue.offer(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            optQueue.remove(key);
        } else {
            if (optQueue.size() == capacity) {
                cache.remove(optQueue.remove());
            }
        }
        optQueue.offer(key);
        cache.put(key, value);
    }*/
    /*private final int capacity;
    private final Map<Integer, Integer> cache;
    private final Map<Integer, Integer> count;
    private final Queue<Integer> optQueue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.count = new HashMap<>(capacity);
        this.optQueue = new LinkedList<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            count.merge(key, 1, Integer::sum);
            optQueue.offer(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            if (count.size() == capacity) {
                int removeKey;
                while (count.merge(removeKey = optQueue.remove(), -1, Integer::sum) != 0) ;
                count.remove(removeKey);
                cache.remove(removeKey);
            }
        }
        count.merge(key, 1, Integer::sum);
        optQueue.offer(key);
        cache.put(key, value);
    }*/

    private final int capacity;
    private final Map<Integer, DoublyListNode> linkedMap;
    private final DoublyListNode headDummy;
    private final DoublyListNode tailDummy;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.linkedMap = new HashMap<>(capacity);
        headDummy = new DoublyListNode();
        tailDummy = new DoublyListNode();
        headDummy.next = tailDummy;
        tailDummy.prev = headDummy;
    }

    public int get(int key) {
        DoublyListNode node = linkedMap.get(key);
        if (node == null) return -1;
        moveNodeToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoublyListNode node = linkedMap.get(key);
        if (node != null) {
            node.value = value;
            moveNodeToTail(node);
        } else {
            if (linkedMap.size() == capacity) {
                removeHeadNodeAndEntry();
            }
            node = new DoublyListNode(key, value);
            addNodeToTail(node);
            linkedMap.put(key, node);
        }
    }

    private void addNodeToTail(DoublyListNode node) {
        node.prev = tailDummy.prev;
        tailDummy.prev = node;
        node.next = tailDummy;
        node.prev.next = node;
    }

    private DoublyListNode removeNode(DoublyListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    private void moveNodeToTail(DoublyListNode node) {
        addNodeToTail(removeNode(node));
    }

    private void removeHeadNodeAndEntry() {
        DoublyListNode head = headDummy.next;
        linkedMap.remove(removeNode(head).key);
    }

    private static class DoublyListNode {
        public int key;
        public int value;
        public DoublyListNode prev;
        public DoublyListNode next;

        public DoublyListNode() {
        }

        public DoublyListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
