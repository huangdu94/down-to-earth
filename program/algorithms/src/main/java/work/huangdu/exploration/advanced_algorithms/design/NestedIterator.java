package work.huangdu.exploration.advanced_algorithms.design;

import java.util.*;

/**
 * 341. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用next 直到hasNext 返回 false，next返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用next直到hasNext 返回 false，next返回的元素的顺序应该是: [1,4,6]。
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/7 15:25
 */
public class NestedIterator implements Iterator<Integer> {

    private final Deque<Iterator<NestedInteger>> iteratorStack;
    private Integer next;

    public NestedIterator(List<NestedInteger> nestedList) {
        iteratorStack = new LinkedList<>();
        iteratorStack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return next;
    }

    @Override
    public boolean hasNext() {
        while (!iteratorStack.isEmpty()) {
            Iterator<NestedInteger> iterator = iteratorStack.peek();
            if (iterator.hasNext()) {
                NestedInteger nextObject = iterator.next();
                if (nextObject.isInteger()) {
                    next = nextObject.getInteger();
                    return true;
                } else {
                    iteratorStack.push(nextObject.getList().iterator());
                    continue;
                }
            }
            iteratorStack.pop();
        }
        return false;
    }
}

class NestedIterator2 implements Iterator<Integer> {
    private final List<Integer> list = new ArrayList<>();
    private int index;

    public NestedIterator2(List<NestedInteger> nestedList) {
        index = 0;
        readNestedList(nestedList);
    }

    private void readNestedList(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
            } else {
                readNestedList(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}

/**
 * This is the interface that allows for creating nested lists.
 * You should not implement it, or speculate about its implementation
 */
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}