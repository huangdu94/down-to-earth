package work.huangdu.question_bank.difficult;

import java.util.*;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * 注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/31 11:01
 */
public class RandomizedCollection {
    /**
     * 存储val和它们在numList中的下标列表
     */
    private final Map<Integer, Set<Integer>> valIndexMap = new HashMap<>();

    /**
     * 所存储数字列表
     */
    private final List<Integer> numList = new ArrayList<>();

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        Set<Integer> indexSet = valIndexMap.computeIfAbsent(val, k -> new HashSet<>());
        numList.add(val);
        indexSet.add(numList.size() - 1);
        return indexSet.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (valIndexMap.containsKey(val)) {
            Set<Integer> valIndexSet = valIndexMap.get(val);
            Integer valIndex = valIndexSet.iterator().next();
            valIndexSet.remove(valIndex);
            if (valIndexSet.size() == 0) {
                valIndexMap.remove(val);
            }
            int lastIndex = numList.size() - 1;
            if (valIndex != lastIndex) {
                int lastNum = numList.get(lastIndex);
                Set<Integer> lastNumIndexSet = valIndexMap.get(lastNum);
                lastNumIndexSet.remove(lastIndex);
                lastNumIndexSet.add(valIndex);
                numList.set(valIndex, lastNum);
            }
            numList.remove(lastIndex);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return numList.get((int) (Math.random() * numList.size()));
    }
}
