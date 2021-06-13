#!/usr/bin/python3
# coding:utf-8

import pandas as pd
import matplotlib.pyplot as plt

filePath = "/home/pi/Documents/IO31/cgi-bin"

data = pd.read_csv("data.csv", index_col = "date", encoding="utf-8")
data.plot()
plt.show()

plt.savefig(filePath + "/aaa.png")
# これだけ。

# html作成
print("CONTENT-TYPE: TEXT/HTML; charset=utf-8")
print("グラフ表示<BR>")
print("<IMG SRC='" + filePath + "/aaa.png'>")
