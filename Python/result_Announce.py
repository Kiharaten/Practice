print("基本情報午後問題の採点プログラムです")

cnt = 0
result = 0
ansDict = dict()
checkList = list()
resultDict = dict()

t_ansDict = {
   1:"エオオイ",2:"オウウイエ",3:"エイイアカ",
   4:"エエウイカ",5:"アオクキオク",
   6:"アアエエイイ",7:"イイアイウ",
   8:"イアアイキカカイウ",9:"アイアオオウ",
   10:"エオイイウア", 11:"イオイオウオ",
   12:"アアイエエカオウ", 13:"エエウキキイ",
}

#------------------ 入力部 ------------------#

ansDict[1] = input("\n\n問1の回答を入力してください\n→")

print("\nここからは選択問題です。2〜7の範囲で、選んだ問題を入力してください")
for i in range(4):
   chooseNum = int(input("\n何問めを選んだかを数字で入力してください"))
   if chooseNum in ansDict:
      print("※入力エラー※\n同じものを二度入力しています！")
      exit()

   if  2 <= chooseNum <= 7:
      ansDict[chooseNum] = str(input("問{0}の回答を入力してください\n→".format(chooseNum)))
   else:
      print("※入力エラー※\n2〜7の範囲で入力してください！")
      exit()

   log = chooseNum

ansDict[8] = str(input("\n問8の回答を入力してください\n→"))

print("""\nここからは言語選択問題です。9〜13の範囲で、選んだ問題を入力してください
example = 9:Clang, 10:COBOL, 11:Java, 12:assembler, 13:Excel\n""")

chooseNum = int(input("何問目を選んだかを数字で入力してください"))

if  9 <= chooseNum <= 13:
   ansDict[chooseNum] = str(input("問{0}の回答を入力してください\n→".format(chooseNum)))
else:
   print("※入力エラー※\n9〜13の範囲で入力してください！")
   exit()


#------------------ 採点部 ------------------#
for i in ansDict.keys():

   if 1 <= i <= 7:
      num = len(ansDict[i])

      for j in range(num):
         text1 = ansDict[i]
         text2 = t_ansDict[i]
         if text1[j] == text2[j]:
            result += 12 / num

   elif 8 <= i <= 13:
      num = len(ansDict[i])

      for j in range(num):
         text1 = ansDict[i]
         text2 = t_ansDict[i]
         if text1[j] == text2[j]:
            result += 20 / num



if result >= 60:
   r_text = "合格"
else:
   r_text = "不合格"

print("\n\nあなたの合計点数は{0}点、{1}です。".format(int(result),r_text))