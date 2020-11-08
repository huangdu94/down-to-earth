package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 901. 股票价格跨度
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 * 示例：
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 * 提示：
 * 调用 StockSpanner.next(int price) 时，将有 1 <= price <= 10^5。
 * 每个测试用例最多可以调用  10000 次 StockSpanner.next。
 * 在所有测试用例中，最多调用 150000 次 StockSpanner.next。
 * 此问题的总时间限制减少了 50%。
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/26 15:11
 */
/*public class StockSpanner {
    private final LinkedList<Integer> priceList;
    private int max;

    public StockSpanner() {
        priceList = new LinkedList<>();
        max = 0;
    }

    public int next(int price) {
        priceList.add(price);
        if (price >= max) {
            max = price;
            return priceList.size();
        }
        int day = 1;
        ListIterator<Integer> iterator = priceList.listIterator(priceList.size() - 1);
        while (iterator.hasPrevious() && price >= iterator.previous()) {
            day++;
        }
        return day;
    }
}*/
/*
public class StockSpanner {
    private final Deque<Pair<Integer, Integer>> stack;

    public StockSpanner() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int day = 1;
        while (!stack.isEmpty() && stack.peek().getKey() <= price) {
            day += stack.remove().getValue();
        }
        stack.push(new Pair<>(price, day));
        return day;
    }
}*/
/*
public class StockSpanner {
    private final Deque<Integer> pStack, dStack;

    public StockSpanner() {
        pStack = new LinkedList<>();
        dStack = new LinkedList<>();
    }

    public int next(int price) {
        int day = 1;
        while (!pStack.isEmpty() && pStack.peek() <= price) {
            pStack.pop();
            day += dStack.pop();
        }
        pStack.push(price);
        dStack.push(day);
        return day;
    }
}*/
public class StockSpanner {
    private final List<Integer> stockList = new ArrayList<>();
    private final Deque<Integer> indexStack = new LinkedList<>();

    public StockSpanner() {
    }

    public int next(int price) {
        while (indexStack.size() > 0 && stockList.get(indexStack.peek()) <= price) {
            indexStack.pop();
        }
        int day = stockList.size() - (indexStack.isEmpty() ? -1 : indexStack.peek());
        indexStack.push(stockList.size());
        stockList.add(price);
        return day;
    }

    public static void main(String[] args) {
        StockSpanner s = new StockSpanner();
        //[[],[100],[80],[60],[70],[60],[75],[85]]
        System.out.println(s.next(100));
        System.out.println(s.next(80));
        System.out.println(s.next(60));
        System.out.println(s.next(70));
        System.out.println(s.next(60));
        System.out.println(s.next(75));
        System.out.println(s.next(85));
    }
}

class StockSpanner2 {
    private static int day;
    private final Deque<int[]> stack;

    public StockSpanner2() {
        day = 0;
        stack = new LinkedList<>();
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        int start = stack.isEmpty() ? 0 : stack.peek()[1];
        stack.push(new int[]{price, ++day});
        return day - start;
    }

    public static void main(String[] args) {
        StockSpanner2 s = new StockSpanner2();
        //[[],[100],[80],[60],[70],[60],[75],[85]]
        System.out.println(s.next(100));
        System.out.println(s.next(80));
        System.out.println(s.next(60));
        System.out.println(s.next(70));
        System.out.println(s.next(60));
        System.out.println(s.next(75));
        System.out.println(s.next(85));
    }
}