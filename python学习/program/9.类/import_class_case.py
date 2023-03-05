# 导入单个类
# from extend_case import Animal
# 从一个模块中导入多个类
# from extend_case import Animal, Cat
# 导入整个模块
import extend_case

# 导入模块中所有的类(不推荐使用)
# from extend_case import *
# 也可以在一个模块中导入另一个模块
# 一些标准库
# 记录键值对添加顺序的字典
# from collections import OrderedDict
# 产生随机数
from random import randint

x = randint(1, 6)
print(x)

animal = extend_case.Animal("hello", "yellow", 10)
print(animal.to_string())
cat = extend_case.Cat("world", "red", 12)
print(cat.to_string())
print(cat.eat())
print(cat.sound())
