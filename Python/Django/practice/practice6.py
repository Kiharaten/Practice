########## 関数作成とその使い方のまとめ ##########

################ practice 6-1 ################
#関数を定義する
# def 関数名(引数1, 引数2, 引数3, ...):
#
#     関数内での処理
#
#     return 戻り値

# #かけ算をする関数を作成
def mul(a, b):
    '''かけ算をする関数です'''
    return a * b

# print(mul(2, 3)) #mul関数に2と3を引数に戻り値を出力
# print(mul(10, 3)) #mul関数に10と3を渡して戻り値を出力


# #印税を計算する関数を作成
def calc_royality(plices, sales, par):
    rate = par / 100  #印税率を数字から百分率にする
    ro = int(plices * sales * rate) #計算内容を整数型に変更
    return ro
#ユーザーから値を入力してもらう
# i1 = input("販売価格は？")
# i1 = int(i1)
# i2 = input("いくつ売れた？")
# i2 = int(i2)
# i3 = input("印税率は？")
# i3 = float(i3)

# v = calc_royality(i1, i2, i3)
# print("印税は", v, "円です\n")

#関数の便利機能docstring。関数定義の直下に三重符を用いて定義できる
# def 関数名(引数1, 引数2, 引数3, ...):
#     '''関数の説明'''
#     #
#     # 関数内での処理
#     #
#     return 戻り値

#docstringを確認するときはhelp(関数名)で表示することができる
# print(help(mul))


# あるスーパーでのタイムセール
# 14時3つ購入で10%引き、15時5つで20%引きの関数を作る
# Aさん 15時 3つ 1300円分
# Bさん 14時 4つ 2000円分
# Cさん 15時 8つ 5400円分

def timeSales(who, time, count, value):
    '''あるスーパーの割引計算'''

    re_v = "割引はありません"

    if (time == 14) and (count >= 3):
        value *= 0.9
        re_v = "10%割引が適用されます"

    if (time == 15) and (count >= 5):
        value *= 0.8
        re_v = "20%割引が適用されます"

    value = int(value)
    print("{0}さんのお会計は{1}円です。{2}。".format(who, value, re_v))


def convCtoI(char):
    '''文字を全てint型に置き換える。ただし未完成'''

    integer =[
        ['一', 1],
        ['二', 2],
        ['三', 3],
        ['四', 4],
        ['五', 5],
        ['六', 6],
        ['七', 7],
        ['八', 8],
        ['九', 9],
    ]

    unit = [
        ['十', 0],
        ['百', 00],
        ['千', 000],
        ['万', 0000],
    ]

    for i in len(char):
        for j in len(integer):

            char = char.replace("integer[j][0]","integer[j][1]")

    return char


# t1 = input("誰ですか？")
# t2 = input("何時ですか？")
# t2 = t2.replace("時", "") #単位を削除
# t3 = input("幾つ買いましたか？")
# t3 = t3.replace("個", "") #単位を削除
# t4 = input("何円買いましたか？")
# t4 = t4.replace("円", "")  #単位を削除
# # 単位が付いているとエラーになるので消しておく。
# # 漢字を数字に置き換えておく。

# re_n = (timeSales(t1, int(t2), int(t3), int(t4)))
# print(re_n) //関数の最後にprintされているので必要ない行でした
# # inputを使うとstr型になるので、intに変更しておく。
# # help(timeSales)でdocstringを確認できる。


################ practice 6-2 ################
# 引数のデフォルト値を指定する
# 畳の枚数から部屋の大きさ何平方メートルか計算する
# ただし地域によって畳の大きさがかわるので場合によっては入力してもらう

def convert_jou(jou, unit="江戸間"):
    if unit == "江戸間":
        base = 0.88 * 1.76
    elif unit == "京間":
        base = 0.955 * 1.91
    elif unit == "中京間":
        base = 0.91 * 1.82

    m2 = jou * base

    s = "{0}で{1}は{2}平方メートルです".format(unit, jou, m2)
    print(s)

# 関数を実行
# convert_jou(6, "江戸間")
# convert_jou(6, "京間")
# convert_jou(6)

# 最後の引数が一つ足りないのはデフォルト値の江戸間が適用されている。
# 実際に計算する時例外を除いて簡単に処理できるので便利な昨日である。


################ practice 6-3 ################
# 名前付き引数の指定
# pythonでは関数を用いる時に名指しで引数を指定できる

def calcTime(dist, speed):
    """ 距離と速さから時間を求める関数"""
    t = dist / speed
    t = round(t, 1)
    return t

# # 普通に関数を呼び出す
# print(calcTime(500, 100))
# help(calcTime)
# # 次に名前付き呼び出し
# print(calcTime(dist=500,speed=100))
# # docstring で説明ちゃんと書いといたら楽できる


################ practice 6-4 ################
# 可変長引数の利用
# python の引数では可変長で引数を指定できる。

def sumArgs(*args):
    v = 0
    for n in args:
        v += n
    return v

#結果を表示
print(sumArgs(1, 2, 3))
print(sumArgs(10, 20, 30, 40, 50))
# 便利なことに引数がいくらあっても全部処理してくれる
