File Edit Options Buffers Tools Python Help                                                                
# coding:utf-8                                                                                             
import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

SW = 4
LED = 17
count = 0

GPIO.setup(LED, GPIO.OUT, initial = False)
GPIO.setup(SW, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)


try:
    while True:
        if GPIO.input(SW):
            count = count + 1

            if count/2:
                GPIO.output(LED,  True)
                print("switch ON")
            else:
                GPIO.output(LED, False)
                print("switch OFF")

        time.sleep(0.1)

except KeyboardInterrupt:
    pass

finally:
    GPIO.cleanup()
    exit()
