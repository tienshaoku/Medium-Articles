#include <bits/stdc++.h>                                                                                                                                                          
using namespace std;

void binary_search(vector<int> v, int target, int &upper, int &lower){
    int l1 = 0, l2 = 0, r1 = v.size() - 1, r2 = r1; 
    while(r1 != l1){
        int m = (l1 + r1) / 2;
        if (v[m] >= target) r1 = m;
        else            l1 = m + 1;
    }   
    lower = l1; 

    while(r2 != l2){
        int m = (l2 + r2) / 2;
        if (v[m] > target) r2 = m;
        else            l2 = m + 1;
    }   
    upper = l2;    
}

int main(void){
    ios::sync_with_stdio(0);
    cin.tie(0);
    int n, run;
    cin >> n;
    vector<int> v1(n);
    for (int i = 0; i< n; i++)
        cin >> v1[i];
    sort(v1.begin(), v1.end());
    
    cin>> run;
    while(run--){
         int upper = 0, lower = 0, target;
         cin >> target;
         binary_search(v1, target, upper, lower);
         if (target <= v1[0])
             cout << "no "<< v1[0] << "\n";
         else if (target >= v1[n-1])
             cout << v1[n-1] << " no" << "\n";
         else if (v1[upper-1] ==  v1[lower]) 
             cout << v1[lower] << "\n";
         else
             cout << v1[upper -1] << " " << v1[upper] << "\n";
    }   
}
