package club.huangdu94.exploration.advanced_algorithms.tree_graph;

import java.util.*;

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
    // 学习点:
    // 1. 单词预处理
    // 2. 双向广度搜索
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        // 单词预处理:
        // dog -> 分别以*og、d*g、do*作为key，dog作为valueList中的一个存储在map中
        // 用于快速查找到只相差一个字母的单词列表
        int wordLen = beginWord.length();
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < wordLen; i++) {
                String key = word.substring(0, i) + '*' + word.substring(i + 1, wordLen);
                List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
                list.add(word);
            }
        }
        int len = 1;
        // 已经使用过的单词集合
        Set<String> visited = new HashSet<>(wordList.size());
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.remove();
                for (int k = 0; k < wordLen; k++) {
                    String key = word.substring(0, k) + '*' + word.substring(k + 1, wordLen);
                    for (String target : map.getOrDefault(key, new ArrayList<>())) {
                        if (!visited.contains(target)) {
                            if (target.equals(endWord)) return len;
                            queue.offer(target);
                            visited.add(target);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        boolean flag = true;
        for (String word : wordList) {
            if (word.equals(endWord)) {
                flag = false;
                break;
            }
        }
        if (flag) return 0;
        int len = 1;
        // 已经使用过的单词集合
        Set<String> visited = new HashSet<>(wordList.size());
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.remove();
                for (String target : wordList) {
                    if (!visited.contains(target) && filter(word, target)) {
                        if (target.equals(endWord)) return len;
                        queue.offer(target);
                        visited.add(target);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 过滤器
     *
     * @param curWord 当前单词
     * @param target  目标单词
     * @return 是否满足过滤条件(只有一个字母不一样则满足条件)
     */
    private boolean filter(String curWord, String target) {
        int count = 0;
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != target.charAt(i) && ++count > 1) return false;
        }
        return count == 1;
    }

//    // 结果
//    private int result = 0;
//
//    public int ladderLength_old(String beginWord, String endWord, List<String> wordList) {
//        // 如果wordList中根本就没有beginWord则不可能转换，无需递归
//        boolean flag = false;
//        for (String word : wordList)
//            if (word.equals(endWord)) {
//                flag = true;
//                break;
//            }
//        // 设置一个黑名单用于提前剪枝
//        Set<String> blackSet = new HashSet<>();
//        if (flag) this.transform(1, beginWord, endWord, wordList, blackSet);
//        return result;
//    }
//
//    /**
//     * 递归寻找最短转换路径
//     *
//     * @param pathLen        当前转换路径长度
//     * @param curWord        当前单词
//     * @param endWord        目标单词
//     * @param remainWordList 当前遗留单词列表
//     * @param blackSet       黑名单
//     * @return false表示该分支查找失败，单词加入黑名单下次遇到直接剪枝
//     */
//    private boolean transform(int pathLen, String curWord, String endWord, List<String> remainWordList, Set<String> blackSet) {
//        // 0. 路径长度加一
//        pathLen += 1;
//        // 1. 提前剪枝，抛弃没有意义的分支
//        if (blackSet.contains(curWord)) return false;
//        if (result != 0 && result <= pathLen) return true;
//        // 2. 寻找当前单词可以转换的单词列表
//        List<String> filtered = new ArrayList<>();
//        // 3. 查看此过滤后的单词列表是否包含目标的endWord，如果包含则记录result值，此分支递归结束
//        if (this.getFilterList(curWord, endWord, remainWordList, filtered)) {
//            if (result == 0 || pathLen < result)
//                result = pathLen;
//            // 该轮递归结束，恢复wordList为进入该层时的状态
//            remainWordList.addAll(filtered);
//            return true;
//        }
//        // 4. 不包含的话，则递归每一个过滤后单词列表
//        boolean result = false;
//        for (String word : filtered)
//            if (!this.transform(pathLen, word, endWord, remainWordList, blackSet)) blackSet.add(word);
//            else result = true;
//        // 5. 该轮递归结束，恢复wordList为进入该层时的状态
//        remainWordList.addAll(filtered);
//        return result;
//    }
//
//    /**
//     * 从list中找到和word仅有一个字母不相同的字母并返回新的list
//     *
//     * @param curWord        当前单词
//     * @param endWord        结束单词
//     * @param remainWordList 剩余单词列表
//     * @param filtered       满足过滤条件的list
//     * @return 是否找到endWord，true的话表示找到
//     */
//    private boolean getFilterList(String curWord, String endWord, List<String> remainWordList, List<String> filtered) {
//        Iterator<String> iterator = remainWordList.iterator();
//        while (iterator.hasNext()) {
//            String target = iterator.next();
//            if (this.filter(curWord, target)) {
//                if (target.equals(endWord))
//                    return true;
//                filtered.add(target);
//                iterator.remove();
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        LadderLength ladderLength = new LadderLength();
        /*
        String beginWord = "cet";
        String endWord = "ism";
        List<String> wordList = new ArrayList<>(Arrays.asList("kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"));
        */
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int res = ladderLength.ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }
}
