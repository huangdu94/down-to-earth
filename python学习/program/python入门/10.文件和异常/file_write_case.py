# open的第二个参数，不传的话默认为r表示只读
# w只写，a追加写，r+读写
with open("file/write.txt", "w") as file_object:
    file_object.write("hello, world!\n")
    file_object.write("hello, world!\n")
