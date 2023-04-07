# 读取整个文件
with open("file/pi_digits.txt") as file_object:
    print(file_object.read())

# 逐行读取
with open("file/pi_digits.txt") as file_object:
    for line in file_object:
        # 不加rstrip()的话，会多很多空行
        print(line.rstrip())

# 创建一个包含文件各行内容的列表
with open("file/pi_digits.txt") as file_object:
    lines = file_object.readlines()
pi_string = ''
for line in lines:
    pi_string += line.strip()
print(pi_string)
print(len(pi_string))

# 读取大文件
with open("file/pi_million_digits.txt") as file_object:
    lines = file_object.readlines()
pi_string = ''
for line in lines:
    pi_string += line.strip()
print(pi_string[0:50] + "...")
print(len(pi_string))

prompt = "Please input your birthday (eg. 012394):\n"
birthday_string = input(prompt)
if birthday_string in pi_string:
    print("Your birthday in PI.")
else:
    print("Your birthday not in PI.")
