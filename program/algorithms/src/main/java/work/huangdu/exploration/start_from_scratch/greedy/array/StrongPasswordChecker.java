package work.huangdu.exploration.start_from_scratch.greedy.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 420. 强密码检验器
 * 一个强密码应满足以下所有条件：
 * 由至少6个，至多20个字符组成。
 * 至少包含一个小写字母，一个大写字母，和一个数字。
 * 同一字符不能连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 是可以的)。
 * 编写函数 strongPasswordChecker(s)，s 代表输入字符串，如果 s 已经符合强密码条件，则返回0；否则返回要将 s 修改为满足强密码条件的字符串所需要进行修改的最小步数。
 * 插入、删除、替换任一字符都算作一次修改。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/16 0:24
 */
public class StrongPasswordChecker {
    public int strongPasswordChecker(String password) {
        int len = password.length(), kind = 0;
        boolean number, lower, upper;
        number = lower = upper = false;
        List<Integer> continuousList = new ArrayList<>();
        char[] pw = password.toCharArray();
        for (int i = 0; i < len; i++) {
            char c = pw[i];
            if (!number && Character.isDigit(c)) {
                number = true;
                kind++;
            } else if (!lower && Character.isLowerCase(c)) {
                lower = true;
                kind++;
            } else if (!upper && Character.isUpperCase(c)) {
                upper = true;
                kind++;
            }
            int continuous = 1;
            while (i + 1 < len && pw[i + 1] == c) {
                i++;
                continuous++;
            }
            if (continuous >= 3) {
                continuousList.add(continuous);
            }
        }
        // 满足要求，返回0
        if (len >= 6 && len <= 20 && kind == 3 && continuousList.isEmpty()) {
            return 0;
        }
        // 需要增加的字符种类，可以通过insert或update
        int needChangeByKind = 3 - kind;
        if (len >= 6 && len <= 20) {
            // 数量满足要求只需要update即可
            // needChangeByContinuous=continuous/3
            int needChangeByContinuous = 0;
            for (int continuous : continuousList) {
                needChangeByContinuous += continuous / 3;
            }
            return Math.max(needChangeByContinuous, needChangeByKind);
        } else if (len < 6) {
            // 需要insert
            int needInsert = 6 - len;
            // 1. 如果需要插入2个或2个以上，仅考虑插入的数量就可以满足密码要求
            // 2. 如果只需要插入1个，仅一种情况就是五位密码都相同，此时需要insert一次，update一次，其它情况均只需要操作needInsert次
            if (needInsert == 1 && !continuousList.isEmpty() && continuousList.get(0) == 5) {
                return 2;
            }
            return needInsert;
        } else {
            // 需要delete
            int needDelete = len - 20;
            // 1.仅通过删除就可以解决多个字符连续问题，则直接返回needDelete+needChangeByKind
            int needDeleteByContinuous = 0;
            for (int continuous : continuousList) {
                needDeleteByContinuous += (continuous - 2);
            }
            if (needDelete >= needDeleteByContinuous) {
                return needDelete + needChangeByKind;
            }
            // 2.仅通过删除无法解决多个字符连续的问题，那么就需要删除连续字符，使得需要替换的字符数量最少
            // 2.1 先考虑删除一个连续的字符，就可以使得需要替换的字符减少一个的
            int remain = needDelete;
            for (int i = 0; i < continuousList.size(); i++) {
                int continuous = continuousList.get(i);
                if (continuous % 3 == 0) {
                    continuousList.set(i, continuous - 1);
                    if (--remain == 0) {
                        break;
                    }
                }
            }
            // 2.2 再考虑删除两个连续的字符，就可以使得需要替换的字符减少一个的
            if (remain > 1) {
                for (int i = 0; i < continuousList.size(); i++) {
                    int continuous = continuousList.get(i);
                    if ((continuous - 1) % 3 == 0) {
                        continuousList.set(i, continuous - 2);
                        if ((remain -= 2) < 2) {
                            break;
                        }
                    }
                }
            }
            // 2.3 再考虑删除三个连续的字符的情况
            while (remain > 2) {
                for (int i = 0; i < continuousList.size(); i++) {
                    int continuous = continuousList.get(i);
                    if (continuous > 2) {
                        continuousList.set(i, continuous - 3);
                        if ((remain -= 3) < 3) {
                            break;
                        }
                    }
                }
            }
            // 经过以上删除步骤后，remain可能为0，1，2但这都不影响结果
            int needChangeByContinuous = 0;
            for (int continuous : continuousList) {
                needChangeByContinuous += continuous / 3;
            }
            return needDelete + Math.max(needChangeByContinuous, needChangeByKind);
        }
    }

    public static void main(String[] args) {
        StrongPasswordChecker spc = new StrongPasswordChecker();
        System.out.println(spc.strongPasswordChecker("aaaaaaaabbbbbbbaaaaaaaaaaaaaaaaaaaaa"));
    }
}
