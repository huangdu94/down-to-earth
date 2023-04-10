from typing import List


class Solution:
    def commonFactors(self, a: int, b: int) -> int:
        ans = 0
        for x in range(1, min(a, b) + 1):
            if a % x == 0 and b % x == 0:
                ans += 1
        return ans

    def baseNeg2(self, n: int) -> str:
        if n == 0:
            return "0"
        ans = ""
        while n != 0:
            n, r = n // -2, n % -2
            if r < 0:
                n += 1
            ans = ("0" if r == 0 else "1") + ans
        return ans

    def checkDistances(self, s: str, distance: List[int]) -> bool:
        mapping = [0] * 26
        for i, ch in enumerate(s):
            idx = ord(ch) - ord('a')
            if mapping[idx] == 0:
                mapping[idx] = i + 1
            elif i - mapping[idx] != distance[idx]:
                return False
        return True


solution = Solution()
print(solution.baseNeg2(4))
print(ord('a'))
