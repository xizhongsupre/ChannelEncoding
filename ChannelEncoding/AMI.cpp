#include<iostream>
using namespace std;
int main() {
	cout << "---------------------------AMI����-------------------------" << endl;
	cout << "����������ź�(һ�����벻Ҫ����13��)��";
	int sign,Sign[13];
	//cout << sizeof(Sign)/4;
	for (int i = 0; i < sizeof(Sign) / 4; i++) {
		cin >> Sign[i];
	}
	//��ȡ�źŲ��������Ļ
	int temp = 0;
	for (int i = 0; i < sizeof(Sign)/4; i++) {
		if (Sign[i] != 0)
			temp += 1;
		if (Sign[i] == 0) {
			cout << "--";
		}
		else {
			if (temp% 2 != 0) {
				cout << "~~"; 
			}
			else {
				cout << "__";
			}
		}
	}
	
	system("pause");
	return 0;

}
