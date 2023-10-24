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
            Canvas.drawBitmap(enejwl1[cnt6]!!.bitmap, 40f, 920f, null)
            Canvas.drawBitmap(rnajd!!.bitmap, 40f, 1000f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 280f, 920f, null)
            Canvas.drawBitmap(enejwl1[cnt7]!!.bitmap, 280f, enejwl[6].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 280f, 1000f, null)
            Canvas.drawBitmap(enltrnajd!!.bitmap, 520f, 920f, null)
            Canvas.drawBitmap(enejwl1[cnt8]!!.bitmap, 520f, enejwl[7].toFloat(), null)
            Canvas.drawBitmap(rnajd!!.bitmap, 520f, 1000f, null)
            for (i in 0..7) {
                if (enejwl[i] > 1500) {
                    enejwl[i] = -100
                }
                if (enejwl[i] == -100) {
                    enejwl[i] = 0
                }
            }
            p.textSize = 40f
            pa.color = Color.BLACK
            Canvas.drawRect(0f, 0f, 720f, 320f, pa)
            pa.color = Color.RED
            Canvas.drawRect(0f, 200f, 720f, 250f, pa)
            p.color = Color.WHITE
            Canvas.drawText(point.toString() + "p", 40f, 150f, p)
            Canvas.drawBitmap(score!!.bitmap, 200f, 0f, null)
            Canvas.drawBitmap(bestscore!!.bitmap, 200f, 60f, null)
            p.textSize = 30f
            p.color = Color.WHITE
            Canvas.drawText(spoint.toString() + "p", 400f, 40f, p)
            p.color = Color.YELLOW
            Canvas.drawText(time.toString(), 660f, 150f, p)
        }
        if (stage == 6) {
            enejwl_y3()
            enejwl_y4()
            Canvas.drawBitmap(qorud!!.bitmap, 0f, 0f, null)
            Canvas.drawBitmap(pocket!!.bitmap, 110f, 150f, null)
            p.color = Color.WHITE
            p.textSize = 40f
            Canvas.drawText("Game Over", 270f, 450f, p)
            Canvas.drawBitmap(gameover!!.bitmap, 200f, 700f, null)
            p.color = Color.WHITE
            p.textSize = 40f
            if(point > spoint){
                Canvas.drawText("New High Score", 250f, 800f, p)
                spoint = point
                val editor = pref1.edit()
                editor.putInt("score", spoint)
                editor.apply()
            }
            if (point < spoint) {
                p.color = Color.YELLOW
                Canvas.drawText("BEST SCORE : " + spoint.toString() + "p", 230f, 700f, p)
            }
            else {
                p.color = Color.WHITE
                Canvas.drawText("BEST SCORE : " + spoint.toString() + "p", 230f, 700f, p)
            }
        }
    }
    private fun enejwlrandom(ennum: Int) {
        rndnum = (Math.random() * 6000 + 1000).toInt()
        rnum1 = (Math.random() * 3 + 1).toInt()
        rnum2 = (Math.random() * 3 + 1).toInt()
        rnum3 = (Math.random() * 3 + 1).toInt()
        rnum4 = (Math.random() * 3 + 1).toInt()
        if (ennum == 0) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt0 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt0 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt0 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt0 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt0 = 0
            }
        }
        if (ennum == 1) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt1 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt1 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt1 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt1 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt1 = 0
            }
        }
        if (ennum == 2) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt2 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt2 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt2 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt2 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt2 = 0
            }
        }
        if (ennum == 3) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt3 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt3 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt3 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt3 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt3 = 0
            }
        }
        if (ennum == 4) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt4 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt4 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt4 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt4 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt4 = 0
            }
        }
        if (ennum == 5) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt5 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt5 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt5 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt5 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt5 = 0
            }
        }
        if (ennum == 6) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt6 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt6 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt6 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt6 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt6 = 0
            }
        }
        if (ennum == 7) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt7 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt7 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt7 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt7 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt7 = 0
            }
        }
        if (ennum == 8) {
            if (stage == 1) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt8 = 0
            }
            if (stage == 2) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum1 * 100
                cnt8 = 0
            }
            if (stage == 3) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum2 * 100
                cnt8 = 0
            }
            if (stage == 4) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum3 * 100
                cnt8 = 0
            }
            if (stage == 5) {
                enejwl[ennum] = 320
                enejwl[ennum] -= rnum4 * 100
                cnt8 = 0
            }
        }
    }
    private fun enejwl_y() {
        if (stage >= 1) {
            if (delaytime < rndnum) {
                delaytime++
                return
            }
            for (i in 0..7) {
                if (i == 0) {
                    enejwl[i] += 1
                }
                if (i == 1) {
                    enejwl[i] += 2
                }
                if (i == 2) {
                    enejwl[i] += 3
                }
                if (i == 3) {
                    enejwl[i] += 4
                }
                if (i == 4) {
                    enejwl[i] += 5
                }
                if (i == 5) {
                    enejwl[i] += 6
                }
                if (i == 6) {
                    enejwl[i] += 7
                }
                if (i == 7) {
                    enejwl[i] += 8
                }
                if (enejwl[i] > 1400) {
                    enejwl[i] = -100
                    if (delaytimesw == false) {
                        delaytimesw = true
                        delaytime1 = 0
                    }
                }
                if (i == 0) {
                    if (delaytime1 < 2000) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 1) {
                    if (delaytime1 < 1600) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 2) {
                    if (delaytime1 < 1200) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 3) {
                    if (delaytime1 < 800) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 4) {
                    if (delaytime1 < 400) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 5) {
                    if (delaytime1 < 200) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 6) {
                    if (delaytime1 < 100) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
                if (i == 7) {
                    if (delaytime1 < 50) {
                        delaytime1++
                        return
                    }
                    delaytimesw = false
                }
            }
        }
    }
    private fun enejwl_y1() {
        if (stage >= 2) {
            if (delaytime2 < rndnum) {
                delaytime2++
                return
            }
            for (i in 0..7) {
                if (i == 0) {
                    enejwl[i] += 1
                }
                if (i == 1) {
                    enejwl[i] += 2
                }
                if (i == 2) {
                    enejwl[i] += 3
                }
                if (i == 3) {
                    enejwl[i] += 4
                }
                if (i == 4) {
                    enejwl[i] += 5
                }
                if (i == 5) {
                    enejwl[i] += 6
                }
                if (i == 6) {
                    enejwl[i] += 7
                }
                if (i == 7) {
                    enejwl[i] += 8
                }
                if (enejwl[i] > 1400) {
                    enejwl[i] = -100
                    if (delaytime2sw == false) {
                        delaytime2sw = true
                        delaytime2 = 0
                    }
                }
                if (i == 0) {
                    if (delaytime2 < 2000) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 1) {
                    if (delaytime2 < 1600) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 2) {
                    if (delaytime2 < 1200) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 3) {
                    if (delaytime2 < 800) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 4) {
                    if (delaytime2 < 400) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 5) {
                    if (delaytime2 < 200) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 6) {
                    if (delaytime2 < 100) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
                if (i == 7) {
                    if (delaytime2 < 50) {
                        delaytime2++
                        return
                    }
                    delaytime2sw = false
                }
            }
        }
    }
    private fun enejwl_y2() {
        if (stage >= 3) {
            if (delaytime3 < rndnum) {
                delaytime3++
                return
            }
            for (i in 0..7) {
                if (i == 0) {
                    enejwl[i] += 1
                }
                if (i == 1) {
                    enejwl[i] += 2
                }
                if (i == 2) {
                    enejwl[i] += 3
                }
                if (i == 3) {
                    enejwl[i] += 4
                }
                if (i == 4) {
                    enejwl[i] += 5
                }
                if (i == 5) {
                    en
