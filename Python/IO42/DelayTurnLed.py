# coding:utf-8

import RPi.GPIO as GPIO
import time

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
        if flg:
            GPIO.output(LED, flg)
            print("switch-OFF")
        else:
            print("timeout")

GPIO.cleanup()
