#include <iostream>
#include <vector>
using namespace std;

int main(){
	string s;
	cin >> s;
	char ans[s.length()+1];
	int length = 0;
	for(int i=0;i<s.length();i++){
		if(s[i]=='a')
			length++;
		else{
			cout << string(length,'a') << endl;
			return 0;
		}
	}
	length++;
	cout << string(length,'a') << endl;
	return 0;
}