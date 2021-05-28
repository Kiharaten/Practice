########## printの使い方に関してのまとめ ##########

################ practice 1-1 ################
print("hello")
#pythonにおいては#がコメント化を示す。

################ practice 1-2 ################
#変数の使い方
price = 1200  #変数price に1200を代入
print(price)  #変数の内容を出力
print("price")

################ practice 1-3 ################
#how long for tokyo?
kyori = 507.5
jisoku = 80
jikan = kyori / jisoku
print(jikan,"min")

################ practice 1-4 ################
#how lengs in sench?
per_inch = 2.54  # 1inch = 2.54sench
inch = 8
cm = inch * per_inch
print(cm, "cm")

#文字列はカンマで区切る

################ practice 1-5 ################
#花屋の支払い金額を求める
#値段
rose_v = 500
sun_v = 400
tulip_v = 700
#個数
rose_c = 18
sun_c = 8 - 2
tulip_c = 21 - 5
#割引率
rate = 0.9
#計算
sum_v = (rose_v * rose_c) + (sun_v * sun_c) + (tulip_v * tulip_c)
payment = sum_v * rate
#結果を表示
print("お買い物合計金額は", sum_v, "円になります")
print("割引が適用されて、",payment,"円になります。")
#こっちの方がわかりやすいでしょう？

################ practice 1-6 ################
#文字列の違いについて
print("hello python")
print('hello python')

#if you want to use " or ' in your text
print("Ican't speak english.")
print('He said "I play the piano"')

################ practice 1-7 ################
#数値と文字列を連結して出力する
kakaku_i = 30 * 20
kakaku = str(kakaku_i)

print("お支払金額は、" + kakaku + "円です。")

#このように、str()を使って文字列に置き換える。
#型を統一しておけば + で結合できる。

################ practice 1-8 ################
#format()メソッドを利用して文字の中に数字を埋め込む
per_inch = 2.54
inch = 24
cm = inch * per_inch
print("{0}インチ = {1}センチ".format(inch, cm))

#別のパターン
print("私の名前は{name}です。".format(name = "ミドリ"
))
print("年齢は{age}歳で、仕事は{job}をしています。".format(age = 20, job = "プログラマ"))
#こんな書き方もできる。