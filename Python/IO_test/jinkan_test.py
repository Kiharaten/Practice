# coding:utf-8
import RPi.GPIO as GPIO
import time
import datetime
import sys
import os

IRGpio = 4
LedBGpio = 14
LedRGpio = 15
flg = False

OutFile = '/home/pi/Documents/test/IRdata.txt'


if not os.path.exists(OutFile):
    file = open(OutFile, 'w')
    file.close()
    
GPIO.setwarnings(False)

GPIO.setmode(GPIO.BCM)
GPIO.setup(IRGpio, GPIO.IN)
GPIO.setup(LedBGpio, GPIO.OUT, initial = False)
GPIO.setup(LedRGpio, GPIO.OUT, initial = False)

try:
    while True:
        print(GPIO.input(IRGpio)
        if GPIO.input(IRGpio):
              GPIO.output(LedRGpio, True)
              GPIO.output(LedBGpio, False)
              if not flg:
                  now = datetime.datetime.now()
                  rec = "人感知 at " + now.strftime("%Y-%m-%d, %H:%M:%S")
                  with open(OutFile, 'a'):
                      file.write(rec + '\n')
                      file.close()
                  print(rec)
              flg = True
        else:
              GPIO.output(LedRGpio, False)
              GPIO.output(LedBGpio, True)
              flg = False
        time.sleep(1)

except KeyboardInterrupt:
              print("bye")
              sys.exit()

GPIO.cleanup()
sys.exit()
                  
              
              
