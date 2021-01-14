package work.huangdu.exploration.advanced_algorithms.design;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4]的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/7 16:43
 */
public class MedianFinder {
    private int size = 0;
    private int capacity = 10;
    private int[] array;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        array = new int[capacity];
    }

    public void addNum(int num) {
        if (size >= capacity) {
            array = Arrays.copyOf(array, capacity *= 1.25);
        }
        int position = Arrays.binarySearch(array, 0, size, num);
        if (position < 0) {
            position = -position - 1;
        }
//        int j = size - 1;
//        while (j >= 0 && array[j] > num) {
//            array[j + 1] = array[j];
//            j--;
//        }
//        array[j + 1] = num;
        System.arraycopy(array, position, array, position + 1, size - position);
        array[position] = num;
        size++;
    }

    public double findMedian() {
        int mid = size / 2;
        if (size % 2 == 0) {
            return (array[mid - 1] + array[mid]) / 2.0;
        }
        return array[mid];
    }
}

class MedianFinder2 {
    private final PriorityQueue<Integer> small;
    private final PriorityQueue<Integer> big;

    /**
     * initialize your data structure here.
     */
    public MedianFinder2() {
        small = new PriorityQueue<>((o1, o2) -> o2 - o1);
        big = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    }

    public void addNum(int num) {
        if (small.size() == 0) {
            small.offer(num);
            return;
        }
        if (small.size() > big.size()) {
            int smallBig = small.peek();
            if (num >= smallBig) {
                big.offer(num);
            } else {
                big.offer(smallBig);
                small.poll();
                small.offer(num);
            }
        } else {
            int bigSmall = big.peek();
            if (num <= bigSmall) {
                small.offer(num);
            } else {
                small.offer(bigSmall);
                big.poll();
                big.offer(num);
            }
        }
    }

    public double findMedian() {
        if (small.size() > big.size()) return small.peek();
        return (small.peek() + big.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(-1);
        finder.addNum(-2);
        finder.addNum(-3);
        finder.addNum(-4);
        finder.addNum(-5);
        System.out.println(finder.findMedian());
    }
}