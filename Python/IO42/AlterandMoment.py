# coding:utf-8
import RPi.GPIO as GPIO
import time
import os

def count_second(type, timer):
    """
    指定した秒数を0.1秒刻みで数える
    正常終了したかどうかでTrue or Falseを返す
    第一引数:判定(1 or 0), 第二引数:秒数(sec * 10)
    """
    cnt = 0

    print("\ntype = {0}, timer = {1} count start".format(type, timer))
    while GPIO.input(sw) == type and cnt < timer: # 0.1秒刻みで押している時間を数える
        print("gpio = {0}".format(GPIO.input(sw), end = ""))
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


class GpioControl:
    def __init__(self, pull, *led):
        for i in led:
            self.i = i
            GPIO.setup(self.i, GPIO.out, initial = False)

        print("initialized led1 = {1}, led2 = {2}".format(self.sw, self.led1, self.led2))

    def switch_press(self):
        GPIO.output(led1, True)
        GPIO.output(led2, True)
        print("switch-ON")
        pushFlg1 = 1

    def switch_release(self):
        GPIO.output(led1, False)
        print("switch-OFF")
        pushFlg1 = 0
        if pushFlg1 != pushFlg2:
            cnt = cnt + 1
        if cnt % 2 == 0:
           GPIO.output(led2, False)
        


# ---------- 前処理 ----------
GPIO.setwarnings(False) 
GPIO.setmode(GPIO.BCM)

raspIo = GpioControl(17, 27)
sw = 4
pushFlg1 = 0
pushFlg2 = 0
cnt = 0

# ---------- 主処理 ----------
def main():
while True:
    if GPIO.input(sw):
        if count_second(1, 5):
            raspIo.switch_press()
            
    elif not GPIO.input(sw):
        if count_second(0, 5):
            raspIo.switch_release()
    
    print("pushFlg1 = {0}, pushFlg2 = {1}, cnt = {2}".format(pushFlg1, pushFlg2, cnt))
    pushFlg2 = pushFlg1
    time.sleep(0.01)

# ---------- 後処理 ----------
GPIO.cleanup()

if __name__ == '__main__':
    ore = Person(Home())
    main(ore)
