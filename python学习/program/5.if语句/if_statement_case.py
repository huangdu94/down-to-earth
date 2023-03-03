a = 0
# 简单的if语句
if a == 0:
    print("a is zero.")
# if-else语句
if a == 0:
    print("a is zero.")
else:
    print("a is not zero.")
# if-elif-else结构（elif可多个，else可省略）
if a == 0:
    print("a is zero.")
elif a == 1:
    print("a is one.")
else:
    print("a is not zero and a is not one.")
# 确定列表不是空的（Python将在列表至少包含一个元素时返回True，并在列表为空时返回False）
var = []
if var:
    print("var is not empty.")
else:
    print("var is empty.")
var.append("a")
if var:
    print("var is not empty.")
else:
    print("var is empty.")
# 确定字符串不是空的（Python将非空字符串解读为True）
var = ""
if var:
    print("var is not empty.")
else:
    print("var is empty.")
var = "hello"
if var:
    print("var is not empty.")
else:
    print("var is empty.")
