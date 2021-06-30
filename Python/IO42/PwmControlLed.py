#!/usr/bin/python3
from gpiozero import MCP3008
import RPi.GPIO as GPIO
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

led_pin = 4
GPIO.setup(led_pin, GPIO.OUT, initial = False)

adc = MCP3008(channel=0)

led1 = GPIO.PWM(led_pin, 50) #50Hz
led1.start(0)

while True:
    value = adc.value * 200 - 40 #センサーの特性によって調整する
    if 100 < value:
        Cycle = 100
    elif value < 0:
        Cycle = 0
    else:
        Cycle = value
                
    led1.ChangeDutyCycle(Cycle)
    time.sleep(5 / 100)

led1.stop()
GPIO.cleanup()
