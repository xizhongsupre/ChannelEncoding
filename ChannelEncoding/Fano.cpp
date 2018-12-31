#include<stdio.h>                                             //ͷ�ļ� 
#include<math.h> 
#include<string.h> 
#define Bmax 10                                               //��볤�� 
#define Smax 20                                               //������󳤶� 
 
/************����"λ"�Ľṹ**********/ 
struct Bit 
{ 
 char b[Bmax];                    //�����볤��������������� �ַ���   ��ɳ�Ա 
 int last; 
}; 
/***********������ŵĽṹ**********/ 
typedef struct symbol                                         //������ṹ 
{ 
 char c;                                               //������Դ����cΪ�ַ��� 
 float probability;                                //������Ÿ���probalityΪ������ 
 struct Bit bit;                                           //Ƕ�׵Ľṹ�� 
}sbl; 
sbl s[Smax];                                                  //S��������������� 
/********������ŵķ��Ÿ���********/ 
void input(int n) 
{ 
 int i;                                                    //�������i���� ���� 
 int c;                           //�������c���� �������͵ĺê��ַ��͵�Ҳ���� 
 printf("��������ż����Ÿ���\n");          //��ӡ����ʾ��"��������ż����Ÿ���" 
    c=getchar(); 
 for(i=0;i<n;i++)                                //��forѭ��Ϊ���ż����Ÿ��ʸ�ֵ 
 { 
  scanf("%c",&s[i].c);                               //��scanf����������� 
  scanf("%f",&s[i].probability);                      //��scanf����������Ÿ��� 
  c=getchar(); 
 } 
} 
/***********��ð�ݷ�����**********/ 
void sort(int n) 
{ 
 float t;                                                   //�������tΪ������ 
 char a;                                                   //�������aΪ�ַ��� 
 int i,j;                                                 //����ѭ������i,jΪ���� 
 for(i=1;i<n;i++)                          //Ƕ��forѭ�� i��1��n-1 ��ѭ��n-1�� 
  for(j=0;j<n-i;j++)                                      //���±�Ϊ0�ĵ�һ�����Ÿ��ʿ�ʼ����֮��ĸ������αȽ� 
   if(s[j].probability<s[j+1].probability)             //������ߴ���ǰ�ߪ���ͨ��www.docin.com 
 //�����ı���a��t�ֱ𽻻�����ź͸��� 
   {t=s[j].probability; 
   a=s[j].c; 
   s[j].probability=s[j+1].probability; 
   s[j].c=s[j+1].c; 
   s[j+1].probability=t; 
            s[j+1].c=a; 
   }                       //��˪��˺���ʵ���˸����ź�������Ӧ�ĸ��ʪ����ո��ʵĴ�С���Ӵ�С���� 
} 
/************���麯��************/ 
void group(int n)                                         //���庯�����޷���ֵ 
{ 
void group1(int low,int mid,int high);
 int i,pmid,plow,phigh;                                       //�������Ϊ���� 
 pmid=phigh=n;                                            //������������ֵΪn 
 plow=0;                                                      //��������ֵΪ0 
 for(i=0;i<n;i++)                                             //forѭ��n�� 
  s[i].bit.last=0;                                         //�����ŵ�λ��ֵΪ0 
    group1(plow,pmid,phigh);                                    //�������η��麯�� 
} 
/**���ν����ź���ķ��Ÿ��ʽ��н���1:1�ֳ�������**/ 
void group1(int low,int mid,int high)                    //���庯������λ����λ����λ�� 
{ 
 float d,dmin;                                         //�������d,dminΪ������ 
 d=0;                                                         //��d����ֵΪ0 
 int i;                                                 //����ѭ������iΪ���� 
 if(high==low+1)                                   //�����λ�͸�λֻ���1��Ҳ�������������� 
  return;                                                  //�򷵻� 
 for(i=low;i<mid;i++)                     //����ͽ���ѭ����i=��λ��ʼ��<�м���λ 
  d+=s[i].probability;                                     //ÿ��ѭ��d�ĸ��ʾ���ǰ�������Ÿ��ʵ��ۼӺ� 
        dmin=d-2*s[low].probability;                             //dmin= 
 for(i=low+1;i<high;i++)                   //������ѭ����i�ӵ�λ��һ��ʼ��<��λ 
 { 
  d=fabs(dmin-2*s[i].probability);                         //��d��ֵ������ֵ 
  if(d<dmin)                            //���d<dmin,���d��ֵ��ֵ��dmin 
   dmin=d; 
  else                                                     //���d>=dmin,����������forѭ����Ҫ����ʹ����Ĳ�Ϊ0������ӽ�1:1 
   break; 
 } 
 mid=i; 
 void code(int low,int mid,int high);                                                    //���м���λ��ֵi 
    code(low,mid,high);
  group1(low,mid,mid);                         //��һ���Ϊ�����骧�м�Ϊ���mid 
    group1(mid,high,high); 
} 
 
/****�Ը��鸳��һ����������Ԫ��0���͡�1��***/ 
void code(int low,int mid,int high)                              //���� 
{ 
 int i;                                               //����ѭ������iΪ���� 
 for(i=low;i<high;i++)                                        //ѭ�� 
 { 
  if(i<mid)                                          //���iС���м���� 
  { 
   s[i].bit.b[s[i].bit.last]='0';                       //�����������ֵΪ"0" 
            s[i].bit.last++; 
  } 
  else 
  { 
   s[i].bit.b[s[i].bit.last]='1';                       //����͸���������ֵΪ"1" 
   s[i].bit.last++; 
  } 
 } 
} 
/*****�������,���Ÿ��ʼ�����****/ 
void output(int n)                                           //������뺯����nΪ���� 
{ 
 int i,j;                                                     //����ѭ������i,j 
 printf("���������,���Ÿ��ʼ�����\n");                       //��ӡ��ʾ��"���������,���Ÿ��ʼ�����" 
 for(i=0;i<n;i++)                                   //i��0��n����ѭ��ִ��n-1�� 
 { 
  printf("%c\t%f\t",s[i].c,s[i].probability);              //ÿ�εĴ�ѭ�������һ�����ź������Ӧ�ĸ��� 
  for(j=0;j<s[i].bit.last;j++) 
   printf("%c",s[i].bit.b[j]);                          //������� 
  printf("\n"); 
 } 
} 
/***********���뺯��**************/ 
void decode(int n,char a[100])                                   //���� 
{ 
 int i=0,j;                                        //����������͡�����i����ֵ=0 
 char s2[100];                                                //����s2���ַ��� 
 s2[0]='\0';                                          //����s2�ĵ�һ��Ԫ��Ϊ\n 
 while(i<strlen(a))                            //��i<����a�ĳ���ʱ��ִ�����³��� www.docin.com 
  { 
  char temp[2];                                      //��������temp,�ַ��� 
  temp[0]=a[i]; 
  temp[1]='\0'; 
  strcat(s2,temp); 
  for(j=0;j<n;j++) 
  { 
   if(strcmp(s2,s[j].bit.b)==0) 
   { 
    printf("%c",s[j].c); 
    s2[0]='\0'; 
    break; 
   } 
  } 
  i++; 
 } 
 printf("\n"); 
} 
 
int main()                                                      //������ 
{ 
 int n;                                                       //������� 
 char a[100];                                            //�������������Ϊ100 
 printf("��ע�⵱��ʾ���ٴ��������������Ÿ���ʱ��˵�������nֵ����!\n"); 
 do{ 
 printf("��������Ÿ���0<n<=20\n");                           // do...while...ѭ������n>Smax����n<=0ʱ���������� 
 scanf("%d",&n);                                            //����������Ÿ��� 
 }while(n>Smax||n<=0); 
 input(n);                                                    //�ֱ�������롢���򡢷��顢�����������ִ�� 
 sort(n); 
 group(n); 
 output(n); 
 printf("������Ҫ��Ķ����ƴ���\n");                          //��ӡ��ʾ��"������Ҫ��Ķ����ƴ���" 
 scanf("%s",a);                                      //��������Ҫ��Ķ����ƴ��� 
 printf("���������\n");                            //��ӡ��ʾ��"���������" 
 decode(n,a);                                    //�������뺯�������� 
 
} 
