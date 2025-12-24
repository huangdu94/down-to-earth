class Solution:
    def minimumBoxes(self, apple: List[int], capacity: List[int]) -> int:
        ans = 0
        sum = 0
        capacity.sort(reverse=True)
        for count in apple:
            sum += count
        for count in capacity:
            ans += 1
            sum -= count
            if sum <= 0:
                return ans
        return -1