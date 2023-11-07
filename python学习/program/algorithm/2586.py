from typing import List


class Solution:
    def vowelStrings(self, words: List[str], left: int, right: int) -> int:
        ans = 0
        set = {'a', 'e', 'i', 'o', 'u'}
        for i in range(left, right + 1):
            word = words[i]
            if word[0] in set and word[-1] in set:
                ans += 1
        return ans


solution = Solution()
print(solution.vowelStrings(["hey", "aeo", "mu", "ooo", "artro"], 1, 4))
