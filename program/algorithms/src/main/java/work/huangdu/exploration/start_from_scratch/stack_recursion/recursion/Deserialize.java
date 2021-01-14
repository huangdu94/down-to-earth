package work.huangdu.exploration.start_from_scratch.stack_recursion.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 385. 迷你语法分析器
 * 给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。
 * 列表中的每个元素只可能是整数或整数嵌套列表
 * 提示：你可以假定这些字符串都是格式良好的：
 * 字符串非空
 * 字符串不包含空格
 * 字符串只包含数字0-9、[、-、,、]
 * 示例 1：
 * 给定 s = "324",
 * 你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
 * 示例 2：
 * 给定 s = "[123,[456,[789]]]",
 * 返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
 * 1. 一个 integer 包含值 123
 * 2. 一个包含两个元素的嵌套列表：
 * *    i.  一个 integer 包含值 456
 * *    ii. 一个包含一个元素的嵌套列表
 * *         a. 一个 integer 包含值 789
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/26 14:11
 */
public class Deserialize {
    private int i = 0;
    private char[] chars;
    private int n;

    public NestedInteger deserialize(String s) {
        chars = s.toCharArray();
        n = s.length();
        if (chars[i] != '[') {
            return new NestedInteger(nextNumber());
        }
        return helper();
    }

    private NestedInteger helper() {
        NestedInteger ni = new NestedInteger();
        i++;
        while (chars[i] != ']') {
            if (chars[i] == ',') {
                i++;
            } else if (chars[i] == '[') {
                ni.add(helper());
            } else {
                ni.add(new NestedInteger(nextNumber()));
            }
        }
        i++;
        return ni;
    }

    private int nextNumber() {
        int num = 0, sign = 1;
        if (chars[i] == '-') {
            sign = -1;
            i++;
        }
        while (i < n && Character.isDigit(chars[i])) {
            num = num * 10 + (chars[i++] - '0');
        }
        return sign * num;
    }

    public static void main(String[] args) {
        Deserialize deserialize = new Deserialize();
        System.out.println(deserialize.deserialize("[123,456,[788,799,833],[[]],10,[]]"));
    }
}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface INestedInteger {
    // Constructor initializes an empty nested list.
    // public NestedInteger();

    // Constructor initializes a single integer.
    // public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedInteger implements INestedInteger {

    Integer value;
    List<NestedInteger> list;

    public NestedInteger() {
        list = new ArrayList<>();
    }

    public NestedInteger(int value) {
        this.value = value;
    }

    @Override
    public boolean isInteger() {
        return value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public void setInteger(int value) {
        this.value = value;
    }

    @Override
    public void add(NestedInteger ni) {
        list.add(ni);
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}