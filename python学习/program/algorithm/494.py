from functools import cache
from typing import List


class Solution:
    """
    1. p选正号的数的和
    2. s所有数组的总和
    3. s-p选负号的数的和
    => p-(s-p)==t
    => 2p==s+t
    => p==(s+t)/2
    """

    def findTargetSumWays2(self, nums: List[int], target: int) -> int:
        n, s = len(nums), sum(nums)
        target += s
        if target < 0 or target % 2 != 0:
            return 0
        target //= 2

        @cache
        def dfs(i, c):
            if i < 0:
                return 1 if c == 0 else 0
            if c < nums[i]:
                return dfs(i - 1, c)
            return dfs(i - 1, c) + dfs(i - 1, c - nums[i])

        return dfs(n - 1, target)

    def findTargetSumWays3(self, nums: List[int], target: int) -> int:
        n, s = len(nums), sum(nums)
        target += s
        if target < 0 or target % 2 != 0:
            return 0
        target //= 2
        dp = [[0] * (target + 1) for _ in range(n + 1)]
        dp[0][0] = 1
        for i, x in enumerate(nums):
            for c in range(target + 1):
                if c < x:
                    dp[i + 1][c] = dp[i][c]
                else:
                    dp[i + 1][c] = dp[i][c] + dp[i][c - x]
        return dp[n][target]

    def findTargetSumWays4(self, nums: List[int], target: int) -> int:
        n, s = len(nums), sum(nums)
        target += s
        if target < 0 or target % 2 != 0:
            return 0
        target //= 2
        dp = [[0] * (target + 1) for _ in range(2)]
        dp[0][0] = 1
        for i, x in enumerate(nums):
            for c in range(target + 1):
                if c < x:
                    dp[(i + 1) % 2][c] = dp[i % 2][c]
                else:
                    dp[(i + 1) % 2][c] = dp[i % 2][c] + dp[i % 2][c - x]
        return dp[n % 2][target]

    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        n, s = len(nums), sum(nums)
        target += s
        if target < 0 or target % 2 != 0:
            return 0
        target //= 2
        dp = [[0] * (target + 1)]
        dp[0] = 1
        for x in nums:
            for c in range(target, x - 1, -1):
                dp[c] = dp[c] + dp[c - x]
        return dp[target]
