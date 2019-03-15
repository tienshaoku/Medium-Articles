#include <bits/stdc++.h>
using namespace std;

int main(void){
    int num;
    cin >> num;

    int x[num];
    memset(x, 0, num*sizeof(int));
    for (int i = 0; i< num; i++)
        cin >> x[i];

    int count = 1, max_count = 0, result = 0;
    for (int i = 1; i< num; i++){
        if (x[i] == x[i-1])
            count ++;
        else{
            max_count = 1;
            for (int j = 1; j< count; j++){
                if (i+j<=num-1){
                    if (x[i] == x[i+j])
                        max_count ++;
                    else
                        break;  // this line is crucial, as it has to deal with unsequtive numbers
                }
            }
            result = max(result, max_count);
            count  = 1;
        }
    }
    cout << 2*result << '\n';

}
