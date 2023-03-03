# 字典
dic = {"number": 1.0, "string": "hello world!", "list": [1, 2], "map": {"a": 1, "b": 2}}
# 访问字典中的值
print(dic["map"])
print(dic["map"]["a"])
print(dic["list"])
print(dic["list"][0])
# 添加键值对
dic["add"] = "add"
print(dic)
# 修改字典中的值
dic["add"] = "update"
print(dic)
# 删除键值对
del dic["add"]
print(dic)

# 遍历所有的键值对
for key, value in dic.items():
    print("key: " + key)
    print("value: " + str(value))
# 遍历所有的键
for key in dic.keys():
    print(key)
# 遍历所有的值
for key in dic.values():
    print(key)
# 对列表进行去重 排序
array = [1, 1, 4, 1, 2, 2, 3]
print(set(array))
print(sorted(array))
# 嵌套：列表中可以存字典，字典中的值可以是列表，字典中的值还可以是字典
