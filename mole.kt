package com.pinball.molegame

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.View
import java.util.*

class mole @SuppressLint("UseCompatLoadingForDrawables") constructor(
    context: Context,
    maxpoint: Int,
    var pref1: SharedPreferences
) :
    View(context) {
    private var Scale_X = 0f
    private var Scale_Y = 0f
    private var enejwl1 = arrayOfNulls<BitmapDrawable>(2)
    private var enejwl2: BitmapDrawable? = null
    private var rnajd: BitmapDrawable? = null
    private var enltrnajd: BitmapDrawable? = null
    private var start: BitmapDrawable? = null
    private var rank: BitmapDrawable? = null
    private var main: BitmapDrawable? = null
    private var restart: BitmapDrawable? = null
    private var setting: BitmapDrawable? = null
    private var slow: BitmapDrawable? = null
    private var middle: BitmapDrawable? = null
    private var fast: BitmapDrawable? = null
    private var qorud: BitmapDrawable? = null
    private var pocket: BitmapDrawable? = null
    private var gameover: BitmapDrawable? = null
    private var music: BitmapDrawable? = null
    private var musicstop: BitmapDrawable? = null
    private var score: BitmapDrawable? = null
    private var bestscore: BitmapDrawable? = null
    private var xx = 0
    private var yy = 0
    private var X = 150.0
    private var Y = 20
    private var thread1: BackThread = BackThread()
    private var thread1_pause = true
    var retry = true
    private var cnt0 = 0
    private var cnt1 = 0
    private var cnt2 = 0
    private var cnt3 = 0
    private var cnt4 = 0
    private var cnt5 = 0
    private var cnt6 = 0
    private var cnt7 = 0
    private var cnt8 = 0 
    private var delaytime = 0
    private var delaytimesw = false
    private var delaytime1 = 0 
    private var delaytime1sw = false
    private var tccnt = 0
    private var delaytime2 = 0
    private var delaytime2sw = false 
    private var delaytime3 = 0
    private var delaytime3sw = false 
    private var delaytime4 = 0
    private var delaytime4sw = false
    private var enejwl = IntArray(9)
    private var rndnum = 0
    private var rndnum1 = 0
    private var rndnum2 = 0
    private  var rndnum3 = 0
    private var rndnum4 = 0
    private var rnum1 = 0
    private var rnum2 = 0
    private  var rnum3 = 0
    private var rnum4 = 0
    var stage = 0
    private var point = 0
    private var num = 0
    var time = 0
    private var time1 = 0
    private var ctime = 20000
    private var spe = 2
    private var speed = 2
    private var sound_sw = 1
    private var spoint = 0
    private var _context: Context
    private var backsound: MediaPlayer?
    private var btnsound: MediaPlayer?
    private var stagesnd: Boolean
    public override fun onDraw(Canvas: Canvas) {
        Scale_X = Canvas.width / 720f
        Scale_Y = Canvas.height / 1280f
        Canvas.scale(Scale_X, Scale_Y)
        for (i in 0..5) {
            enejwlrandom(i)
        }
        val p = Paint()
        val pa = Paint()
        if (stage == 0) {
            enejwl_y()
            enejwl_y1()
            enejwl_y2()
            Canvas.drawBitmap(qorud!!.bitmap, 0f, 0f, null)
            Canvas.drawBitmap(pocket!!.bitmap, 110f, 150f, null)
            Canvas.drawBitmap(start!!.bitmap, 200f, 700f, null)
            Canvas.drawBitmap(setting!!.bitmap, 200f, 900f, null)
            Canvas.drawBitmap(rank!!.bitmap, 200f, 1100f, null)
        }
        if (stage == -1) {
            Canvas.drawBitmap(qorud!!.bitmap, 0f, 0f, null)
            Canvas.drawBitmap(pocket!!.bitmap, 110f, 150f, null)
            if (speed == 1) {
                Canvas.drawBitmap(slow!!.bitmap, 200f, 700f, null)
            }
            if (speed == 2) {
                Canvas.drawBitmap(middle!!.bitmap, 200f, 700f, null)
            }
            if (speed == 3) {
                Canvas.drawBitmap(fast!!.bitmap, 200f, 700f, null)
            }
            p.textSize = 40f
            if(sound_sw ==1){
            Canvas.drawBitmap(music!!.bitmap, 200f, 900f, null)
            }
            else {
                Canvas.drawBitmap(musicstop!!.bitmap, 200f, 900f, null)
            }
            Canvas.drawBitmap(main!!.bitmap, 200f, 1100f, null)
        }
        if (stage >= 1 && stage < 6) {
            Canvas.drawBitmap(qorud!!.bitmap, 0f, 0f, null)
            pa.color = Color.GREEN
            Canvas.drawRect(150f, 20f, (X + 550).toFloat(), (Y + 30).toFloat(), pa)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 40f, 320f, null)
            Canvas.drawBitmap(enejwl1[cnt0]!!.bitmap, 40f, enejwl[0].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 40f, 400f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 280f, 320f, null)
            Canvas.drawBitmap(enejwl1[cnt1]!!.bitmap, 280f, enejwl[1].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 280f, 400f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 520f, 320f, null)
            Canvas.drawBitmap(enejwl1[cnt2]!!.bitmap, 520f, enejwl[2].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 520f, 400f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 40f, 620f, null)
            Canvas.drawBitmap(enejwl1[cnt3]!!.bitmap, 40f, enejwl[3].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 40f, 700f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 280f, 620f, null)
            Canvas.drawBitmap(enejwl1[cnt4]!!.bitmap, 280f, enejwl[4].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 280f, 700f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 520f, 620f, null)
            Canvas.drawBitmap(enejwl1[cnt5]!!.bitmap, 520f, enejwl[5].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 520f, 700f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 40f, 920f, null)
            Canvas.drawBitmap(enejwl1[cnt6]!!.bitmap, 40f, enejwl[6].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 40f, 1000f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 280f, 920f, null)
            Canvas.drawBitmap(enejwl1[cnt7]!!.bitmap, 280f, enejwl[7].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 280f, 1000f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 520f, 920f, null)
            Canvas.drawBitmap(enejwl1[cnt8]!!.bitmap, 520f, enejwl[8].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 520f, 1000f, null)
            p.textSize = 40f
            p.color = Color.LTGRAY
            //Canvas.drawText("xx"+ xx+ "yy"+yy,10,300,p );
            Canvas.drawText("Score : $point", 400f, 150f, p)
            Canvas.drawText("Best Scroe : $spoint", 400f, 100f, p)
            if ((time * 0.005).toInt() < 3) {
                Canvas.drawText((3 - time * 0.005).toInt().toString() + "초뒤 시작", 450f, 200f, p)
            }
            pre_save()
        }
        if (X <= -400) {
            X = -600.0
            stage = 6
            //spe=0;
            Canvas.drawBitmap(qorud!!.bitmap, 0f, 0f, null)
            time1 = (time * 0.005).toInt()
            p.textSize = 70f
            pa.textSize = 100f
            p.color = Color.LTGRAY
            pa.color = Color.LTGRAY
            Canvas.drawBitmap(bestscore!!.bitmap, 100f, 250f, null)
            Canvas.drawText("$spoint", 450f,350f,pa)
            Canvas.drawBitmap( score!!.bitmap, 100f, 100f, null)
            Canvas.drawText("$point", 450f,200f,pa)

            Canvas.drawBitmap(gameover!!.bitmap, 100f, 450f, null)
            Canvas.drawBitmap(main!!.bitmap, 50f, 1100f, null)
            Canvas.drawBitmap(restart!!.bitmap, 400f, 1100f, null)


            //Canvas.drawText("xx"+ xx+ "yy"+yy,10,30,p );
            enejwl_y()
            enejwl_y1()
            enejwl_y2()
            //pre_save();
        }
        if (stagesnd == true) {
            backsound!!.setVolume(0.5f, 0.5f)
            if (sound_sw == 1) {
                backsound!!.start()
            } else {
                backsound!!.pause()
            }
            backsound!!.isLooping = true
        }
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        btnsound!!.stop()
        btnsound!!.release()
        btnsound = null
        backsound!!.stop()
        backsound!!.release()
        backsound = null
        var retry1 = true
        thread1.isDaemon = false
        while (retry1) {
            try {
                thread1.join()
                retry1 = false
            } catch (e: InterruptedException) {
            }
        }
    }

    public override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        thread1_pause = if (visibility == VISIBLE) {
            backsound!!.start()
            false
        } else {
            backsound!!.pause()
            true
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        xx = (event.x / Scale_X).toInt()
        yy = (event.y / Scale_Y).toInt()
        val rt = Rect()
        if (event.action == MotionEvent.ACTION_DOWN) {
            rt[200, 700, 500] = 835
            if (stage == 0) {
                if (rt.contains(xx, yy)) {
                    if (spe == 1) {
                        stage = 1
                    }
                    if (spe == 2) {
                        stage = 2
                    }
                    if (spe == 3) {
                        stage = 3
                    }
                    xx = 0
                    yy = 0
                }
            }
            rt[200, 900, 500] = 1035
            if (rt.contains(xx, yy)) {
                if (stage == 0) {
                    stage = -1
                    xx = 0
                    yy = 0
                }
            }
            if (stage == -1) {
                rt[200, 1100, 500] = 1235
                if (rt.contains(xx, yy)) {
                    stage = 0
                }
                rt[200, 900, 500] = 1035
                if (rt.contains(xx, yy)) {
                    sound_sw = if (sound_sw == 1) {
                        0
                    } else {
                        1
                    }
                }
                rt[200, 700, 500] = 835
                if (rt.contains(xx, yy)) {
                    if (speed == 1) {
                        spe = 1
                        stage = 0
                        speed = 2
                    } else if (speed == 2) {
                        spe = 2
                        stage = 0
                        speed = 3
                    } else if (speed == 3) {
                        spe = 3
                        stage = 0
                        speed = 1
                    }
                } //느림
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
            if (stage >= 1 && stage < 6) {
                rt[40, 360, 200] = 480
                if (rt.contains(xx, yy)) {
                    if (enejwl[0] < 370) {
                        //if(stagesnd==true){
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        /*}else{
							btnsound.seekTo(0);
							btnsound.stop();
						}*/tccnt++
                        cnt0 = 1
                        num = 0
                        pluscount()
                        enejwlmove1(num)
                    }
                }
                rt[280, 360, 440] = 480
                if (rt.contains(xx, yy)) {
                    if (enejwl[1] < 370) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt1 = 1
                        tccnt++
                        num = 1
                        pluscount()
                        enejwlmove1(num)
                    }
                }
                rt[520, 360, 680] = 480
                if (rt.contains(xx, yy)) {
                    if (enejwl[2] < 370) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt2 = 1
                        tccnt++
                        num = 2
                        pluscount()
                        enejwlmove1(num)
                    }
                }
                rt[40, 660, 200] = 780
                if (rt.contains(xx, yy)) {
                    if (enejwl[3] < 670) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt3 = 1
                        num = 3
                        enejwlmove1(num)
                        tccnt++
                        pluscount()
                    }
                }
                rt[280, 660, 440] = 780
                if (rt.contains(xx, yy)) {
                    if (enejwl[4] < 670) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt4 = 1
                        num = 4
                        enejwlmove1(num)
                        tccnt++
                        pluscount()
                    }
                }
                rt[520, 660, 680] = 780
                if (rt.contains(xx, yy)) {
                    if (enejwl[5] < 670) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt5 = 1
                        num = 5
                        enejwlmove1(num)
                        tccnt++
                        pluscount()
                    }
                }
                rt[40, 960, 200] = 1080
                if (rt.contains(xx, yy)) {
                    if (enejwl[6] < 970) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt6 = 1
                        num = 6
                        enejwlmove1(num)
                        tccnt++
                        pluscount()
                    }
                }
                rt[280, 960, 440] = 1080
                if (rt.contains(xx, yy)) {
                    if (enejwl[7] < 970) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt7 = 1
                        num = 7
                        enejwlmove1(num)
                        tccnt++
                        pluscount()
                    }
                }
                rt[520, 960, 680] = 1080
                if (rt.contains(xx, yy)) {
                    if (enejwl[8] < 970) {
                        btnsound!!.seekTo(0)
                        btnsound!!.start()
                        cnt8 = 1
                        num = 8
                        enejwlmove1(num)
                        tccnt++
                        pluscount()
                    }
                }
            }
            rt[50, 1100, 350] = 1250
            if (rt.contains(xx, yy)) {
                if (stage == 6) {
                    pre_save()
                    stage = 0
                    X = 150.0
                    point = 0
                    time = 0
                }
            }
            rt[400, 1100, 750] = 1250
            if (rt.contains(xx, yy)) {
                if (stage == 6) {
                    pre_save()
                    if (spe == 1) {
                        stage = 1
                    }
                    if (spe == 2) {
                        stage = 2
                    }
                    if (spe == 3) {
                        stage = 3
                    }
                    X = 150.0
                    point = 0
                    time = 0
                    if (spe == 0) {
                        spe = 2
                    }
                }
            }
        }
        return true
    }

    inner class BackThread : Thread() {
        override fun run() {
            while (retry) {
                try {
                    Handler1.sendEmptyMessage(0)
                    sleep(5)
                } catch (e: InterruptedException) {
                }
            }
        }
    }

    var Handler1: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0) {
                if (stage >= 1 && stage < 6) {
                    timecheck()
                }
                if (stage == 1 && (time * 0.005).toInt() > 3) {
                    delaytime()
                    enejwlmove()
                    countdown()
                }
                if (stage == 2 && (time * 0.005).toInt() > 3) {
                    delaytime1()
                    enejwlmove1()
                    countdown()
                }
                if (stage == 3 && (time * 0.005).toInt() > 3) {
                    delaytime2()
                    enejwlmove2()
                    countdown()
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
				}*/invalidate()
            }
        }
    }

    init {
        spoint = maxpoint
        main = context.resources.getDrawable(R.mipmap.mainmenu) as BitmapDrawable
        restart = context.resources.getDrawable(R.mipmap.restartbutton) as BitmapDrawable
        setting = context.resources.getDrawable(R.mipmap.settingbutton) as BitmapDrawable
        start = context.resources.getDrawable(R.mipmap.gamesaterbutton) as BitmapDrawable
        rank = context.resources.getDrawable(R.mipmap.rangkinbutton) as BitmapDrawable
       // shop = context.resources.getDrawable(R.mipmap.shop) as BitmapDrawable
       // end = context.resources.getDrawable(R.mipmap.end) as BitmapDrawable
        enejwl1[1] = context.resources.getDrawable(R.mipmap.enejwldkdnt) as BitmapDrawable
        enejwl1[0] = context.resources.getDrawable(R.mipmap.enejwl1) as BitmapDrawable
        enejwl2 = context.resources.getDrawable(R.mipmap.enejwl1) as BitmapDrawable
        rnajd = context.resources.getDrawable(R.mipmap.enejwl) as BitmapDrawable
        enltrnajd = context.resources.getDrawable(R.mipmap.enltrnajd) as BitmapDrawable
        slow = context.resources.getDrawable(R.mipmap.slowbutton) as BitmapDrawable
        middle = context.resources.getDrawable(R.mipmap.middlebutton) as BitmapDrawable
        fast = context.resources.getDrawable(R.mipmap.fastbutton) as BitmapDrawable
        qorud = context.resources.getDrawable(R.mipmap.wksel) as BitmapDrawable
        pocket = context.resources.getDrawable(R.mipmap.pocket) as BitmapDrawable
        gameover = context.resources.getDrawable(R.mipmap.gameover) as BitmapDrawable
        music = context.resources.getDrawable(R.mipmap.musicbutton) as BitmapDrawable
        musicstop = context.resources.getDrawable(R.mipmap.musicstop) as BitmapDrawable
        score = context.resources.getDrawable(R.mipmap.score) as BitmapDrawable
        bestscore = context.resources.getDrawable(R.mipmap.bestscore) as BitmapDrawable
        _context = context
        stagesnd = true
        backsound = MediaPlayer.create(_context, R.raw.country)
        btnsound = MediaPlayer.create(_context, R.raw.touch)
        thread1.isDaemon = true
        thread1.start()
    }

    private fun enejwlrandom(cnt: Int) {
        val rnd = Random()
        rndnum = rnd.nextInt(1) + 150
        rndnum1 = rnd.nextInt(1) + 100
        rndnum2 = rnd.nextInt(1) + 70
        //rndnum3 = rnd.nextInt(1)+50;
        //rndnum4 = rnd.nextInt(1)+35;
        rnum1 = rnd.nextInt(3)
        rnum2 = rnd.nextInt(3) + 3
        rnum3 = rnd.nextInt(3) + 6
        rnum4 = rnd.nextInt(9)
    }

    private fun enejwlmove() {
        if (delaytimesw == true) {
            enejwlm()
        }
    }

    private fun enejwlmove1() {
        if (delaytime1sw == true) {
            enejwlm()
        }
    }

    private fun enejwlmove2() {
        if (delaytime2sw == true) {
            enejwlm()
        }
    }

    private fun enejwlmove3() {
        if (delaytime3sw == true) {
            enejwlm()
        }
    }

    private fun enejwlmove4() {
        if (delaytime4sw == true) {
            enejwlm()
        }
    }

    private fun enejwlmove1(n: Int) {
        enejwl[n] += 70
    }

    private fun enejwlm() {
        enejwl[rnum1] -= 70
        enejwl[rnum2] -= 70
        enejwl[rnum3] -= 70
        cnt0 = 0
        cnt1 = 0
        cnt2 = 0
        cnt3 = 0
        cnt4 = 0
        cnt5 = 0
        cnt6 = 0
        cnt7 = 0
        cnt8 = 0
        if (enejwl[rnum1] < 360) {
            enejwl_y()
        }
        if (enejwl[rnum2] < 660) {
            enejwl_y1()
        }
        if (enejwl[rnum3] < 960) {
            enejwl_y2()
        }
    }

    private fun enejwl_y() {
        enejwl[0] = 430
        enejwl[1] = 430
        enejwl[2] = 430
    }

    private fun enejwl_y1() {
        enejwl[3] = 730
        enejwl[4] = 730
        enejwl[5] = 730
    }

    private fun enejwl_y2() {
        enejwl[6] = 1030
        enejwl[7] = 1030
        enejwl[8] = 1030
    }

    private fun delaytime() {
        if (delaytime < rndnum) {
            delaytimesw = false
            delaytime++
        }
        if (delaytime == rndnum) {
            delaytimesw = true
            delaytime = 0
        }
    }

    private fun delaytime1() {
        if (delaytime1 < rndnum1) {
            delaytime1sw = false
            delaytime1++
        }
        if (delaytime1 == rndnum1) {
            delaytime1sw = true
            delaytime1 = 0
        }
    }

    private fun delaytime2() {
        if (delaytime2 < rndnum2) {
            delaytime2sw = false
            delaytime2++
        }
        if (delaytime2 == rndnum2) {
            delaytime2sw = true
            delaytime2 = 0
        }
    }

    private fun delaytime3() {
        if (delaytime3 < rndnum3) {
            delaytime3sw = false
            delaytime3++
        }
        if (delaytime3 == rndnum3) {
            delaytime3sw = true
            delaytime3 = 0
        }
    }

    private fun delaytime4() {
        if (delaytime4 < rndnum4) {
            delaytime4sw = false
            delaytime4++
        }
        if (delaytime4 == rndnum4) {
            delaytime4sw = true
            delaytime4 = 0
        }
    }

    private fun timecheck() {
        time++
    }

    private fun countdown() {
        ctime--
        if (stage == 1 || stage == 2 || stage == 3) {
            if (X >= -400) {
                X -= 0.2
            } else {
                X = -400.0
            }
            if (X <= 170) {
                X -= 0.2
            } else {
                X = 170.0
            }
        }
    }

    private fun pluscount() {
        if (spe == 1) {
            X += 30.0
            point += 30
        }
        if (spe == 2) {
            X += 40.0
            point += 40
        }
        if (spe == 3) {
            X += 40.0
            point += 60
        }
    }

    fun pre_save() {
        val edit = pref1.edit()
        if (spoint >= point) {
            edit.putInt("maxjumsu", spoint)
        } else if (spoint < point) {
            edit.putInt("maxjumsu", point)
            spoint = point
        }
        edit.commit()
    }
}
