package work.huangdu.specific.union_find_set;

import java.util.Random;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/28
 */
public class UnionFindSetTest {
    public static void main(String[] args) {
        // 合并次数m，并查集大小n
        int m = 1000000, n = 10000;
        QuickFindUnionFindSet quickFindUnionFindSet = new QuickFindUnionFindSet(n);
        QuickUnionUnionFindSet quickUnionUnionFindSet = new QuickUnionUnionFindSet(n);
        SizeUnionFindSet sizeUnionFindSet = new SizeUnionFindSet(n);
        RankUnionFindSet rankUnionFindSet = new RankUnionFindSet(n);
        FinalUnionFindSet finalUnionFindSet = new FinalUnionFindSet(n);
        long cost1 = testUnionFindSet(quickFindUnionFindSet, m, n);
        long cost2 = testUnionFindSet(quickUnionUnionFindSet, m, n);
        long cost3 = testUnionFindSet(sizeUnionFindSet, m, n);
        long cost4 = testUnionFindSet(rankUnionFindSet, m, n);
        long cost5 = testUnionFindSet(finalUnionFindSet, m, n);
        //System.out.println(String.format("%s %s %s", cost3, cost4, cost5));
        System.out.println(String.format("%s %s %s %s %s", cost1, cost2, cost3, cost4, cost5));
    }

    private static long testUnionFindSet(AbstractUnionFindSet ufs, int m, int n) {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < m; i++) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            ufs.union(x, y);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
