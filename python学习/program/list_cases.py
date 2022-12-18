# 创建列表
collect=['linux','windows','mac os','android','ios']
print(collect)
# 列表访问
print(collect[0])
print(collect[-1])
# 指定索引元素修改
collect[0]='linux2'
print(collect)
# 列表末尾添加元素
collect.append('new')
print(collect)
# 列表指定位置插入元素
collect.insert(0,'first')
print(collect)
# 删除指定位置元素
del collect[0]
print(collect)
# 弹出列表末尾元素
print(collect.pop())
print(collect)
# 弹出列表指定位置元素
print(collect.pop(0))
print(collect)
collect.insert(0,'linux')
print(collect)
# 删除指定值的元素（如果该值不存在会报错）
collect.remove('ios')
print(collect)
# 排序
collect.sort()
print(collect)
# 反着排序
collect.sort(reverse=True)
print(collect)
collect=['linux','windows','mac os','android','ios']
# 排序（但不影响列表本来的顺序，只是显示一下）
print(sorted(collect))
print(collect)
print(sorted(collect,reverse=True))
print(collect)
# 反转列表
collect.reverse()
print(collect)
# 查看列表长度
print(len(collect))
