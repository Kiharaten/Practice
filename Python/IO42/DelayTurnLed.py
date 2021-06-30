# coding:utf-8
import RPi.GPIO as GPIO
import time

def countSec(second,  type):
    """
    指定した秒数を数える
    正常終了したかどうかでTrue or Falseを返す
   
    第一引数:秒数(sec), 第二引数:判定("ON" or "OFF")
    """
    cnt = 0
    timer = second * 10

    if type == "ON":
        print("\ntype={0}, count timer={1} start".format(type, timer))
        while GPIO.input(SW) and cnt < timer: # 0.1秒刻みで押している時間を数える
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

    elif type == "OFF":
        print("\ntype={0}, count timer={1} start".format(type,timer))
        while not GPIO.input(SW) and cnt < timer: # 0.1秒刻みで離している時間を数える
            time.sleep(0.1)
            cnt = cnt + 1
        else:       
            print("count stop")
            if timer <= cnt: # 正常終了の場合False, 異常終了の場合TTrueを返す            
                print("return False")
                return False         
            else:   
                print("return True")
                return True          

    else:           
        return "tyle error"

GPIO.setwarnings(False) 
GPIO.setmode(GPIO.BCM)

SW = 4
LED = 17

GPIO.setup(SW, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)
GPIO.setup(LED, GPIO.OUT, initial = False)

#ループ
while True:
    if GPIO.input(SW):
        flg = countSec(3, "ON")
        if flg:
            GPIO.output(LED, flg)
            print("switch-ON")
        else:
            print("timeout")

    elif not GPIO.input(SW):
        flg = countSec(2, "OFF")
        if not flg:
            GPIO.output(LED, flg)
            print("switch-OFF")
        else:
            print("timeout")
            
GPIO.cleanup()
