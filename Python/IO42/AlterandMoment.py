# coding:utf-8
import RPi.GPIO as GPIO
import time
import os

def countSec(type, timer):
    """
    指定した秒数を0.1秒刻みで数える
    正常終了したかどうかでTrue or Falseを返す
   
    第一引数:判定(1 or 0), 第二引数:秒数(sec * 10)
    """
    cnt = 0

    print("\ntype = {0}, timer = {1} count start".format(type, timer))
    while GPIO.input(SW) == type and cnt < timer: # 0.1秒刻みで押している時間を数える
        print("gpio = {0}".format(GPIO.input(SW), end = ""))
        cnt = cnt + 1
        time.sleep(0.1)
    else:
        print("count stop, ", end = "")
        if timer <= cnt: # 正常終了の場合True, 異常終了の場合Falseを返す
            print("return True, ", end = "")
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
cnt = 0

GPIO.setup(SW, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)
GPIO.setup(LED1, GPIO.OUT, initial = False)
GPIO.setup(LED2, GPIO.OUT, initial = False)

# ---------- 主処理 ----------
while True:
    if GPIO.input(SW):
        flg = countSec(1, 5)
        if flg:
            GPIO.output(LED1, True)
            GPIO.output(LED2, True)
            print("switch-ON")
            pushFlg1 = 1
            
    elif not GPIO.input(SW):
        flg = countSec(0, 5)
        if flg:
            GPIO.output(LED2, False)
            print("switch-OFF")
            pushFlg1 = 0
            if pushFlg1 != pushFlg2:
                cnt = cnt + 1
            if cnt % 2 == 0:
                GPIO.output(LED1, False)
    
    print("pushFlg1 = {0}, pushFlg2 = {1}, cnt = {2}".format(pushFlg1, pushFlg2, cnt))
    pushFlg2 = pushFlg1

# ---------- 後処理 ----------
GPIO.cleanup()
