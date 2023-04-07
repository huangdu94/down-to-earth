class Animal:
    """动物父类"""

    def __init__(self, name, color, age):
        """构造方法"""
        self.name = name
        self.color = color
        self.age = age

    def to_string(self):
        """展示类中的变量值的方法"""
        return "name: " + self.name + ", color: " + self.color + ", age: " + str(self.age) + "."


class Cat(Animal):
    """猫子类，继承动物父类"""

    def __init__(self, name, color, age):
        """构造方法"""
        super().__init__(name, color, age)

    def sound(self):
        """叫声"""
        return self.name + " sound miao."

    def eat(self):
        """吃"""
        return self.name + " eat fish."

    def to_string(self):
        return "[cat] " + super().to_string()
