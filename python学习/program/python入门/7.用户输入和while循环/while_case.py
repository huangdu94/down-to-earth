# while循环
i = 0
while i < 10:
    print(i)
    i += 1
print("----------")
# break语句
i = 0
while i < 10:
    if i == 5:
        break
    print(i)
    i += 1
print("----------")
# continue语句
i = -1
while i < 10:
    i += 1
    if i == 5:
        continue
    print(i)
