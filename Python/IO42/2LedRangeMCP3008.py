# coding:utf-8
from gpiozero import MCP3008
import RPi.GPIO as GPIO
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

led_pin = 4
GPIO.setup(led_pin, GPIO.OUT, initial = False)

adc = MCP3008(channel=0)

led1 = GPIO.PWM(led_pin, 100) #100Hz
led1.start(0)


while True:
    
    led1.ChangeDutyCycle(100 - 1 / adc.value)
        
led1.stop()
GPIO.cleanup()
