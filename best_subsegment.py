num = int(input())
y = input()
x = y.split()
x = [int(i) for i in x] #crucial step

count = 0
MAX = max(x)
for i in range(0, num):
    if (x[i] == MAX):
        count += 1


max_count = 0
pre = x[0] #previous value
if count == 1:
    print(1)
else:
    count = 1  
    for i in range(1, num):
        if (x[i] == MAX) and (pre == MAX):
            count += 1
        else:
            count = 1
        max_count = max(max_count,count) #update max
        pre = x[i]
    
    print(max_count)
    
