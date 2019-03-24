x = input()
y = x.split()
y = [int (i) for i in y]
string = input()

length = y[0]
num    = y[1]
arr    = [0] * (length + 1)
arr[0] = 0

count = 0
for i in range(length-1):
    if string[i] == "A" and string[i+1] == "C":
        count +=1
        arr[i+1] = count
    else:
        arr[i+1] = arr[i]

result   = 0
for i in range(num):
    z = input()
    c = z.split()
    c = [int (i) for i in c]
    l = c[0]
    r = c[1]
    result = arr[r-1] - arr[l-1]         

    print(result)
    result = 0

