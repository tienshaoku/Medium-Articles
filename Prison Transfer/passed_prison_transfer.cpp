#include <bits/stdc++.h>
using namespace std;

int main(void){
    ios::sync_with_stdio(0);
    cin.tie(0);
    int n, t, c;
    cin>> n >> t >> c;

    //int arr[n] = {};  it's said this isn't the perfect way of writing. some compiler may report an error?!
    int arr[n];
    memset(arr, 0, sizeof(int));
    for (int i = 0; i<= n-1; i++){
        cin >> arr[i];
    }

    int result = 0, count = 0;
    for(int i = 0; i< n; i++){
        if (arr[i] <= t){
            count ++;
            if (count>=c)
                result ++;
        }
        else
            count = 0;

    }


    cout << result << "\n";


}
