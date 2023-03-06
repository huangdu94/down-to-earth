import unittest


def add(a, b):
    """加法运算"""
    return a + b


def subtract(a, b):
    """减法运算"""
    return a - b


def multiply(a, b):
    """乘法运算"""
    return a * b


def division(a, b):
    """除法运算"""
    return a / b


class TestFunctionCase(unittest.TestCase):
    """function测试类"""

    def test_add_function(self):
        """测试add函数"""
        self.assertEqual(add(1, 1), 2)

    def test_subtract_function(self):
        """测试subtract函数"""
        self.assertEqual(subtract(1, 1), 0)

    def test_multiply_function(self):
        """测试multiply函数"""
        self.assertEqual(multiply(1, 1), 1)

    def test_division_function(self):
        """测试division函数"""
        self.assertEqual(division(1, 1), 1)


unittest.main()
