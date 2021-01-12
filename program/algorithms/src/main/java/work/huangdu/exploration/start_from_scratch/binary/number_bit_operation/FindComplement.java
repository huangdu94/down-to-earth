package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 476. 数字的补数
 * 1009. 十进制整数的反码
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 * 示例 1:
 * 输入: 5
 * 输出: 2
 * 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * 示例 2:
 * 输入: 1
 * 输出: 0
 * 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 * 注意:
 * 给定的整数保证在 32 位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/10/14 13:14
 */
public class FindComplement {
    private static final int[] mask = {
            0X00000001,
            0X00000003,
            0X00000007,
            0X0000000F,
            0X0000001F,
            0X0000003F,
            0X0000007F,
            0X000000FF,
            0X000001FF,
            0X000003FF,
            0X000007FF,
            0X00000FFF,
            0X00001FFF,
            0X00003FFF,
            0X00007FFF,
            0X0000FFFF,
            0X0001FFFF,
            0X0003FFFF,
            0X0007FFFF,
            0X000FFFFF,
            0X001FFFFF,
            0X003FFFFF,
            0X007FFFFF,
            0X00FFFFFF,
            0X01FFFFFF,
            0X03FFFFFF,
            0X07FFFFFF,
            0X0FFFFFFF,
            0X1FFFFFFF,
            0X3FFFFFFF,
            0X7FFFFFFF
    };

    public int findComplement(int num) {
        if (num == 0) return 1;
        int i = 0;
        for (int m : mask) {
            if (num > m) {
                i++;
            } else {
                break;
            }
        }
        return (~num) & mask[i];
    }

    public int findComplement2(int num) {
        if (num == 0) return 1;
        int i = 0;
        for (int m : mask) {
            if (num > m) {
                i++;
            } else {
                break;
            }
        }
        return num ^ mask[i];
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(134));
        System.out.println(Integer.toBinaryString(255));
        System.out.println(Integer.toBinaryString(511));
        FindComplement complement = new FindComplement();
        System.out.println(complement.findComplement(134));
    }
}
