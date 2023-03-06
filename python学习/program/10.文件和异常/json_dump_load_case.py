import json


def write(filename, content, mode='w'):
    """将content写入filename中，mode支持w或a两种"""
    if mode != 'w' and mode != 'a':
        print("mode param: " + mode + ",is not support.")
    with open(filename, mode) as file_object:
        json.dump(content, file_object)


def read(filename):
    """读取filename内容"""
    try:
        with open(filename, 'r') as file_object:
            return json.load(file_object)
    except FileNotFoundError:
        return None


def text():
    """笔记本小程序"""
    print("Welcome to use text program.")
    while True:
        filename = input("Please input your filename:\n")
        mode = input("Please input your mode(w,a写，其它读):\n")
        if mode == 'w' or mode == 'a':
            content = input("Please input your content:\n")
            write(filename, content, mode)
        else:
            content = read(filename)
            if content:
                print(filename + " content: [" + content + "].")
            else:
                print(filename + " is not exist.")
        flag = input("continue?(Y/N)")
        if flag == 'N':
            break


text()
