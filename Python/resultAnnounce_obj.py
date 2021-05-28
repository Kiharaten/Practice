THE_ANSWER = [
   "エオオイ", "オウウイエ", "エイイアカ",
   "エエウイカ", "アオクキオク",
   "アアエエイイ", "イイアイウ",
   "イアアイキカカイウ", "アイアオオウ",
   "エオイイウア", "イオイオウオ",
   "アアイエエカオウ", "エエウキキイ",
]
SCORING_1_7 = 12
SCORING_8_13 = 20

import tkinter as tk
from tkinter import *
from tkinter import ttk
import sys

win1 = Tk()
win1.title("基本情報試験午後問題採点プログラム")
win1.geometry("800x500")
# win1.configure(bg='')

label = [
   tk.Label(win1, text="基本情報午後の採点プログラムです"),
   tk.Label(win1, text="※残念ながらダークモードには対応しておりません！申し訳ない！"),
   tk.Label(win1, text="選んだ回答をテキストボックスに入力してください。"),
   tk.Label(win1, text="ここからは選択問題です。2〜7の間で4問選択し、選択した問題とその回答を入力してください"),
]

questionNumber = [
   tk.Label(win1, text="問 １"),
   tk.Label(win1, text="問"),
   tk.Label(win1, text="問 ８"),
]

EditBox = list()
for i in range(1,7):
   EditBox.append(tk.Entry(width=50))


button = tk.Button(win1, text="結果を計算する")
# selectnum.append()

# rdoSet.append(tk.Radiobutton(text='エ', foreground='#F00', background='#00F')


# ーーーーーーー表示部ーーーーーーー
label[2].place(x=50, y=20)
# 問1に関する表示
questionNumber[0].place(x=50, y=50)
EditBox[1].place(x=90, y=50)
# 問2-7に関する表示
label[3].place(x=50, y=100)

questionNumber[1].place(x=50, y=50 + 40 + 60)


button.place(x=350, y=450)
# 表示するときgrid()とpack()を併用すると無限ループするので注意
# 表示するときplace()にすると位置を調節できる
win1.mainloop()

# class chooseNumAndAnswerCharacter:
#     def __init__(self, num, char):
#         self.chooseNum = num
#         self.ansChar = char

# class yourChoose:
#     def __init__(self, choose):
#         self.chooseNum = choose

#     def slider(self, choose):
#         self.chooseNum = choose

# class inputCheck:
#     def checkChooseIsNum(self):
#         if not (order.chooseNum.isdecimal()):
#             print("※入力エラー※\n英数字で入力してください！")
#             exit()

#     def checkChooseIn2_7(self):
#         if not (2 <= int(order.chooseNum) <= 7):
#             print("※入力エラー※\n2〜7の範囲で入力してください！")
#             exit()

#     def checkChooseIn9_13(self):
#         if not (9 <= int(order.chooseNum) <= 13):
#             print("※入力エラー※\n9〜13の範囲で入力してください！")
#             exit()

# ansList = list()
# result = 0

# print("\n基本情報午後問題の採点プログラムです")

# order = yourChoose(1)
# ansList.append(chooseNumAndAnswerCharacter(order.chooseNum,input("\n\n問1の回答を入力してください\n→")))

# print("\nここからは選択問題です。2〜7の範囲で、選んだ問題を入力してください")
# for i in range(4):
#     order.slider(input("\n何問めを選んだかを数字で入力してください"))
#     inputCheck.checkChooseIsNum(order)
#     inputCheck.checkChooseIn2_7(order)
#     ansList.append(chooseNumAndAnswerCharacter(order.chooseNum,input("問{0}の回答を入力してください\n→".format(order.chooseNum))))

# order = yourChoose(8)
# ansList.append(chooseNumAndAnswerCharacter(order.chooseNum,input("\n\n問8の回答を入力してください\n→")))

# print("\nここからは言語選択問題です。9〜13の範囲で、1つ選んだ問題を入力してください")
# print("example = 9:Clang, 10:COBOL, 11:Java, 12:assembler, 13:Excel\n")

# order.slider(input("\n何問めを選んだかを数字で入力してください"))
# inputCheck.checkChooseIsNum(order)
# inputCheck.checkChooseIn9_13(order)
# ansList.append(chooseNumAndAnswerCharacter(order.chooseNum,input("問{0}の回答を入力してください\n→".format(order.chooseNum))))

# for obj in ansList:
#     i = int(obj.chooseNum) - 1

#     if i < 7:
#         for j in range(len(obj.ansChar)):

#             if obj.ansChar[j] == THE_ANSWER[i][j]:
#                 result += SCORING_1_7 / len(THE_ANSWER[i])

#     elif i <= 13:
#         for j in range(len(obj.ansChar)):

#             if obj.ansChar[j] == THE_ANSWER[i][j]:
#                 result += 20 / len(THE_ANSWER[i])

# if result >= 60:
#    r_text = "合格"
# else:
#    r_text = "不合格"

# print("\n\nあなたの合計点数は{0}点、{1}です。".format(round(result), r_text))
