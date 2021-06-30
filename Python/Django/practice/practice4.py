########## 辞書型(dict)に関してのまとめ ##########

################ practice 4-1 ################
#辞書型はindexナンバーではなく任意のキー文字列で内容を操作する。
#辞書型のデータ作成例
#変数 = {'キー1':値1, 'キー2':値2, 'キー3':値3}

#実際に作成してみる
ages = {'中村': 28, '木原': 19, '佐々木': 21, '柴田': 18}
print(ages)  #print OK!

#辞書型から値を取り出す
print("木原の年齢は{0}歳です".format(ages['木原']))

#辞書型のないようを更新する
ages['柴田'] = 19
print(ages)

print("\n")

#辞書型にもin 句を用いることが可能
prices = {'リンゴ': 100, 'バナナ': 90, 'オレンジ': 120}
#リンゴはある？
print("リンゴはある？")
if 'リンゴ' in prices:
    print("あります。")
else:
    print("ありません。")
print("{0}円です。".format(prices['リンゴ']))

#オレンジはある？
print("パイナポーはある？")
if 'パイナポー' in prices:
    print("あります。")
else:
    print("ありません。")
# print("{0}円です。".format(prices['パイナポー']))
print("\n")

#辞書型のキーが思い出せなくても大丈夫！keys()で列挙することができる。
print("prisesのキーは、{0}だよ。".format(prices.keys()))

print("listを使うと{0}と表示される".format(list(prices.keys())))
print("sortedを使うと{0}と並び替えて表示される".format(sorted(prices.keys())))  #実は文字コード順に並び替えている。
print("\n")

#辞書型(dict)の値を列挙する小技まとめ
# list(dict.keys())
# sorted(dict.keys())
# dict,values()
# list(dict.items())

#辞書型とforを組み合わせて使う
fluits = {
    'リンゴ': 100,
    'バナナ': 90,
    'オレンジ': 120,
    'メロン': 500,
    'マンゴー': 410
}

#辞書型のデータ一覧を表示１ ループで辞書型を表示
for name in fluits.keys(): #繰り返し変数 in 個数分
    # 値段を得る
    price = fluits[name]
    #画面に出力
    s = "{0}の値段は{1}円です。".format(name, price)
    print(s)
else:
    print("以上が販売している商品の一覧です。\n")

#辞書型のデータ一覧を表示２ items()を利用する
for name, price in fluits.items():
   #items()を用いることによって辞書型から変数を2個取って来れる
    s = "{0}の値段は{1}円です。".format(name, price)
    print(s)
else:
    print("こっちの方がスマートでしょう？\n")

#for キー, 値 in 辞書型.items():
#   キーと値を利用する

#成績データを辞書型で定義
records = {
    'Tanaka': 72, 'Yamada': 65, 'Hirata': 100,
    'Akai': 56, 'Hukuda':66, 'Sakai':80
}


#合計を求める
sum_v = 0
for v in records.values():  #recordsの値だけを順に取得
    sum_v += v

#合計値を要素数で割る
ave_v = sum_v / len(records)
print("合計点:", sum_v)
print("平均点:", ave_v)

#成績データの一覧と平均点との差を表示
fmt = "| {0:<7} | {1:>4} | {2:>5} |"
#{0}を7文字左寄せ、{1}を4文字右寄せ、{2}を5文字右寄せにする

print(fmt.format("名前", "点数", "差"))
for name, v in sorted(records.items()):
    #平均点との差を求める
    diff_v = v - ave_v
    #小数点以下を丸める
    diff_v = round(diff_v, 1) #diff_vの値を小数以下1位に四捨五入
    #設定した書式に沿って出力
    print(fmt.format(name, v, diff_v))
else:
    print("こんな器用なこともできるんですよ\n")


#英単語の出現回数を調べる
text = """
He's been watching us since we arrived.
No, she helped me with my homework.
But my daughter's wedding cost me a fortune.
In the morning, I love to have a cup of coffee!
What should we season it with?
"""
# 単語を区切る
#文字列 = 文字列.replace("A", "B") AをBに置き換える
text = text.replace(",", "") #,を削除
text = text.replace(".", "") #.を削除
text = text.replace("!", "") #!を削除
text = text.replace("?", "")  #?を削除
#
words = text.split()  #空白で区切ってリスト型を作成

#単語を数える
counter = {}  #counterという空白の辞書型を作成

for w in words:
    ws = w.lower()  #小文字に変換
    if ws in counter:  #もし辞書型にすでにキーがあれば値を一つ追加
        counter[ws] += 1
    else: #もし辞書型にキーがなければ値を1としてキーを登録
        counter[ws] = 1

#結果を表示
for k, v in sorted(counter.items()):
    if v >= 1: #文字列が1つ以上あれば表示する
        print(k, v) #文字と個数を表示