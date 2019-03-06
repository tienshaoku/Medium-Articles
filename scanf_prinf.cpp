// scanf, printf only
#include <bits/stdc++.h>
using namespace std;


int main(void){
    ios::sync_with_stdio(0);  // if this line is written, cin and scanf cannot be used at the same time, while it's okay to use separately
    cin.tie(0);               // it's okay to keep these two lines even using scanf and printf

    int num;
    scanf("%d", &num);
    int arr[100];
    for (int i = 0; i < 100; i++){
        arr[i] = 0;
    }
    
    int temp=0;
    while(num--){
        scanf("%d", &temp);
        arr[temp-1] ++;
    }
    
    for (int i = 99; i >= 0; i--){
        while (arr[i]--){
            printf("%d ", i+1);
        }
    }
    printf("\n");
}
