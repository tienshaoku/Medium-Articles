x = input()
y = x.split()
y = [int (i) for i in y]
 
length = y[0]
num    = y[1]
 
count  = 0
string = input()
for i in range(num):
    z = input()
    c = z.split()
    c = [int (i) for i in c]
    l = c[0] - 1
    r = c[1] - 1
    for j in range(l,r+1):
        if j+1 < r+1:
             if string[j] == "A" and string[j+1] == "C":
                 count +=1
    print(count)
    count = 0
