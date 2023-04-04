from functools import cache
from math import inf
from typing import List


class Solution:
    def coinChange2(self, coins: List[int], amount: int) -> int:
        n = len(coins)

        @cache
        def dfs(i, c):
            if i < 0:
                return 0 if c == 0 else inf
            if c < coins[i]:
                return dfs(i - 1, c)
            return min(dfs(i - 1, c), dfs(i, c - coins[i]) + 1)

        ans = dfs(n - 1, amount)
        return -1 if ans == inf else ans

    def coinChange3(self, coins: List[int], amount: int) -> int:
        n = len(coins)
        dp = [[inf] * (amount + 1) for _ in range(n + 1)]
        dp[0][0] = 0
        for i, coin in enumerate(coins):
            for c in range(amount + 1):
                if c < coin:
                    dp[i + 1][c] = dp[i][c]
                else:
                    dp[i + 1][c] = min(dp[i][c], dp[i + 1][c - coin] + 1)
        return dp[n][amount] if dp[n][amount] < inf else -1

    def coinChange4(self, coins: List[int], amount: int) -> int:
        n = len(coins)
        dp = [[inf] * (amount + 1) for _ in range(2)]
        dp[0][0] = 0
        for i, coin in enumerate(coins):
            for c in range(amount + 1):
                if c < coin:
                    dp[(i + 1) % 2][c] = dp[i % 2][c]
                else:
                    dp[(i + 1) % 2][c] = min(dp[i % 2][c], dp[(i + 1) % 2][c - coin] + 1)
        return dp[n % 2][amount] if dp[n % 2][amount] < inf else -1

    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [inf] * (amount + 1)
        dp[0] = 0
        for coin in coins:
            for c in range(coin, amount + 1):
                dp[c] = min(dp[c], dp[c - coin] + 1)
        return dp[amount] if dp[amount] < inf else -1
