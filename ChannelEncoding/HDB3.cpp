#include<iostream>
#include<fstream>
using namespace std;
int main() {
	cout << "------------------HDB3����------------------" << endl;
	//��ȡ�ļ��е��ַ�
	fstream OpenFile("F://test.txt");
	char ch;
	int Sign[23], i = 0,a;
	while (!OpenFile.eof()) {
		OpenFile.get(ch);
		a = (int)ch-48;
		Sign[i] = a;
		i++;
	}
	//cout << "������������������źţ�һ�β�Ҫ����13��" << endl;
	int index = 0, temp = 0, symble[sizeof(Sign) / 4];
	for (int i = 0; i < sizeof(Sign) / 4; i++) {
		if (Sign[i] != 1) {
			//��¼��������0�ĸ����Լ��ɼ���4��0�����
			++index;
			if (index >= 4 && index % 4 == 0) {
				++temp;
				symble[temp - 1] = i;//��ʶÿһ�γ���4��0ʱi��λ��
			}
		}
		else {
			index = 0;
		}
	}
	for (size_t i = 0; i < sizeof(Sign)/4-1; i++)
	{
		cout << Sign[i];
	}
	cout << "\n\n";

	/*int index = 0, temp = 0, symble[sizeof(Sign) / 4];
	for (int i = 0; i < sizeof(Sign) / 4; i++) {
		cin >> Sign[i];
		if (Sign[i] != 1) {
			//��¼��������0�ĸ����Լ��ɼ���4��0�����
			++index;
			if (index >= 4 && index % 4 == 0) {
				++temp;
				symble[temp - 1] = i;//��ʶÿһ�γ���4��0ʱi��λ��
			}
		}
		else {
			index = 0;
		}

	}*/
	for (size_t i = 0; i < sizeof(symble)/4-21; i++)
	{
		//cout << symble[i] << endl;//Ϊ��������ǵ�V��λ��
	}
	
	int ts[sizeof(Sign) / 4], t = 1;
	ts[0] = 0;//����ts[0]��Ϊ��ʹ��ts�����ܹ���¼numbersOfEqual_1_BetweenVandV��ֵ
	int sum = 0;//sum��¼���Ժ�
	int x = 0;//������ʶ�ǵڼ�������������0�����
	for (int i = 0; i < sizeof(Sign) / 4; i++) {
		//���������4��0�������Ҳ����temp==0��ʱ��HDB3��AMI��һ���ġ�
		sum += Sign[i];
		if (temp == 0) {
			if (Sign[i] == 0)
				cout << "--";
			else {
				if (sum % 2 == 0)
					cout << "__";
				else
					cout << "~~";
			}
		}
		
		//�������4��0�����ʱ
		if (temp != 0) {
			if (Sign[i] == 0) {
				if (i != (symble[x]-3))//ע�������Ǽ�3�����Ǽ�ȥ4����Ϊѭ��ʹ��i++
					cout << "--";
				else {
					//���ʱ����������0000�еĵ�һ����0��λ���ϣ������������λ�õĲ���

					//����numbersOfEqual_1_BetweenVandV����¼����V֮��������������ż������1��
					int numbersOfEqual_1_BetweenVandV=0;
					if (symble[x + 1] < sizeof(Sign)/4) {//ע���������symble[x+1]<Sign�ĳ����ǻ�����Խ�������
						for (int a = symble[x]; a < symble[x + 1]; a++) {
							if (Sign[a] == 1)
								numbersOfEqual_1_BetweenVandV++;
						}
					}
					//numbersOfEqual_1_BetweenVandV��ֵ������x++���仯�ģ����Ա���ס֮ǰ��numbersOfEqual_1_BetweenVandV�����ж�
					ts[t] = numbersOfEqual_1_BetweenVandV;
					
					//�ж�0000��α仯
					if (ts[t-1]>0 && ts[t-1] % 2 == 0) {
						//����0000���B00V��ʱ��
							if (sum % 2 == 0) cout << "~~";
							else cout << "__";
							Sign[i + 3] = 1;
							//sum = sum - 2;//��Ϊ��������0000���B00V,�������α�Ϊ1����ʵ����sum%2��˵�൱��û�仯���ʴ˾��Ҫ�ɲ�Ҫ
					}
					else
					{	//����0000���000V��ʱ��
						cout << "--";
						if (sum % 2 != 0) {
							Sign[i + 3] = 1;
							sum = sum - 1;//��Ϊ��һ��V��000V��V���1,��ʵ��sum+1,���������һ��
						}
					}	
				x++,t++;
				}
			}
			else {
					if (sum % 2 == 0)
						cout << "__";
					else
						cout << "~~";
			}
		}
	}
	system("pause");
	return 0;

}
