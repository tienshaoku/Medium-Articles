x = input()

count = 0
max_c = 0
set = ["A", "T", "C", "G"]
for i in x:
    if i in set:
        count += 1
    else:
        count = 0
    max_c = max(max_c, count)

print(max_c)
