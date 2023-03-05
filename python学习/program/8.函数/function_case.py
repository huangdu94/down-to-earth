def say_hello():
    """这是一个无参函数"""
    print("hello.")


def say_hello2(name):
    """这是一个有参函数（参数也可以是列表、字典）"""
    print("hello " + name + ".")


def subtraction(a, b):
    """有两个参数的函数"""
    print("a - b = " + str(a - b))


def subtraction2(a, b=1):
    """有两个参数，且有一个提供了默认值，提供了默认值的参数必须放在没有提供默认值的参数后面"""
    print("a - b = " + str(a - b))


# 无参函数调用
say_hello()
# 有参函数调用
say_hello2("yiyun")

# 位置实参
subtraction(1, 2)
subtraction(2, 1)
# 关键字实参
subtraction(a=3, b=10)
subtraction(b=10, a=3)
# 等效函数调用
subtraction2(10)
subtraction2(10, 1)
subtraction2(a=10, b=1)
subtraction2(b=1, a=10)
subtraction2(a=10)


def add(a, b):
    """这是一有返回值的函数（也可以返回字典）"""
    return a + b


# 向函数传递列表的副本可以避免原列表被修改
print(add(1, 2))


def fuc(number, *params):
    """传递任意数量的实参"""
    print(number)
    # params是一个元组
    for param in params:
        print(param)


def fuc1(number, *params):
    """传递任意数量的实参"""
    print(number)
    # params是一个元组
    for param in params:
        print(param)


def fuc2(*params, number):
    """传递任意数量的实参"""
    print(number)
    # params是一个元组
    for param in params:
        print(param)


fuc1(1, "hello", "world")

# Positional argument after keyword argument
# fuc1(number=1, "hello", "world")

# fuc1() got multiple values for argument 'number'
# fuc1("hello", "world", number=1)

# fuc2() missing 1 required keyword-only argument: 'number'
# fuc2(1, "hello", "world")

fuc2("hello", "world", number=2)


def fuc3(a, **params):
    """接收任意数量的关键字实参"""
    print(a)
    print(params)


fuc3(1, c=2, b=3)
fuc3(a=1, c=2, b=3)
# fuc3() got multiple values for argument 'a'
# fuc3(1, a=1, c=2, b=3)
