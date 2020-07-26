package club.huangdu94.algorithm_middle.design;

import java.util.*;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/20 22:57
 */
public class RandomizedSet {
    private static final int INIT_LEN = 100;
    private static final double EXPAND_FACTOR = 0.25;
    private int CURRENT_LEN;
    /**
     * -Integer.MIN_VALUE/32
     */
    private static final int FLAG_LEN = 67108864;
    private int[] valArr;
    private int size;
    private final int[] positiveFlag;
    private final int[] negativeFlag;
    private final Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        this.valArr = new int[INIT_LEN];
        this.CURRENT_LEN = INIT_LEN;
        size = 0;
        // 0 -> Integer.MAX_VALUE
        this.positiveFlag = new int[FLAG_LEN];
        //-1 -> Integer.MIN_VALUE
        this.negativeFlag = new int[FLAG_LEN];
        this.random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (canOperate(val, true)) {
            if (size >= CURRENT_LEN)
                valArr = Arrays.copyOf(valArr, CURRENT_LEN = (int) (valArr.length * (1 + EXPAND_FACTOR)));
            valArr[size++] = val;
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (canOperate(val, false)) {
            int lastIndex = size-- - 1;
            int i;
            for (i = 0; i < lastIndex; i++)
                if (valArr[i] == val)
                    break;
            if (i < lastIndex) {
                int temp = valArr[i];
                valArr[i] = valArr[lastIndex];
                valArr[lastIndex] = temp;
            }
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        if (size < 1)
            throw new NoSuchElementException();
        return valArr[random.nextInt(size)];
    }

    /**
     * 判断是否可操作,并封装flag改变
     *
     * @param val     所要操作的值
     * @param operate true为 add false为remove
     * @return 是否可操作
     */
    private boolean canOperate(int val, boolean operate) {
        int value = val < 0 ? val + 1 : val;
        int[] flagArr = val < 0 ? negativeFlag : positiveFlag;
        int index = (value) >>> 5;
        int bit = (value) & 0x1f;
        int flag = 1 << bit;
        if (operate) {
            if ((flagArr[index] & flag) != 0)
                return false;
            flagArr[index] |= flag;
        } else {
            if ((flagArr[index] & flag) == 0)
                return false;
            flagArr[index] &= (~flag);
        }
        return true;
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
class RandomizedSet2 {
    private final Set<Integer> valSet;
    private final Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet2() {
        valSet = new HashSet<>();
        this.random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        return valSet.add(val);
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        return valSet.remove(val);
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Integer[] valArr = valSet.toArray(new Integer[0]);
        return valArr[random.nextInt(valArr.length)];
    }
}

class RandomizedSet3 {
    private static final int INIT_LEN = 100;
    private int CURRENT_LEN;
    private static final double EXPAND_FACTOR = 0.25;
    private final Map<Integer, Integer> valIndexMap;
    private int[] valArr;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet3() {
        valIndexMap = new HashMap<>(INIT_LEN);
        this.valArr = new int[INIT_LEN];
        this.CURRENT_LEN = INIT_LEN;
        size = 0;
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valIndexMap.containsKey(val))
            return false;
        if (size >= CURRENT_LEN)
            valArr = Arrays.copyOf(valArr, CURRENT_LEN = (int) (valArr.length * (1 + EXPAND_FACTOR)));
        valIndexMap.put(val, size);
        valArr[size++] = val;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        Integer index = valIndexMap.remove(val);
        if (index == null)
            return false;
        if (index != size - 1) {
            int lastVal = valArr[size - 1];
            valArr[index] = lastVal;
            valIndexMap.put(lastVal, index);
        }
        size--;
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return valArr[(int) (Math.random() * size)];
    }

    public static void main(String[] args) {
        RandomizedSet3 randomizedSet = new RandomizedSet3();
        for (int i = 1000000000; i <= 1000000099; i++) {
            System.out.println(randomizedSet.insert(i));
        }
        for (int i = -1000000000; i >= -1000000099; i--) {
            System.out.println(randomizedSet.insert(i));
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(randomizedSet.getRandom());
        }
        for (int i = 1000000000; i <= 1000000099; i++) {
            System.out.println(randomizedSet.remove(i));
        }
        for (int i = -1000000000; i >= -1000000099; i--) {
            System.out.println(randomizedSet.remove(i));
        }
    }
}