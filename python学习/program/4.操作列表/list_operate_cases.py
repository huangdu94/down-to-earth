# 列表的for循环遍历
collect = ['1', '2', '3', '4']
for num in collect:
    print(num)
    print(num + '.')
print('done!')
# 创建数字列表，以及数字列表的一些操作
for num in range(1, 11, 1):
    print(num)
nums = list(range(1, 11))
total = sum(nums)
minNum = min(nums)
maxNum = max(nums)
print('total:' + str(total) + ',min:' + str(minNum) + ',max:' + str(maxNum))
# 创建数字列表的其它方式
nums = []
for num in range(1, 11):
    nums.append(num ** 2)
print(nums)
nums2 = [num ** 2 for num in range(1, 11)]
print(nums)
print(sum(range(1, 1000000)))
# 建立切片，以及列表复制
nums = [num ** 3 for num in range(1, 11)]
print(nums)
print(nums[0:2])
print(nums[:3])
print(nums[1:])
print(nums[-3:])
nums2 = nums[:]
nums2[0] = -1
print(nums)
print(nums2)
# 元组的概念，创建后，里面的值就不能单独修改，但是元组变量可以重新赋值
d = (1, 10)
for num in d:
    print(num)
print(d[0])
# d[0]=1
d = (10, 1)
print(d)
