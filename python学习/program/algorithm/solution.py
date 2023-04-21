import bisect
import math
from functools import cache
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


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

    def nextLargerNodes(self, head: Optional[ListNode]) -> List[int]:
        ans = []
        stack = []
        while head:
            while stack and ans[stack[-1]] < head.val:
                ans[stack.pop()] = head.val
            stack.append(len(ans))
            ans.append(head.val)
            head = head.next
        for idx in stack:
            ans[idx] = 0
        return ans

    def mostFrequentEven(self, nums: List[int]) -> int:
        freq = {}
        ans = -1
        for num in nums:
            if (num & 1) == 0:
                if num not in freq:
                    freq[num] = 0
                freq[num] += 1
                if ans == -1 or freq[num] > freq[ans] or freq[num] == freq[ans] and num < ans:
                    ans = num
        return ans

    def makeArrayIncreasing(self, arr1: List[int], arr2: List[int]) -> int:
        n = len(arr1)
        arr2 = sorted(set(arr2))

        @cache
        def dfs(i, pre):
            if i < 0:
                return 0
            idx = bisect.bisect_left(arr2, pre) - 1
            if arr1[i] >= pre:
                return math.inf if idx == -1 else dfs(i - 1, arr2[idx]) + 1
            else:
                return dfs(i - 1, arr1[i]) if idx == -1 else min(dfs(i - 1, arr1[i]), dfs(i - 1, arr2[idx]) + 1)

        ans = dfs(n - 1, math.inf)
        return ans if ans < math.inf else -1


solution = Solution()
print(solution.makeArrayIncreasing([7, 6, 3, 0, 3], [9]))
