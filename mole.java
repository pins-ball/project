package com.pinball.playgame;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.media.MediaPlayer;

import com.pinball.playgame.R;

public class mole  extends View {
    float Scale_X;
    float Scale_Y;
    BitmapDrawable enejwlout=null;
    BitmapDrawable enejwl1[]=new BitmapDrawable[2];
    BitmapDrawable enejwl2=null;
    BitmapDrawable akdcl=null;
    BitmapDrawable rnajd=null;
    BitmapDrawable enltrnajd=null;
    BitmapDrawable end=null;
    BitmapDrawable  start=null;
    BitmapDrawable  rank=null;
    BitmapDrawable  shop=null;
    BitmapDrawable  standard=null;
    BitmapDrawable  main=null;
    BitmapDrawable  restart=null;
    BitmapDrawable  setting=null;
    BitmapDrawable  slow=null;
    BitmapDrawable  middle=null;
    BitmapDrawable  fast=null;
    BitmapDrawable  qorud=null;
    BitmapDrawable  pocket=null;
    BitmapDrawable  gameover=null;
    BitmapDrawable  music=null;
    boolean sXL=false,sXR=false;
    int xx,yy,xxx,yyy;
    double X=150;
    int Y=20;
    BackThread thread1=new BackThread();
    boolean thread1_pause=true;
    boolean retry=true;
    int cnt0,cnt1,cnt2,cnt3,cnt4,cnt5,cnt6,cnt7,cnt8=0;// ���ΰ��� �׸��� �����ϱ� ���� ����
    int delaytime;
    boolean delaytimesw=false;
    int delaytime1=0;// ���ΰ� �׸� ����ӵ� ���� ����
    boolean delaytime1sw=false;//�ð����� ����ġ
    int tccnt=0;
    int delaytime2=0;
    boolean delaytime2sw=false;//�ð����� ����ġ
    int delaytime3=0;
    boolean delaytime3sw=false;//�ð����� ����ġ
    int delay3;
    boolean delayt=false;
    int delaytime4;
    boolean delaytime4sw=false;
    int delaycnt2=501;
    int chwidth,chheight;//���ΰ��� �׸��� ���� ���̸� �����ϴ� ����
    int enejwl[]=new int[9];
    int rndnum;
    int rndnum1;
    int rndnum2;
    int rndnum3;
    int rndnum4;
    int rnum1;
    int rnum2;
    int rnum3;
    int rnum4;
    int rnd;
    int delay;
    int stage=0;
    int point=0;
    int num=0;
    int time=0;
    int time1=0;
    int ctime=20000;
    int spe =2;

