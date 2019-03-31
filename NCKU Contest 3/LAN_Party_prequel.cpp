#include <bits/stdc++.h>                                                                                                                                                          
using namespace std;

int main(void){
    //ios::sync_with_stdio(0);
    //cin.tie(0);
    int n, run;
    cin >> n;
    vector<int> v1(n);
    for (int i = 0; i< n; i++)
        cin >> v1[i];
    sort(v1.begin(), v1.end());
    
    cin>> run;
    for (int i = 0; i< run; i++){
         int target;
         cin >> target;

         vector<int>::iterator low, upp;
         low = lower_bound(v1.begin(), v1.end(), target);
         upp = upper_bound(v1.begin(), v1.end(), target);
     
         int lower, upper;
         lower = low - v1.begin();
         upper = upp - v1.begin();

         if (target <= v1[0])
             cout << "no "<< v1[0] << "\n";
         else if (target >= v1[n-1])
             cout << v1[n-1] << " no" << "\n";
         else if (v1[upper-1] ==  v1[lower]) 
             cout << v1[lower] << "\n";
         else
             cout << v1[upper -1] << " " << v1[upper] << "\n";
     
         //cout << "\n";
    }   
}
