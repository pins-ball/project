#include <iostream>
#include <Windows.h>
#include <string.h>
#include <conio.h>
#include <time.h>
#include <stdlib.h>
#include <cstring>
#include <ctime>

using namespace std;

#define hits 10
#define lifes 3
#define LEFT 75
#define RIGHT 77
#define UP 72
#define scores 1000

class airplane{
private:
    int x, y, gx, gy, i, yy, k;
	int bx[5],by[5],bx2[5],by2[5];
    char face[10];
    char bad1[5][10];
	char bad2[5][10];
    char bad[10];
    char key_in;
    bool sw1;
	bool active;
	char key;
	int hit;
	int life;
	int score;
	double duration;
	char getKey() {
        return key;
    }
	int gethit(){
		return hit;
	}
	int getlife(){
		return life;
	}
	int getscore(){
		return score;
	}
	airplane(){
		i=0;
		k=0;
		gy=0;
		yy=3;
		sw1=false;
		hit=hits;
		life=lifes;
		score=scores;
		active=false;

	}
	void gotoxy(int x, int y){
	
	COORD pos = {x-1, y-1};
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),pos);

}
void delay(int x){
	Sleep(x);
}
void scr_mov()
{
	gotoxy(x,y);
	cout<<face<<endl;
}
void scr_clear() // �߰�
{
    gotoxy(x,y);
    cout<<"          "<<endl;
}
void  scr_mov2()
{	
	
    for(i=0;i<5;i++){
        gotoxy(bx[i],by[i]);
        cout<<bad1[i]<<endl;
    }
	delay(20);
    // ������ ��µ� �� ����� �̹��� �����
    for(i=0;i<5;i++){
        gotoxy(bx2[i],by2[i]);
        cout<<"       "<<endl;
    }
    // ���� ��µ� �� ����� �̹����� ���� ��ġ �迭�� ����
   for(i=0;i<5;i++){
        bx2[i] = bx[i];
        by2[i] = by[i];
    }	
   for(i=0;i<5;i++){
	   if(y<=by[i]){
			scr_mov();
			strcpy(bad1[i],"�ߡ��");
	   }
   }
}
void  hit_fun(){
		system("cls");
		gotoxy(26,8);
		cout<<"���"<<life<<"����"<<endl;
		gotoxy(26,10);
		cout<<"�̼� ����"<<endl;
}
void  life_fun(){
	system("cls");
	gotoxy(26,8);
	cout<<"���� ������ "<<hit<<"���� ����ֽ��ϴ�.";
	gotoxy(26,10);
	cout<<"�̼� ����"<<endl;
}
void  start()
{
	
	gotoxy(21,8);
	cout<<"���ΰ� �̵� : �¿� ȭ��ǥ Ű����"<<endl;
	gotoxy(26,10);
	cout<<"���� �߻� : �����̽���"<<endl;
	gotoxy(20,12);
	cout<<"S Ű�� �����ϸ� ������ ���۵˴ϴ�."<<endl;
	cin>>key;
}
void  gun_mov()
{
	
	gotoxy(gx,gy);
	cout<<"��"<<endl;
	gotoxy(gx,gy+1);
		cout<<"  "<<endl;
		if(gy==1){
			gotoxy(gx,1);
			cout<<"  "<<endl;
		
		}
		scr_crash();

		
}
void  scr_move(){
	gotoxy(4,1);
		cout<<"hit : "<<hit<<" "; // ���� 10���� 1�� ���� 90���� ����
		gotoxy(16,1);
		cout<<"life : "<<life;
	if(kbhit()){
			key_in=getch();
			if(key_in == LEFT){
				if(x>2){
					scr_clear();
					x--;
				}
			}else if(key_in == RIGHT ){
				if(x<70){
					scr_clear();
					x++;
				}
			}else if(key_in == ' '){
				if(gy==0){
					sw1=true;
					gx=x + 3;
					gy=23;

				}
			}
	}
		
	if(sw1==true){
		gy--;
		if(gy==0){
			sw1=false;
		}else{
			gun_mov();
		}
	}
	if(life==3||yy==24){
			active=true;
	}
	if(yy==24){
			yy=3;
		}else{
			if(k==10){
				k=0;
				yy++;

			}else{
				k++;
			}
		}
		for(i=0;i<5;i++){
			by[i]=yy;	
		}
		if(active==true){
			crash1();
		}
		scr_mov2();
}

void  str_cpy(){
	char st1[15]="��-��-��";
	char st2[10]="�ߡ��";
	char st3[10]="      ";
	strcpy(face,st1);
	x=35;
	y=22;
	
	for(int i=0; i<5; i++){
		strcpy(bad1[i],st2); // i��° �迭�� ���ڿ� ����
		bx[i]=(i+1)*13;
		by[i]=3;
	}
}
void  scr_crash(){
	for(int i=0;i<5;i++){
		if(gx>bx[i]-1&&gx<bx[i]+11&&gy==by[i]){
			if(strcmp(bad1[i],"       ")==1){
				strcpy(bad1[i],"       ");
				//strcpy();
				hit=hit-1;
			}
		}	
	}
	
}
void  crash1(){
	for(int i=0;i<5;i++){
		//if(x>bx[i]+6&&x+8<bx[i]&&y-1==by[i]){
		if(x<bx[i]+6&&x+8>bx[i]&&y-1==by[i]){
			if(strcmp(bad1[i],"       ")==1){
					life=life-1;
					active=false;	
					
			}

		}
				//hero.y-1���� ������ �༭ �ذ�����
	}

}
};



int main(){
	time_t start, finish;
	double duration;
	airplane hero;

	hero.str_cpy();
	start = time(NULL);
	hero.start();
	 if (hero.getKey() == 's') {
		system("cls");
	while(1)
	{
		hero.scr_mov();
		hero.scr_move();
		
		
		delay(10);


		if(hero.gethit()==0){
			
			hero.hit_fun();
			finish = time(NULL);
			duration = (double)(finish - start);
			gotoxy(28,1);
			cout<<"time : "<<duration<<"��";
			break;
		}else if(hero.getlife() ==0 || hero.getscore() ==0){
			hero.life_fun();
			break;
		}
	}
	}
	return 0;
}