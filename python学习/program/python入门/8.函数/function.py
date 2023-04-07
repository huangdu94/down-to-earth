def show(a, op, b, ans):
    """显示结果"""
    print(str(a) + op + str(b) + "=" + str(ans))


def add(a, b):
    """加法运算"""
    show(a, '+', b, a + b)


def subtract(a, b):
    """减法运算"""
    show(a, '-', b, a - b)


def multiply(a, b):
    """乘法运算"""
    show(a, '×', b, a * b)


def division(a, b):
    """除法运算"""
    show(a, '÷', b, a / b)
