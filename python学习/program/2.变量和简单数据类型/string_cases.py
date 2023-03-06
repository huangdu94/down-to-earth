name = 'yi yun'
print('Hello ' + name + ', would you like to learn some Python today?')
print(name.title())
print(name.upper())
print(name.lower())
print("'")
print('"')
word = ' word '
print(word.lstrip() + '.')
print(word.rstrip() + '.')
print(word.strip() + '.')
print('1\t2\t3\t4\n5\t6\t7\t8')

# 字符串赋值的时候最前面加一个r，则后面需要转义的字符自动转义
path = r'\a\b\c\d'
print(path)
