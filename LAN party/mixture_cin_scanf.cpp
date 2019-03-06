// scanf mixes with cin,there will possibly be error                                                                                                          
#include <bits/stdc++.h>
using namespace std;


int main(void){
    ios::sync_with_stdio(0);  // if this line is written, cin and scanf cannot be used at the same time, while it's okay to use separately
    cin.tie(0);
    int num;
    cin >> num;  // cin
    int arr[100];
    for (int i = 0; i < 100; i++){
        arr[i] = 0;
    }   
    
    int temp=0;
    while(num--){
        scanf("%d", &temp); // scanf
        arr[temp-1] ++; 
    }   
    
    for (int i = 99; i >= 0; i--){
        while (arr[i]--){
            printf("%d ", i+1); // printf
    }   
    }   
    printf("\n"); // printf
}
