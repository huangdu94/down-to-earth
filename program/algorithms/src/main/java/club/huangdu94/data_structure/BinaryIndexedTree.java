package club.huangdu94.data_structure;

/**
 * Binary Indexed Trees 树状数组
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/4 10:52
 */
public class BinaryIndexedTree {
    // 树状数组
    private int[] trees;
    // 原数组长度
    private final int length;

    public BinaryIndexedTree(int len) {
        this.length = len;
        init();
    }

    /**
     * 初始化树状数组
     * 数组长度为原数组长度加一即可，因为index=0我们不使用
     */
    private void init() {
        trees = new int[length + 1];
    }

    /**
     * 树状数组核心
     * 这里利用的负数的存储特性，负数是以补码存储的，对于整数运算 x&(-x)有
     * 1. 当x为0时，即 0 & 0，结果为0；
     * 2. 当x为奇数时，最后一个比特位为1，取反加1没有进位，故x和-x除最后一位外前面的位正好相反，按位与结果为0。结果为1。
     * 3. 当x为偶数，且为2的m次方时，x的二进制表示中只有一位是1（从右往左的第m+1位），其右边有m位0，故x取反加1后，从右到左第有m个0，第m+1位及其左边全是1。这样，x& (-x) 得到的就是x。
     * 4. 当x为偶数，却不为2的m次方的形式时，可以写作x= y * (2^k)。其中，y的最低位为1。实际上就是把x用一个奇数左移k位来表示。这时，x的二进制表示最右边有k个0，从右往左第k+1位为1。当对x取反时，最右边的k位0变成1，第k+1位变为0；再加1，最右边的k位就又变成了0，第k+1位因为进位的关系变成了1。左边的位因为没有进位，正好和x原来对应的位上的值相反。二者按位与，得到：第k+1位上为1，左边右边都为0。结果为2^k。
     * 总结一下：x&(-x)，当x为0时结果为0；x为奇数时，结果为1；x为偶数时，结果为x中2的最大次方的因子。
     */
    private int lowBit(int x) {
        return x & (-x);
    }

    /**
     * 树状数组性质
     * 设节点编号为x，那么包含该节点的元素如下
     * A[i] 包含于 C[i + 2^k(i)]、C[(i + 2^k) + 2^k(i+2^k)]...
     * 其中k(i)为i二进制末尾0的个数
     * 树状数组包含pos的地方都增加value值
     *
     * @param pos   原数组index+1
     * @param value 需要更新的值
     */
    public void update(int pos, int value) {
        while (pos <= length) {
            trees[pos] += value;
            pos += lowBit(pos);
        }
    }

    /**
     * 树状数组包含pos的地方都增加1
     *
     * @param pos 原数组index+1
     */
    public void update(int pos) {
        update(pos, 1);
    }

    /**
     * 树状数组性质
     * 设节点编号为x，那么这个节点管辖的区间为2^k（其中k为x二进制末尾0的个数）个元素。
     * 因为这个区间最后一个元素必然为Ax：
     * 所以很明显：Cx = A(x – 2^k + 1) + A(x – 2^k + 2) + ... + Ax
     *
     * @param pos 结束位置(原数组index+1)
     * @return 原数组中1至pos位置(index + 1)元素之和
     */
    public int query(int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += trees[pos];
            pos -= lowBit(pos);
        }
        return sum;
    }

    /**
     * 求原数组中start至end位置(index + 1)元素之和
     *
     * @param start 开始位置(原数组index+1)
     * @param end   结束位置(原数组index+1)
     * @return 原数组中start至end位置(index + 1)元素之和
     */
    public int query(int start, int end) {
        return query(end) - query(start - 1);
    }
}