    int speed = 2;
    int sound_sw=1;
    SharedPreferences pref1;
    int spoint=0;
    Context _context;
    MediaPlayer backsound;
    MediaPlayer btnsound;
    boolean stagesnd;
    @SuppressLint("UseCompatLoadingForDrawables")
    public mole(Context context, int maxpoint, SharedPreferences pref){
        super(context);

        pref1=pref;
        spoint=maxpoint;
        main=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.mainmenu);
        restart=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.restartbutton);
        setting=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.settingbutton);
        start=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.gamesaterbutton);
        rank=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.rangkinbutton);
        shop=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.shop);
        end=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.end);
        enejwl1[1]=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.enejwldkdnt);
        enejwl1[0]=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.enejwl1);
        enejwl2=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.enejwl1);
        akdcl=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.akdcl);
        rnajd=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.enejwl);
        enltrnajd=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.enltrnajd);
        slow=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.slowbutton);
        middle=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.middlebutton);
        fast=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.fastbutton);
        qorud=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.wksel);
        pocket=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.pocket);
        gameover=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.gameover);
        music=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.musicbutton);

        _context=context;
        stagesnd=true;
        backsound=MediaPlayer.create(_context, R.raw.country);
        btnsound = MediaPlayer.create(_context, R.raw.touch);

        thread1.setDaemon(true);
        thread1.start();


    }
    @Override
    public void onDraw(Canvas Canvas){
        Scale_X = Canvas.getWidth()/ 720f;
        Scale_Y = Canvas.getHeight() / 1280f;
        Canvas.scale(Scale_X, Scale_Y);

        for(int i=0;i<6;i++){
            enejwlrandom(i);
        }
        Paint p = new Paint();
        Paint pa = new Paint();
        if(stage==0){
            enejwl_y();
            enejwl_y1();
            enejwl_y2();

            Canvas.drawBitmap(qorud.getBitmap(),0,0,null);
            Canvas.drawBitmap(pocket.getBitmap(),110,150,null);
            Canvas.drawBitmap(start.getBitmap(),200,700,null);
            Canvas.drawBitmap(setting.getBitmap(),200,900,null);
            Canvas.drawBitmap(rank.getBitmap(),200,1100,null);

        }
        if(stage==-1){
            Canvas.drawBitmap(qorud.getBitmap(),0,0,null);
            Canvas.drawBitmap(pocket.getBitmap(),110,150,null);
            if(speed == 1){
                Canvas.drawBitmap(slow.getBitmap(),200,700,null);
            }
            if(speed == 2) {
                Canvas.drawBitmap(middle.getBitmap(), 200, 700, null);
            }
            if(speed == 3) {
                Canvas.drawBitmap(fast.getBitmap(), 200, 700, null);
            }
            p.setTextSize(40);
            Canvas.drawBitmap(music.getBitmap(),200,900,null);
            Canvas.drawText("음악재생"+sound_sw,450,150,p);
           // Canvas.drawText("speed"+speed+"spe"+spe,450,250,p);
            Canvas.drawBitmap(main.getBitmap(),200,1100,null);
        }
        if(stage>=1&&stage<6){

            Canvas.drawBitmap(qorud.getBitmap(),0,0,null);
            pa.setColor(Color.GREEN);
            Canvas.drawRect(150,20,(float) (X+550),Y+30,pa);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 40,320, null);
            Canvas.drawBitmap(enejwl1[cnt0].getBitmap(),40,enejwl[0],null);
            Canvas.drawBitmap(rnajd.getBitmap(), 40,400, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 280,320, null);
            Canvas.drawBitmap(enejwl1[cnt1].getBitmap(),280,enejwl[1], null);
            Canvas.drawBitmap(rnajd.getBitmap(), 280,400, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 520,320, null);
            Canvas.drawBitmap(enejwl1[cnt2].getBitmap(),520,enejwl[2], null);
            Canvas.drawBitmap(rnajd.getBitmap(), 520,400, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 40,620, null);
            Canvas.drawBitmap(enejwl1[cnt3].getBitmap(),40,enejwl[3],null);
            Canvas.drawBitmap(rnajd.getBitmap(), 40,700, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 280,620, null);
            Canvas.drawBitmap(enejwl1[cnt4].getBitmap(),280,enejwl[4], null);
            Canvas.drawBitmap(rnajd.getBitmap(), 280,700, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 520,620, null);
            Canvas.drawBitmap(enejwl1[cnt5].getBitmap(),520,enejwl[5], null);
            Canvas.drawBitmap(rnajd.getBitmap(), 520,700, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 40,920, null);
            Canvas.drawBitmap(enejwl1[cnt6].getBitmap(),40,enejwl[6],null);
            Canvas.drawBitmap(rnajd.getBitmap(), 40,1000, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 280,920, null);
            Canvas.drawBitmap(enejwl1[cnt7].getBitmap(),280,enejwl[7], null);
            Canvas.drawBitmap(rnajd.getBitmap(), 280,1000, null);
            Canvas.drawBitmap(enltrnajd.getBitmap(), 520,920, null);
            Canvas.drawBitmap(enejwl1[cnt8].getBitmap(),520,enejwl[8], null);
            Canvas.drawBitmap(rnajd.getBitmap(), 520,1000, null);
            p.setTextSize(40);
            p.setColor(Color.LTGRAY);
            //Canvas.drawText("xx"+ xx+ "yy"+yy,10,300,p );
            Canvas.drawText("점수"+point,450,100,p);
            Canvas.drawText("최고점수"+spoint,450,150,p);
            if((int)(time*0.005)<3){
                Canvas.drawText((int)(3-time*0.005)+"초뒤 시작", 450, 200, p);
            }
        }
        if(X<=-400){
            X=-600;
            stage=6;
            //spe=0;
            Canvas.drawBitmap(qorud.getBitmap(),0,0,null);
            time1= (int) (time*0.005);
            p.setTextSize(60);
            pa.setTextSize(60);
            p.setColor(Color.LTGRAY);
            //Canvas.drawText("��ƾ�ð�"+time1+"��",220,340,p);
            Canvas.drawText("최고점수"+point,220,325,p);
            if(spoint>=point){
                Canvas.drawText("당신의점수"+spoint,220,225,p);
            }
            else if(spoint<point){
                Canvas.drawText("당신의점수"+point,220,225,p);
            }
            Canvas.drawBitmap(gameover.getBitmap(),100,450, null);
            Canvas.drawBitmap(main.getBitmap(),50,1100,null);
            Canvas.drawBitmap(restart.getBitmap(),400,1100,null);


            //Canvas.drawText("xx"+ xx+ "yy"+yy,10,30,p );
            enejwl_y();
            enejwl_y1();
            enejwl_y2();
            //pre_save();

        }

        if(stagesnd == true){
            backsound.setVolume(0.5f, 0.5f);
            if(sound_sw==1){
                backsound.start();
            }else{
                backsound.pause();
            }
            backsound.setLooping(true);
        }
    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        btnsound.stop();
        btnsound.release();
        btnsound = null;
        backsound.stop();
        backsound.release();
        backsound = null;

        boolean retry1 = true;
        thread1.setDaemon(false);
        while (retry1) {
            try {
                thread1.join();
                retry1 = false;
            } catch (InterruptedException e) {
            }
        }
    }
    @Override
    public void onWindowVisibilityChanged(int visibility){
        super.onWindowVisibilityChanged(visibility);
        if(visibility==View.VISIBLE){
            backsound.start();
            thread1_pause=false;
        }else{backsound.pause();
            thread1_pause=true;

        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        xx=(int)(event.getX()/Scale_X);
        yy=(int)(event.getY()/Scale_Y);
        Rect rt=new Rect();

        if(event.getAction()== MotionEvent.ACTION_DOWN){

            rt.set(200,700,500,835);
            if(stage==0){
            if(rt.contains(xx,yy)){

                    if(spe == 1){
                        stage=1;
                    }
                    if(spe == 2){
                        stage=2;
                    }
                    if(spe == 3){
                        stage=3;
                    }
                    xx=0;
                    yy=0;
                }
            }
            rt.set(200,900,500,1035);
            if(rt.contains(xx,yy)){
                if(stage==0){
                    stage=-1;
                    xx=0;
                    yy=0;
                }
            }
            if(stage==-1){
                rt.set(200,1100,500,1235);
                if(rt.contains(xx,yy)){
                    stage=0;
                }
                rt.set(200,900,500,1035);
                if(rt.contains(xx,yy)){
                    if(sound_sw==1){
                        sound_sw=0;
                    }else{
                        sound_sw=1;
                    }
                }
                rt.set(200,700,500,835);
                if(rt.contains(xx,yy)){
                    if(speed ==1) {
                        spe = 1;
                        stage = 0;
                        speed = 2;
                    } else if (speed ==2) {
                        spe =2;
                        stage=0;
                        speed=3;
                    }else if (speed ==3) {
                        spe =3;
                        stage=0;
                        speed=1;
                    }

                }//느림
               /* rt.set(200,700,500,835);
                if(rt.contains(xx,yy)&&speed==2){
                    spe =2;
                    stage=0;
                    speed=3;

                }//중간
                rt.set(200,700,500,835);
                if(rt.contains(xx,yy)&&speed==3){
                    spe =3;
                    stage=0;
                    speed=1;

                }//빠름*/
            }
            if(stage>=1&&stage<6){
                rt.set(40,360,200,480);
                if(rt.contains(xx,yy)){
                    if(enejwl[0]<370){
                        //if(stagesnd==true){
                        btnsound.seekTo(0);
                        btnsound.start();
						/*}else{
							btnsound.seekTo(0);
							btnsound.stop();
						}*/
                        tccnt++;
                        cnt0=1;
                        num=0;
                        pluscount();
                        enejwlmove1(num);
                    }
                }
                rt.set(280,360,440,480);
                if(rt.contains(xx,yy)){
                    if(enejwl[1]<370){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt1=1;
                        tccnt++;
                        num=1;
                        pluscount();
                        enejwlmove1(num);
                    }
                }
                rt.set(520,360,680,480);
                if(rt.contains(xx,yy)){
                    if(enejwl[2]<370){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt2=1;
                        tccnt++;
                        num=2;
                        pluscount();
                        enejwlmove1(num);
                    }
                }
                rt.set(40,660,200,780);
                if(rt.contains(xx,yy)){
                    if(enejwl[3]<670){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt3=1;
                        num=3;
                        enejwlmove1(num);
                        tccnt++;
                        pluscount();
                    }
                }
                rt.set(280,660,440,780);
                if(rt.contains(xx,yy)){
                    if(enejwl[4]<670){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt4=1;
                        num=4;
                        enejwlmove1(num);
                        tccnt++;
                        pluscount();
                    }
                }
                rt.set(520,660,680,780);
                if(rt.contains(xx,yy)){
                    if(enejwl[5]<670){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt5=1;
                        num=5;
                        enejwlmove1(num);
                        tccnt++;
                        pluscount();
                    }
                }
                rt.set(40,960,200,1080);
                if(rt.contains(xx,yy)){
                    if(enejwl[6]<970){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt6=1;
                        num=6;
                        enejwlmove1(num);
                        tccnt++;
                        pluscount();
                    }
                }
                rt.set(280,960,440,1080);
                if(rt.contains(xx,yy)){
                    if(enejwl[7]<970){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt7=1;
                        num=7;
                        enejwlmove1(num);
                        tccnt++;
                        pluscount();
                    }
                }
                rt.set(520,960,680,1080);
                if(rt.contains(xx,yy)){
                    if(enejwl[8]<970){
                        btnsound.seekTo(0);
                        btnsound.start();
                        cnt8=1;
                        num=8;
                        enejwlmove1(num);
                        tccnt++;
                        pluscount();
                    }
                }
            }
            rt.set(50,1100,350,1250);
            if(rt.contains(xx,yy)){
                if(stage==6){
                    pre_save();
                    stage=0;
                    X=150;
                    point=0;
                    time=0;

                }
            }
            rt.set(400,1100,750,1250);
            if(rt.contains(xx,yy)){
                if(stage==6){
                    pre_save();
                    if(spe == 1){
                        stage=1;
                    }
                    if(spe == 2){
                        stage=2;
                    }
                    if(spe == 3){
                        stage=3;
                    }
                    X=150;
                    point=0;
                    time=0;
                    if(spe==0){
                        spe=2;
                    }//�ٽ��ϱ�
                }
            }
        }
        return true;
    }
    class BackThread extends Thread{
        public void run(){
            while(retry){
                try{
                    Handler1.sendEmptyMessage(0);
                    Thread.sleep(5);
                }
                catch
                (InterruptedException e){;}
            }
        }
    }
    Handler Handler1=new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                if(stage>=1&&stage<6){
                    timecheck();
                }
                if(stage==1&&(int) (time*0.005)>3){
                    delaytime();
                    enejwlmove();

                    countdown();
                }
                if(stage==2&&(int) (time*0.005)>3){
                    delaytime1();
                    enejwlmove1();
                    countdown();

                }
                if(stage==3&&(int) (time*0.005)>3){
                    delaytime2();
                    enejwlmove2();

                    countdown();
                }
				/*else if(stage==4){
					delaytime3();
					enejwlmove3();
					timecheck();
					countdown();
				}
				else if(stage==5){
					delaytime4();
					enejwlmove4();
					timecheck();
					countdown();
				}*/

                invalidate();
            }
        }

    };
    private void enejwlrandom(int cnt){
        Random rnd = new Random();
        rndnum = rnd.nextInt(1)+150;
        rndnum1 = rnd.nextInt(1)+100;
        rndnum2 = rnd.nextInt(1)+70;
        //rndnum3 = rnd.nextInt(1)+50;
        //rndnum4 = rnd.nextInt(1)+35;
        rnum1 = rnd.nextInt(3);
        rnum2 = rnd.nextInt(3) + 3;
        rnum3 = rnd.nextInt(3) + 6;
        rnum4= rnd.nextInt(9) ;
    }
    private void enejwlmove(){
        if(delaytimesw==true){
            enejwlm();
        }
    }
    private void enejwlmove1(){
        if(delaytime1sw==true){
            enejwlm();
        }
    }
    private void enejwlmove2(){
        if(delaytime2sw==true){
            enejwlm();
        }
    }
    private void enejwlmove3(){
        if(delaytime3sw==true){
            enejwlm();
        }
    }
    private void enejwlmove4(){
        if(delaytime4sw==true){
            enejwlm();
        }
    }
    private void enejwlmove1(int n){
        enejwl[n]+=70;

    }
    private void enejwlm(){
        enejwl[rnum1]-=70;
        enejwl[rnum2]-=70;
        enejwl[rnum3]-=70;
        cnt0=0;
        cnt1=0;
        cnt2=0;
        cnt3=0;
        cnt4=0;
        cnt5=0;
        cnt6=0;
        cnt7=0;
        cnt8=0;

        if(enejwl[rnum1]<360){
            enejwl_y();
        }
        if(enejwl[rnum2]<660){
            enejwl_y1();
        }
        if(enejwl[rnum3]<960){
            enejwl_y2();
        }
    }
    private void enejwl_y(){
        enejwl[0]=430;
        enejwl[1]=430;
        enejwl[2]=430;
    }
    private void enejwl_y1(){
        enejwl[3]=730;
        enejwl[4]=730;
        enejwl[5]=730;
    }
    private void enejwl_y2(){
        enejwl[6]=1030;
        enejwl[7]=1030;
        enejwl[8]=1030;
    }
    private void delaytime(){
        if(delaytime<rndnum){
            delaytimesw=false;
            delaytime++;
        }
        if(delaytime==rndnum){
            delaytimesw=true;
            delaytime=0;
        }
    }
    private void delaytime1(){
        if(delaytime1<rndnum1){
            delaytime1sw=false;
            delaytime1++;
        }
        if(delaytime1==rndnum1){
            delaytime1sw=true;
            delaytime1=0;
        }
    }
    private void delaytime2(){
        if(delaytime2<rndnum2){
            delaytime2sw=false;
            delaytime2++;
        }
        if(delaytime2==rndnum2){
            delaytime2sw=true;
            delaytime2=0;
        }
    }
    private void delaytime3(){
        if(delaytime3<rndnum3){
            delaytime3sw=false;
            delaytime3++;
        }
        if(delaytime3==rndnum3){
            delaytime3sw=true;
            delaytime3=0;
        }
    }
    private void delaytime4(){
        if(delaytime4<rndnum4){
            delaytime4sw=false;
            delaytime4++;
        }
        if(delaytime4==rndnum4){
            delaytime4sw=true;
            delaytime4=0;
        }
    }
    private void timecheck(){
        time++;
    }
    private void countdown(){
        ctime--;
        if(stage==1 || stage ==2 || stage ==3){
            if(X>=-400){
                X-=0.2;
            }else{
                X=-400;
            }
            if(X<=170){
                X-=0.2;
            }else{
                X=170;
            }

        }
    }
    private void pluscount(){
        if(spe==1){
            X+=30;
            point+=30;
        }
        if(spe==2){
            X+=40;
            point+=40;
        }
        if(spe==3){
            X+=40;
            point+=60;
        }
    }
    public void pre_save(){
        SharedPreferences.Editor edit = pref1.edit();
        if(spoint>=point){
            edit.putInt("maxjumsu", spoint);

        }
        else if(spoint<point){
            edit.putInt("maxjumsu", point);
            spoint=point;
        }
        edit.commit();
    }
}
//�δ��� �ö���� �ӵ� ī��Ʈ �ӵ� ���� �ӵ� �뷱�� ����
// ���� �ӵ��� ���� 0�� ���� 6��
//���� ����ϰ� ��Ÿ�� ���� �̸� ������ ���� ����Ʈ  �׸� ������ �°� ��ó Ȯ��
//�׸� ���� Ȯ�� ���ְ� ����� �ʿ� ���� ������ǵ� �ʿ����
//�ð��� �� �׸� �ٲٱ�  ����: ���� �δ��� ���  ��� ������ �ð� ������ ǥ�� �ٲٱ�
//������� ���� �����ϰ� 3�� ����
//����ȭ�� ���� �� �����ϱ