# coding:utf-8                                                                                             
import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

SW = 4
LED = 17

GPIO.setup(LED, GPIO.OUT, initial = False)
GPIO.setup(SW, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)

while True:
    GPIO.output(LED, True)
    # print("switch-ON")
    time.sleep(0.001)
    
    GPIO.output(LED, False)
    # print("switch-OFF")
    time.sleep(0.009)

GPIO.cleanup()
