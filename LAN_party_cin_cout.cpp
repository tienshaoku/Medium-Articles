// cin, cout only
#include <bits/stdc++.h>
using namespace std;


int main(void){
    ios::sync_with_stdio(0); //this line will disable scanf, while it still works on my computer
    cin.tie(0);
    int num;
    //scanf("%d", &num);
    cin >> num;
    int arr[100];
    for (int i = 0; i < 100; i++){
        arr[i] = 0;
    }
    
    int temp=0;
    while(num--){
        cin >> temp;
        arr[temp-1] ++;
    }
    
    for (int i = 99; i >= 0; i--){
        while (arr[i]--){
            cout << i+1 << " ";
    }
    }
    cout << "\n";    
}
