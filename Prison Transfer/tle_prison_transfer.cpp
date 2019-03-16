#include <bits/stdc++.h>
using namespace std;

int main(void){
    ios::sync_with_stdio(0);
    cin.tie(0);
    int n, t, c;
    cin>> n >> t >> c;

    //int arr[n] = {};
    int arr[n];
    memset(arr, 0, sizeof(int)*n);
    for (int i = 0; i<= n-1; i++){
        cin >> arr[i];
    }

    int result = 0, count = 0;
    for (int i= 0; i<n-c+1; i++){
        for (int j = 0; j < c; j++){
            if (arr[i+j] <=t)
                count ++;
            else
                break;
        }
        if (count == c){
            result ++;
        }
        count = 0;
    }
    cout << result << "\n";


}
