# coding:utf-8
import RPi.GPIO as GPIO
import time
import datetime
import sys
import dht11

tempGpio = 14
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
dhtStat = dht11.DHT11(pin = tempGpio)

while(True):                                                                                                                                                               
    stat = dhtStat.read()                                                                                                                                                  
                                                                                                                                                                        
    if stat.temperature == 0 and stat.humidity == 0:                                                                                                                       
        continue                                                                                                                                                           

    with open("/home/pi/Documents/IO31/data.csv", "a") as fp:
        now = datetime.datetime.now()
        data = now.strftime("%Y-%m-%d, %H:%M") + ", " + str(stat.temperature) + ", " + str(stat.humidity) + "\n"
        print(data)
        fp.write(data)
    break     
