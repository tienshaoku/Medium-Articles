#zerojudge.tw                                                                                                                                                                     
num = int(input())
y   = input()
x   = y.split()
x   = [int(j) for j in x]
MIN = min(x)
for i in range(num):
    if x[i] == MIN:
        position = i 
        #print(position)
   
result = 0 
if position==0:
    for k in range(num-2):
        result += x[0] * x[1] * x[2]
        del x[1]

elif position == num - 1:
    for k in range(num-2):
        result += x[num-3-k] * x[num-1-k] * x[num-2-k]
        del x[num-2-k]
    
else:
    for k in range(num-position-2):
        result += x[position] * x[position+1] * x[position+2]
        del x[position+1]
    for k in range(position-1):
        result += x[position-k] * x[position-1-k] * x[position-2-k]
        del x[position-1-k]
    result += x[0] * x[1] * x[2]
     

print(result)
