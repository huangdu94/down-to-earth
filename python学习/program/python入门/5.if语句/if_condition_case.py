var = "abc"
# 字符串相等
print(var == "abc")
# 字符串不相等
print(var != "ABC")
# 字符串相等（大小写不敏感）
print(var.upper() == "ABC")
print(var)

var = 1
# 数字相等
print(var == 1)
# 数字不相等
print(var != 2)
# 数字比较
print(var > 0)
print(var >= 0)
print(var < 2)
print(var <= 2)
# and
print(var == 1 and var > 0)
# or
print(var == 1 or var < 2)

var = ["ab", "cd", "de", "ef"]
# 值在列表中
print("ab" in var)
# 值不在列表中
print("ab" not in var)

# 布尔常量
print(True)
print(False)
