# coding:utf-8
import RPi.GPIO as GPIO
import os
import time
import datetime
import sys

SwGpio = 4
flg = False

GPIO.setwarnings(False)

GPIO.setmode(GPIO.BCM)
GPIO.setup(SwGpio, GPIO.OUT, initial=GPIO.LOW)

try:

    while True:
    
        if flg == False:
            GPIO.output(SwGpio, True)
            flg = True
            print("True")
        elif flg == True:
            GPIO.output(SwGpio, False)
            flg = False
            print("False")
        time.sleep(0.5)
        
except KeyboardInterrupt:
    print("停止")

GPIO.cleanup()
sys.exit()
