import sys

prime = [
    2, 3, 5, 7, 11,
    13, 17, 19, 23, 29,
    31, 37, 41, 43, 47,
    53, 59, 61, 67, 71,
    73, 79, 83, 89, 97,
    101, 103, 107, 109, 113,
    127, 131, 137, 139, 149,
    151, 157, 163, 167, 173,
    179, 181, 191, 193, 197,
    199, 211, 223, 227, 229,
    233, 239, 241, 251, 257,
    263, 269, 271, 277, 281,
    283, 293, 307, 311, 313,
    317, 331, 337, 347, 349,
    353, 359, 367, 373, 379,
    383, 389, 397, 401, 409,
    419, 421, 431, 433, 439,
    443, 449, 457, 461, 463,
    467, 479, 487, 491, 499,
    503, 509, 521, 523, 541,
    547, 557, 563, 569, 571,
    577, 587, 593, 599, 601,
    607, 613, 617, 619, 631,
    641, 643, 647, 653, 659,
    661, 673, 677, 683, 691,
    701, 709, 719, 727, 733,
    739, 743, 751, 757, 761,
    769, 773, 787, 797, 809,
    811, 821, 823, 827, 829,
    839, 853, 857, 859, 863,
    877, 881, 883, 887, 907,
    911, 919, 929, 937, 941,
    947, 953, 967, 971, 977,
    983, 991, 997,
    ]

subnum = list()
cnt1 = list()
cnt2 = list()
cnt4 = list()
cnt6 = list()
cnt8 = list()
cnt10 = list()
cnt12 = list()
cnt14 = list()

fmt = "{0:>2}|"


for i in range(len(prime) - 1):
    subnum.append(prime[i+1] - prime[i])

    # cnt1.append(subnum.count(1))
    # cnt2.append(subnum.count(2))
    # cnt4.append(subnum.count(4))
    # cnt6.append(subnum.count(6))
    # cnt8.append(subnum.count(8))
    # cnt10.append(subnum.count(10))
    # cnt12.append(subnum.count(12))
    # cnt14.append(subnum.count(14))

    sys.stdout.write(fmt.format(subnum.count(1)))
    sys.stdout.write(fmt.format(subnum.count(2)))
    sys.stdout.write(fmt.format(subnum.count(4)))
    sys.stdout.write(fmt.format(subnum.count(6)))
    sys.stdout.write(fmt.format(subnum.count(8)))
    sys.stdout.write(fmt.format(subnum.count(10)))
    sys.stdout.write(fmt.format(subnum.count(12)))
    sys.stdout.write(fmt.format(subnum.count(14)))
    sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt1[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt2[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt4[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt6[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt8[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt10[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt12[i]))
# sys.stdout.write("\n")

# for i in range(len(prime) - 1):
#     sys.stdout.write(fmt.format(cnt14[i]))
# sys.stdout.write("\n")

from tkinter import *   # ライブラリを取り込む
#画面の初期化
w = Canvas(Tk(), width=800, height=800)# 900*400の画面を用意
w.pack()#描画画面を配置する

#線をたくさん引く
# for i in range(300):    # 描画処理
#     x = i * 3
#     w.create_line(x, 0, x, 400, fill="#FF0000")

cent = 400
def pointing(centX,centY,):
    print()

w.create_rectangle(100,100,102,102, fill='#000')
 #塗りつぶし

#楽しいね
print("楽しいね")

mainloop()  # ←これがないと一瞬だけ表示して終了してしまう
