num = int(input())
y   = input()
x   = y.split()
x   = [int(k) for k in x]


count = 1
pre   = x[0]
max_count = 0
result    = 0
for i in range(1, num):
    if x[i] == pre:
        count += 1
    else:
        max_count = 1
        for j in range(1, count):
            try:
                x[i] == x[i+j]
            except:
                break
            else:
                if x[i] == x[i+j]:
                    max_count += 1
                else:
                    break # this line is crucial
        result = max(result, max_count)
        count = 1
    pre = x[i]

print(2*result)

