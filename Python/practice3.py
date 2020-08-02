########## index型とそれらの小技まとめ ##########

################ practice 3-1 ################
#test of list

num = [10, 20, 30, 40]
for i in range(4):
    print(num[i])
else:
    print(type(num[0]))
    print("\n")

char = ['one', 'two', 'three', 'four']
for i in range(4):
    print(char[i])
else:
    print(type(char[0]))
    print("\n")

decimal = [0.1, 0.2, 0.3, 0.4]
for i in range(4):
    print(decimal[i])
else:
    print(type(decimal[0]))
    print("\n")

#このように"list"機能は配列と同じように扱われる
# ※ 厳密には配列、arrayとは別なので注意！

val_list = [10, 'one', 0.1]
print(val_list[0])
print(val_list[1])
print(val_list[2])
print(type(val_list[0]))
print(type(val_list[1]))
print(type(val_list[2]))

print("\n")

# 配列とは別物なので、型がバラバラでもOK。
# 一応array型も存在する。

fruits = ["BANANA", "APPLE", "ORANGE", "MERON", "GRAPE",
          "STRAWBERRY", "PEACH", "LEMON", "PINEAPPLE", "KIWI"]

print("\"10の果実\"")
for i in range(10):
    print(fruits[i])
else:
    print("\n")

points = [70, 80, 90,]
print(sum(points))
print(sum(points) / len(points))

# edit list
# リストへ新たな値を追加する時、append()を用いる

nums = [1, 2, 3]

nums.append(4)
nums.append(5)

print(nums)

#国語の点数の一覧
points = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]

#30点未満は赤点なので赤点リストに追加
akaten = []

#このfor文はpointsから要素数分内容を一つづつ取り出して処理している。
for n in points:
    if n < 30:
        akaten.append(n)

#赤点リストを表示
print(akaten)

# print test
# print("aaaaa")
# print("bbbbb")
# print("aaaaa" + "bbbbb")
# print("{0}個aaaaa".format(5))

#リストの結合
n1 = [1, 2, 3]
n2 = [4, 5, 6]
n3 = n1 + n2
print(n3)

m1 = [1, 2, 3]
m1 += [4, 5, 6]
print(m1)

l1 = [1, 2, 3]
l1.extend([4, 5, 6])
print(l1)

#リストのスライス
a = [11, 22, 33, 44]
print(a[0:3])  #0から数えて0件目から3件目の手前までを出力

#スライスのテクニック
num = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(num[:3])
print(num[7:])

print(num[-1])
print(num[-5])
print(num[-4:])

#ステップ数を加えたスライス
print(num[2:8:2])
print(num[::3])

del num[2]
print(num)  #←2が消えている

num = [1,2,3,4,5,6,7,8,9]
del num[0:9:2]
print(num)

#この他に
# remove() : 削除
# extend() : リスト延長
# insert(i:x) : i番目にxを挿入
# pop() ; 取り出してから一件削除
# index(x) : xの要素番号を取り出す
# count(x) : リストの中にxが何回出るか数える
# sort(key, reverse) : リストの内容を降順に切り替える True で昇順
# copy() : リストの複製（浅いコピー）を返す
# copy.deepcopy() : これで（深いコピー）を返すことができる

#リストの親戚tuple
#tupleを生成
a = (10, 20, 30)
print(a[1])  #10 は a[0]

#スライスしてみる
print(a[:2])

#一見listとそっくりだが、内容の変更はできない。
#a[1] = 50

# 案ずる事なかれ、list と tuple は変換することができる
# list(タプル)
# tuple(リスト)
#これらで相互に変換することができる。

#さらにlost、tuple に似た集合型として,set がある
#このset型は重複する値を持てず、順序をつけることもできないが、
#結合・交差・差分・対称差などの数学的操作が可能である

#集合型を生成
colors = {"red", "blue", "yellow"}
fluits = set({"Apple", "Banana", "Orange"})
print(fluits)

#それではset 型の操作を行っていく。
box1 = {'ハンマー', '釘', 'ペンチ'}
box2 = {'釘', 'ペンチ'}
#それではset集合型の”差分”を活用していく。
print(box1 - box2)  #すっごいでしょ？

#要素が集合に含まれるかを調べる。
print('ペンチ' in box1)  #存在するとTrue、なければFalseを返す。

#結合する
box3 = {'ドライバー', 'ノコギリ'}
print(box1 | box3)

#共通する値だけ取り出す
print(box1 & box2)

#このように器用に操作することが可能である。

# 集合型まとめ
# リスト list 複数の値を持つことができ、汎用性が高い。
# タプル tuple 要素を変更できないリストで、固定値などに使う。
# 集合型 set 要素を超低できないタプルだが、器用な操作性を持つ。
# これらは、list(tuple), tuple(set), set(list) で相互に変換することができる。







