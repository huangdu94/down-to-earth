package work.huangdu.question_bank.medium;

import java.util.*;

/**
 * 721. 账户合并
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * 示例 1：
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * 提示：
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/18
 */
public class AccountsMerge {
    // 最优解 哈希表+并查集
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Set<String>>> nameEmailSetListMap = new HashMap<>();
        for (List<String> account : accounts) {
            int size = account.size();
            if (size == 1) continue;
            String name = account.get(0);
            Set<String> emailSet = new HashSet<>(account.size() - 1);
            for (int i = 1; i < size; i++) {
                emailSet.add(account.get(i));
            }
            List<Set<String>> emailSetList = nameEmailSetListMap.computeIfAbsent(name, k -> new ArrayList<>());
            emailSetList.add(emailSet);
        }
        List<List<String>> result = new ArrayList<>();
        for (String name : nameEmailSetListMap.keySet()) {
            List<Set<String>> emailSetList = nameEmailSetListMap.get(name);
            int size = emailSetList.size(), visitedCount = 0;
            boolean[] visited = new boolean[size];
            for (int i = 0; i < size && visitedCount < size; i++) {
                if (visited[i]) continue;
                Set<String> emailSetI = emailSetList.get(i);
                boolean changeFlag = true;
                while (changeFlag && visitedCount < size) {
                    changeFlag = false;
                    for (int j = i + 1; j < size && visitedCount < size; j++) {
                        if (visited[j]) continue;
                        Set<String> emailSetJ = emailSetList.get(j);
                        if (haveCommon(emailSetI, emailSetJ)) {
                            emailSetI.addAll(emailSetJ);
                            visited[j] = true;
                            visitedCount++;
                            changeFlag = true;
                        }
                    }
                }
                visited[i] = true;
                visitedCount++;
                List<String> account = new ArrayList<>(emailSetI);
                Collections.sort(account);
                account.add(0, name);
                result.add(account);
            }
        }
        return result;
    }

    private boolean haveCommon(Set<String> set1, Set<String> set2) {
        if (set1.size() > set2.size()) return haveCommon(set2, set1);
        for (String email : set1) {
            if (set2.contains(email)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AccountsMerge am = new AccountsMerge();
        List<List<String>> accounts = Arrays.asList(Arrays.asList("John", "John12@m.co", "John16@m.co", "John17@m.co", "John6@m.co", "John9@m.co"), Arrays.asList("John", "John11@m.co", "John1@m.co", "John3@m.co", "John7@m.co", "John8@m.co"), Arrays.asList("John", "John0@m.co", "John10@m.co", "John14@m.co", "John20@m.co", "John5@m.co"));
        //Arrays.asList(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"), Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"), Arrays.asList("Mary", "mary@mail.com"), Arrays.asList("John", "johnnybravo@mail.com"));
        System.out.println(am.accountsMerge(accounts));
    }
}
