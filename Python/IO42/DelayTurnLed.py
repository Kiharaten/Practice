# coding:utf-8
import RPi.GPIO as GPIO
import time

def countSec(timer, type):
    """
    指定した秒数を0.1秒刻みで数える
    正常終了したかどうかでTrue or Falseを返す
   
    第一引数:秒数(sec * 10), 第二引数:判定(1 or 0)
    """
    cnt = 0

    print("\ntype={0}, count timer={1} start".format(type, timer))
    while GPIO.input(SW) == type and cnt <= timer: # 0.1秒刻みで押している時間を数える
        print("gpio true ={0}".format(GPIO.input(SW)))
        time.sleep(0.1)
        cnt = cnt + 1
    else:
        print("count stop")
        if timer <= cnt: # 正常終了の場合True, 異常終了の場合Falseを返す
            print("return True")
            return True
        else:
            print("return False")
            return False

# ---------- 主処理 ----------
GPIO.setwarnings(False) 
GPIO.setmode(GPIO.BCM)

SW = 4
LED = 17

GPIO.setup(SW, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)
GPIO.setup(LED, GPIO.OUT, initial = False)

while True:
    if GPIO.input(SW):
        flg = countSec(30, 1) # 3秒間GPIO.inputに1が入り続けるとTrueを返す
        if flg:
            GPIO.output(LED, True)
            print("switch-ON")

    elif not GPIO.input(SW):
        flg = countSec(20, 0) # 2秒間GPIO.inputに0が入り続けるとTrueを返す
        if flg:
            GPIO.output(LED, False)
            print("switch-OFF")
            
GPIO.cleanup()
