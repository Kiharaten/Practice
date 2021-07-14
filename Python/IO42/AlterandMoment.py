# coding:utf-8
import RPi.GPIO as GPIO
import time
import os
import sys

def count_second(type, timer):
    """
    指定した秒数を1/10秒刻みで数える
    正常終了したかどうかでTrue or Falseを返す
   
    第一引数:判定(1 or 0), 第二引数:秒数(sec * 10)
    """
    cnt = 0
    print("type = {0}, timer = {1}, count start >>> ".format(type, timer), end = "")  
    print("GPIO INPUT = ", end = "")
    while GPIO.input(SW) == type and cnt < timer: # 0.1秒刻みで押している時間を数える
        print(GPIO.input(SW), end = "")
        cnt = cnt + 1
        time.sleep(0.1)
    else:
        os.system("clear") #GPIOを 数え終わった時点で画面クリア
        print("\ncount stop, ", end = "")
        if timer <= cnt: # 正常終了の場合True, 異常終了の場合Falseを返す
            print("return True")
            return True
        else:
            print("return False")
            return False
        
# ---------- 前処理 ----------
GPIO.setwarnings(False) 
GPIO.setmode(GPIO.BCM)

SW = 4
LED1 = 17
LED2 = 27
pushFlg1 = 0
pushFlg2 = 0
pushCnt = 0
releaseCnt = 0
text = ""

GPIO.setup(SW, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)
GPIO.setup(LED1, GPIO.OUT, initial = False)
GPIO.setup(LED2, GPIO.OUT, initial = False)

# ---------- 主処理 ----------
try:
    while True:
        if GPIO.input(SW):
            if count_second(1, 5):
                GPIO.output(LED1, True)
                GPIO.output(LED2, True)
                text = "LED1-ON, LED2-ON"
                pushFlg1 = 1
                if pushFlg1 != pushFlg2:
                    pushCnt = pushCnt + 1

        elif not GPIO.input(SW):
            if count_second(0, 5):
                GPIO.output(LED1, False)
                text = "LED1-OFF"
                pushFlg1 = 0
                if pushFlg1 != pushFlg2:
                    releaseCnt = releaseCnt + 1

                # ---------- オルタネート用
                if releaseCnt % 2 == 0:
                    GPIO.output(LED2, False)
                    text = text + ", LED2-OFF"
                else:
                    text = text + ", LED2-ON"

        print(text)
        #print("push flg 1 = {0}, push flg 2 = {1}".format(pushFlg1, pushFlg2))
        print("push count = {0}, release count = {1}".format(pushCnt, releaseCnt))
        pushFlg2 = pushFlg1

# ---------- 後処理 ----------
except KeyboardInterrupt:
    print("\nKeyboardIntereupt")
except:
    print("\nUnknown error")
    
finally:
    GPIO.cleanup()
