package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 12. 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/28 18:54
 */
public class IntToRoman {
    public String intToRoman(int num) {
        String kiloBit = "", hundreds = "", tens = "", units = "";
        int i = 0;
        while (num != 0) {
            switch (i++) {
                case 0:
                    units = units(num % 10);
                    break;
                case 1:
                    tens = tens(num % 10);
                    break;
                case 2:
                    hundreds = hundreds(num % 10);
                    break;
                default:
                    kiloBit = kiloBit(num);
            }
            num /= 10;
        }
        return kiloBit.concat(hundreds).concat(tens).concat(units);
    }

    private String units(int n) {
        switch (n) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            default:
                return "";
        }
    }

    private String tens(int n) {
        switch (n) {
            case 1:
                return "X";
            case 2:
                return "XX";
            case 3:
                return "XXX";
            case 4:
                return "XL";
            case 5:
                return "L";
            case 6:
                return "LX";
            case 7:
                return "LXX";
            case 8:
                return "LXXX";
            case 9:
                return "XC";
            default:
                return "";
        }
    }

    private String hundreds(int n) {
        switch (n) {
            case 1:
                return "C";
            case 2:
                return "CC";
            case 3:
                return "CCC";
            case 4:
                return "CD";
            case 5:
                return "D";
            case 6:
                return "DC";
            case 7:
                return "DCC";
            case 8:
                return "DCCC";
            case 9:
                return "CM";
            default:
                return "";
        }
    }

    private String kiloBit(int n) {
        switch (n) {
            case 1:
                return "M";
            case 2:
                return "MM";
            default:
                return "MMM";
        }
    }
}
