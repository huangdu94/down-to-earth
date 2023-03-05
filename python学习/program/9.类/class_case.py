# 类名使用驼峰命名法，方法名使用小写字母、下划线
class Cat:
    """这是一个类，如果没有继承的类的话，括号可以省略"""

    def __init__(self, name, color, age):
        """这是一个构造方法"""
        self.name = name
        self.color = color
        self.age = age

    def to_string(self):
        """展示类中的变量值的方法"""
        return "name: " + self.name + ", color: " + self.color + ", age: " + str(self.age) + "."


# 创建实例
cat = Cat("hello", "red", 10)

# 访问属性
print(cat.name)
print(cat.color)
print(cat.age)

# 调用方法
print(cat.to_string())
