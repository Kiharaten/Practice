# coding:utf-8
from gpiozero import MCP3008
import RPi.GPIO as GPIO
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

LED = 4
GPIO.setup(LED, GPIO.OUT, initial = False)

adc = MCP3008(channel=0)

# ループ
while True:
    time.sleep(1 / 1000)

    valueOn = round(adc.value ** 3 * 10, 2)
    valueOff = round(1 / adc.value ** 1.5, 2)

    GPIO.output(LED, True)
#    print("switch-ON")
    time.sleep(valueOn / 1000)

    GPIO.output(LED, False)
#    print("switch-OFF")
    time.sleep(valueOff / 1000)

    text = "value: {0} : {1} = {2}".format(str(valueOn), str(valueOff), str(round(valueOn / valueOff, 2)))
    print(text)
GPIO.cleanup()
