package work.huangdu.question_bank.medium;

import java.util.*;

/**
 * 332. 重新安排行程
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 说明:
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 示例 1:
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2:
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/27 0:37
 */
public class FindItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Queue<String>> startDesMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            //Queue<String> desQueue = startDesMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>());
            if (!startDesMap.containsKey(ticket.get(0))) {
                startDesMap.put(ticket.get(0), new PriorityQueue<>());
            }
            startDesMap.get(ticket.get(0)).offer(ticket.get(1));
            //desQueue.offer(ticket.get(1));
        }
        LinkedList<String> journey = new LinkedList<>();
        helper(startDesMap, journey, "JFK");
        return journey;
    }

    private void helper(Map<String, Queue<String>> startDesMap, LinkedList<String> journey, String start) {
        Queue<String> nextQueue = startDesMap.get(start);
        while (nextQueue != null && !nextQueue.isEmpty()) {
            helper(startDesMap, journey, nextQueue.poll());
        }
        journey.addFirst(start);
    }

    // 处理节点
/*    private void helper(Map<String, Queue<String>> startDesMap, List<String> trunk, String start, String end) {
        trunk.add(start);
        if (start.equals(end)) {
            return;
        }
        Queue<String> desQueue = startDesMap.get(start);
        // 如果可选目的地址只有一个，则直接加入trunk最终行程
        while (desQueue != null && desQueue.size() == 1) {
            String next = desQueue.poll();
            trunk.add(next);
            desQueue = startDesMap.get(next);
        }
        // 进入分支处理
        if (desQueue != null && desQueue.size() > 1) {
            // 多个分支最多只会有一个最终不会回到start，该分支存到branch里，其它肯定都为环加入trunk中
            List<String> notRing = null;
            while (!desQueue.isEmpty()) {
                String cur = desQueue.poll();
                List<String> ring = new ArrayList<>();
                helper(startDesMap, ring, cur, start);
                if (ring.get(ring.size() - 1).equals(start)) {
                    trunk.addAll(ring);
                } else {
                    notRing = ring;
                }
            }
            if (notRing != null) {
                trunk.addAll(notRing);
            }
        }
    }*/

    public static void main(String[] args) {
        FindItinerary findItinerary = new FindItinerary();
        String input = "[[\"AXA\",\"ADL\"],[\"BNE\",\"AXA\"],[\"EZE\",\"OOL\"],[\"EZE\",\"CBR\"],[\"EZE\",\"AXA\"],[\"ADL\",\"AUA\"],[\"TIA\",\"MEL\"],[\"LST\",\"CBR\"],[\"MEL\",\"EZE\"],[\"LST\",\"AXA\"],[\"CNS\",\"ANU\"],[\"JFK\",\"ADL\"],[\"CBR\",\"JFK\"],[\"TIA\",\"ADL\"],[\"EZE\",\"OOL\"],[\"CNS\",\"MEL\"],[\"ADL\",\"TIA\"],[\"AXA\",\"LST\"],[\"AXA\",\"JFK\"],[\"DRW\",\"AXA\"],[\"ANU\",\"BIM\"],[\"BNE\",\"PER\"],[\"CNS\",\"MEL\"],[\"AUA\",\"AXA\"],[\"HBA\",\"LST\"],[\"MEL\",\"BNE\"],[\"ANU\",\"AXA\"],[\"AXA\",\"LST\"],[\"DRW\",\"AXA\"],[\"AXA\",\"TIA\"],[\"CNS\",\"JFK\"],[\"AUA\",\"TIA\"],[\"CNS\",\"ADL\"],[\"TIA\",\"JFK\"],[\"JFK\",\"ANU\"],[\"TIA\",\"AUA\"],[\"OOL\",\"PER\"],[\"OOL\",\"AUA\"],[\"AXA\",\"LST\"],[\"JFK\",\"BNE\"],[\"MEL\",\"DRW\"],[\"TIA\",\"CNS\"],[\"TIA\",\"EZE\"],[\"PER\",\"AUA\"],[\"OOL\",\"LST\"],[\"ADL\",\"BNE\"],[\"LST\",\"AUA\"],[\"EZE\",\"ADL\"],[\"LST\",\"HBA\"],[\"BNE\",\"ANU\"],[\"OOL\",\"EZE\"],[\"CNS\",\"TIA\"],[\"TIA\",\"HBA\"],[\"AUA\",\"EZE\"],[\"EZE\",\"AUA\"],[\"ADL\",\"EZE\"],[\"JFK\",\"AUA\"],[\"CBR\",\"CNS\"],[\"AUA\",\"LST\"],[\"AUA\",\"OOL\"],[\"ADL\",\"DRW\"],[\"HBA\",\"DRW\"],[\"AUA\",\"JFK\"],[\"LST\",\"AUA\"],[\"LST\",\"TIA\"],[\"LST\",\"JFK\"],[\"AUA\",\"AXA\"],[\"ANU\",\"LST\"],[\"AXA\",\"CNS\"],[\"AXA\",\"EZE\"],[\"JFK\",\"EZE\"],[\"PER\",\"ADL\"],[\"ADL\",\"LST\"],[\"BNE\",\"CNS\"],[\"ADL\",\"JFK\"],[\"AXA\",\"BNE\"],[\"JFK\",\"PER\"],[\"DRW\",\"OOL\"],[\"JFK\",\"TIA\"],[\"LST\",\"AXA\"],[\"CBR\",\"CNS\"],[\"EZE\",\"CBR\"],[\"LST\",\"BNE\"],[\"ANU\",\"MEL\"],[\"PER\",\"TIA\"],[\"AUA\",\"ADL\"],[\"JFK\",\"TIA\"],[\"BNE\",\"CNS\"],[\"CNS\",\"LST\"],[\"MEL\",\"ANU\"],[\"TIA\",\"CNS\"]]";
        List<List<String>> tickets = getTickets(input);
        List<String> res = findItinerary.findItinerary(tickets);
        System.out.println(res);
    }

    private static List<List<String>> getTickets(String sample) {
        sample = sample.replace(" ", "");
        // 去开头和结尾的[],按逗号分割成多个["JFK","MUC"]
        String[] subArray = sample.substring(2, sample.length() - 2).split("],\\[");
        List<List<String>> tickets = new ArrayList<>(subArray.length);
        for (String sub : subArray) {
            String[] location = sub.substring(1, sub.length() - 1).split("\",\"");
            tickets.add(Arrays.asList(location[0], location[1]));
        }
        return tickets;
    }
}
