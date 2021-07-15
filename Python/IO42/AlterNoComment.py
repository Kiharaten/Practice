# coding:utf-8
import RPi.GPIO as GPIO
import time, os, sys

def count_second(type, timer):
    cnt = 0
    while GPIO.input(sw) == type and cnt < timer:
        cnt = cnt + 1
        time.sleep(0.1)
    else:
        if timer <= cnt:
            return True
        else:
            return False
        
GPIO.setwarnings(False) 
GPIO.setmode(GPIO.BCM)

sw = 4
led = [17, 27]
flg = [0, 0]
cnt = [0, 0]

GPIO.setup(sw, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)
GPIO.setup(led1, GPIO.OUT, initial = False)
GPIO.setup(led2, GPIO.OUT, initial = False)

try:
    while True:
        if GPIO.input(sw):
            if count_second(1, 5):
                GPIO.output(led1, True)
                GPIO.output(led2, True)
                flg[0] = 1
                if flg[0] != flg[1]:
                    cnt[0] = cnt[0] + 1

        elif not GPIO.input(sw):
            if count_second(0, 5):
                GPIO.output(led1, False)
                flg[0] = 0
                if flg[0] != flg[1]:
                    cnt[1] = cnt[1] + 1

                # ---------- オルタネート用
                if cnt[1] % 2 == 0:
                    GPIO.output(led2, False)

        flg[1] = flg[0]

except KeyboardInterrupt:
    GPIO.cleanup()