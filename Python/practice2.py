########## 分岐、ループに関してのまとめ ##########

############### practice 2-1 ################
# 名前を入力
name = input("お名前は？")
print("こんにちは" + name + "さん！")

############### practice 2-2 ################
# ユーザーから入力を得て値を出力する
per_inch = 2.54
user = input("何インチ？")
inch = float(user)
cm = per_inch * inch
print("{0}inch = {1}cm".format(inch, cm))

int_i = int(12345)
float_i = float(12345)
str_i = str(12345)

print("""{0},{1},
{2},{3},
{4},{5}""".format(int_i, type(int_i), float_i, type(float_i), str_i,type(int_i), type(float_i), type(str_i)))
# 型を変換するメソッドとして、
# float(), int(), str() があり、型を調べるメソッドとしてtype()がある

############### practice 2-3 ################
#if文を利用する
age = int(input("あなたの年齢は？"))

if age >= 80:
    print("大おじいさんですね")

elif age >= 60:
    print("おじいちゃんですね")

elif age >= 30:
    print("おじさんですね")

elif age >= 20:
    print("若者ですね")

elif age >= 10:
    print("ガキンチョですね")

else:
    print("ちっちゃい子ですね！")

#給料計算
money = int(input("時給はいくらですか？"))
time = int(input("何時間働きましたか？"))
ans = money * time

print("時給{0}円で、{1}時間働いたので、".format(money, time))
print("お給料は{0}円です。".format(ans))

#BMIを測定する
weight = int(input("体重を入力してください"))
height = int(input("次に身長を入力してください"))

height = height / 100
bmi = weight / (height * height)


print("\nBMI = {0}".format(bmi))
if bmi < 18.5:
    print("痩せ気味ですね")

elif (bmi < 18.5) and (bmi < 25):
    print("標準体型ですね")

elif (bmi < 25) and (bmi < 30):
    print("ちょっと太り気味かもしれませんね")

else:
    print("ダイエット始めた方がいいかもしれませんね。")



# < が続くif分の時は、一番小さい値が先頭に来る
# > が続くif分の時は、一番大きい値が先頭に来る

# pythonで用いる論理演算子は、or, and, notである。


########### test of and ############
print("\ntest of and\n")

if True and True:
    print("true")
else:
    print("false")


if True and False:
    print("true")
else:
    print("false")


if False and True:
    print("true")
else:
    print("false")


if False and False:
    print("true")
else:
    print("false")


############ test of or ############
print("\ntest of or\n")

if True or True:
    print("true")
else:
    print("false")


if True or False:
    print("true")
else:
    print("false")


if False or True:
    print("true")
else:
    print("false")


if False or False:
    print("true")
else:
    print("false")

############ test of not ############
print("\ntest of not\n")

if not True:
    print("true")
else:
    print("false")

if not False:
    print("true")
else:
    print("false")

print("")

############### practice 2-4 ################
# 繰り返し処理の利用

# whileループ
num = 1
while num <= 10:
    print("{0}回目".format(num))
    num += 1

else:
    print("")

#forループ
for i in range(6):  #6回繰り返す
    print("{0}回目".format(i))
else:
    print("")

for i in range(1, 6):  #1~6まで繰り返す
    print("{0}回目".format(i))
else:
    print("")

for i in range(1, 6, 2):  #1~6まで2づつ足して繰り返す
    print("{0}回目".format(i))
else:
    print("")

# このように、for文の範囲指定にはrange()関数を用いる。

# pythonの繰り返しにはelseを用いることができ、
# ループ終了後の処理を指定することができる。

# # range
# for e in range(3):
#     print(e)

# # リスト
# for e in [0, 1, 2]:
#     print(e)

# # タプル
# for e in (0, 1, 2):
#     print(e)

# # 集合
# for e in {0, 1, 2}:
#     print(e)

# # 辞書
# for e in {0:'a', 1:'b', 2:'c'}:
#     print(e)

# # 文字列
# for e in '012':
#     print(e)
# for文に関して、これらの形式のループも利用可能

# pythonの本から抜粋
# 画面に300本の縦線を書く

# グラフィックライブラリを取り込む
from tkinter import *   # ライブラリを取り込む
#画面の初期化
w = Canvas(Tk(), width=830, height=445)# 900*400の画面を用意
w.pack()#描画画面を配置する

#線をたくさん引く
# for i in range(300):    # 描画処理
#     x = i * 3
#     w.create_line(x, 0, x, 400, fill="#FF0000")

# mainloop()    # 画面を表示して待機
# ↑これがないと一瞬だけ表示して終了してしまう

#単純に線を引くプログラム
w.create_line(0, 0, 100, 100, fill="#FF0000")
w.create_line(100, 0, 0, 100, fill="#FF0000")
w.create_line(100, 100, 100, 200, fill="#FF0000")
w.create_line(100, 200, 200, 300, fill="#FF0000")
w.create_line(200, 300, 100, 400, fill="#FF0000")
w.create_line(100, 300, 200, 400, fill="#FF0000")
w.create_line(100, 300, 0, 400, fill="#FF0000")

#上のやつを反転したやつ
w.create_line(830-0, 445-0, 830-100, 445-100, fill="#FF0000")
w.create_line(830-100, 445-0, 830-0, 445-100, fill="#FF0000")
w.create_line(830-100, 445-100, 830-100, 445-200, fill="#FF0000")
w.create_line(830-100, 445-200, 830-200, 445-300, fill="#FF0000")
w.create_line(830-200, 445-300, 830-100, 445-400, fill="#FF0000")
w.create_line(830-100, 445-300, 830-200, 445-400, fill="#FF0000")
w.create_line(830-100, 445-300, 830-0, 445-400, fill="#FF0000")

#楽しいね
print("楽しいね")

mainloop()  # ←これがないと一瞬だけ表示して終了してしまう