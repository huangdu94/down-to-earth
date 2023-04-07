# ZeroDivisionError
# result = 1 / 0

try:
    result = 1 / 0
except ZeroDivisionError:
    print("you can't division by zero.")

try:
    result = 1 / 1
except ZeroDivisionError:
    print("you can't division by zero.")
else:
    print(result)


# FileNotFoundError
# with open("test.txt") as file_object:
#     print(file_object.read())

def read_file(filename):
    """读取文件内容函数"""
    try:
        with open(filename) as file_object:
            content = file_object.read()
    except FileNotFoundError:
        print(filename + " is not exist.")
    else:
        print(filename + " content:")
        print(content)


def read_file2(filename):
    """读取文件内容函数，失败时候一声不吭"""
    try:
        with open(filename) as file_object:
            content = file_object.read()
    except FileNotFoundError:
        # 失败时跳过
        pass
    else:
        print(filename + " content:")
        print(content)


filenames = ["test.txt", "file/pi_digits.txt", "file/write.txt"]
for name in filenames:
    read_file(name)
    read_file2(name)
