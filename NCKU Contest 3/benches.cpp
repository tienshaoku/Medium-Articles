#include <bits/stdc++.h>
using namespace std;

int main(void){                                                                                                                                                                   
    int num, coming, sum=0, tmp;
    cin >> num >> coming;
    vector<int> v(num);
    for (int i = 0; i< num; i++){
        cin >> tmp;
        v.at(i) = tmp;
        sum += tmp;
    }   
    
    sort(v.begin(), v.end());
    int max_v = v[num-1];
    int res_max, res_min;
    res_min = ceil( float((sum+coming)) / float(num));
    if (res_min < max_v){
        res_min = max_v;
    }   
    res_max = max_v + coming;
    cout<< res_min<< " "<< res_max << "\n"; 
}
