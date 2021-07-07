# coding:utf-8
import RPi.GPIO as GPIO
import os
import time
import datetime
import sys

SwGpio = 4
LedGpio = 17
cnt = 0
flg = 0

GPIO.setwarnings(False)

GPIO.setmode(GPIO.BCM)
GPIO.setup(SwGpio, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
GPIO.setup(LedGpio, GPIO.OUT, initial=GPIO.LOW)

try:
    
    file = "data.csv"
    fp = open(file, "w")
    
    while True:
        nowdate = datetime.datetime.now()
        rec = str(nowdate)[0:19] + ", SW-ON! \n"
        
        if GPIO.input(SwGpio):
            if flg == 0:
                GPIO.output(LedGpio, True)

                print(rec)
                fp.write(rec)

                flg = 1            
        else:
            GPIO.output(LedGpio, False)
            flg = 0
            
        time.sleep(0.01)
        
except KeyboardInterrupt:
    fp.close()
    print("停止")

GPIO.cleanup()
sys.exit()
