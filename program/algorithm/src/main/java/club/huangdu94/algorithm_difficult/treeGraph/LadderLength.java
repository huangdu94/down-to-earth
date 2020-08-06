package club.huangdu94.algorithm_difficult.treeGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 单词接龙
 * 给定两个单词（beginWord和 endWord）和一个字典，找到从beginWord 到endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出:0
 * 解释:endWord "cog" 不在字典中，所以无法进行转换。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/4 12:06
 */
public class LadderLength {
    // 结果
    private int result = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        this.transform(1, beginWord, endWord, wordList);
        return result;
    }

    /**
     * 递归寻找最短转换路径
     *
     * @param pathLen        当前转换路径长度
     * @param curWord        当前单词
     * @param endWord        目标单词
     * @param remainWordList 当前遗留单词列表
     */
    private void transform(int pathLen, String curWord, String endWord, List<String> remainWordList) {
        // 0. 路径长度加一
        pathLen += 1;
        // 1. 寻找当前单词可以转换的单词列表
        List<String> filtered = this.getFilterList(curWord, remainWordList);
        // 2. 查看此过滤后的单词列表是否包含目标的endWord，如果包含则记录result值，此分支递归结束
        for (String word : filtered)
            if (word.equals(endWord)) {
                if (result == 0 || pathLen < result)
                    result = pathLen;
                // 该轮递归结束，恢复wordList为进入该层时的状态
                remainWordList.addAll(filtered);
                return;
            }
        // 3. 不包含的话，则递归每一个过滤后单词列表
        for (String word : filtered)
            this.transform(pathLen, word, endWord, remainWordList);
        // 4. 该轮递归结束，恢复wordList为进入该层时的状态
        remainWordList.addAll(filtered);
    }

    /**
     * 从list中找到和word仅有一个字母不相同的字母并返回新的list
     *
     * @param curWord        当前单词
     * @param remainWordList 剩余单词列表
     * @return 满足过滤条件的list
     */
    private List<String> getFilterList(String curWord, List<String> remainWordList) {
        List<String> filtered = new ArrayList<>();
        Iterator<String> iterator = remainWordList.iterator();
        while (iterator.hasNext()) {
            String target = iterator.next();
            if (this.filter(curWord, target)) {
                filtered.add(target);
                iterator.remove();
            }
        }
        return filtered;
    }

    /**
     * 过滤器
     *
     * @param curWord 当前单词
     * @param target  目标单词
     * @return 查看当前单词和目标单词是否只有一个字母不一样
     */
    private boolean filter(String curWord, String target) {
        int count = 0;
        for (int i = 0; i < curWord.length(); i++)
            if (curWord.charAt(i) != target.charAt(i))
                if (++count > 1)
                    return false;
        return count == 1;
    }

    public static void main(String[] args) {
        LadderLength ladderLength = new LadderLength();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        int r = ladderLength.ladderLength(beginWord, endWord, wordList);
        System.out.println(r);
    }
}
