# coding:utf-8                                                                                             
import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
Ledno1 = 18
Ledno2 = 17

wcnt = input('点滅回数を入力してください。→')
print('LEDを' + wcnt + '回点滅させます。')
interval = input('点滅間隔を入力してください。→')
print(interval + '秒間隔で処理します。')

GPIO.setup(Ledno1, GPIO.OUT)
GPIO.setup(Ledno2, GPIO.OUT)

lcnt = int(1)
while lcnt <= int(wcnt):
    print(str(lcnt) + '回目')

    GPIO.output(Ledno1, True)
    GPIO.output(Ledno2, False)

    time.sleep(float(interval))

    GPIO.output(Ledno1, False)
    GPIO.output(Ledno2, True)

    time.sleep(float(interval))

    lcnt = lcnt + 1

GPIO.output(Ledno1, False)
GPIO.output(Ledno2, False)

print('LEDを消灯しました。')

GPIO.cleanup()