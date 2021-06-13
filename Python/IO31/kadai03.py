
# coding:utf-8
import RPi.GPIO as GPIO
import time
import sys
SwGpio = 4
LedG = 14
LedR = 15
LedY = 18
cnt = 0
flg = 1

GPIO.setwarnings(False)

GPIO.setmode(GPIO.BCM)
GPIO.setup(SwGpio, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

GPIO.setup(LedG, GPIO.OUT, initial=GPIO.LOW)
GPIO.setup(LedR, GPIO.OUT, initial=GPIO.LOW)
GPIO.setup(LedY, GPIO.OUT, initial=GPIO.LOW)

print("システム起動完了")

try:
    while True:
        if GPIO.input(SwGpio):
            cnt = cnt + 1

            if cnt % 4 == 0 and flg == 1:
                GPIO.output(LedY, False)
                GPIO.output(LedG, False)
                GPIO.output(LedR, False)
                
            elif cnt % 4 == 1 and flg == 1:
                print("在籍中")
                GPIO.output(LedY, False)
                GPIO.output(LedR, False)
                GPIO.output(LedG,True)
                flg = 0
                                
            elif cnt % 4 == 2 and flg == 1:
                print("離席中")
                GPIO.output(LedG, False)
                GPIO.output(LedY, False)
                GPIO.output(LedR, True)
                flg = 0
                                                
            elif cnt % 4 == 3 and flg == 1:
                print("外出中")
                GPIO.output(LedR, False)
                GPIO.output(LedG, False)
                GPIO.output(LedY, True)
                flg = 0

        else:
            flg = 1
                
        time.sleep(0.2)
        
except KeyboardInterrupt:
    print("退社")

GPIO.cleanup()
sys.exit()
