# coding:utf-8
import RPi.GPIO as GPIO
import time
import sys
import dht11

tempGpio = 14
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

dhtStat = dht11.DHT11(pin = tempGpio)

stat = dhtStat.read()
# print(stat.temperature)
# print(stat.humidity)

print("温度:{0}℃".format(stat.temperature))
print("湿度:{0}%".format(stat.humidity))

