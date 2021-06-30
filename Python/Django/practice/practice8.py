# 値のソート
# (動物、最高時速)のリスト(各要素はタプルで作成)
animal_list = [
    ("ライオン", 58),
    ("チーター" , 110),
    ("シマウマ" , 60),
    ("トナカイ" , 80),
]

# 足の速い順に並び替える
faster_list = sorted(
    animal_list,
    key = lambda ani : ani[1], # 1はタプル配列の用を番号0の次の1、速度の値のこと
    reverse = True
)

# 結果を表示
print("\nlist型の並び替え")
for i in faster_list : print(i)

# dict型で動物 : 最高速度を指定
animal_dict = {
    "ライオン" : 58,
    "チーター" : 110,
    "シマウマ" : 60,
    "トナカイ" : 80,
}

# 時速で並び替えて表示
faster_dict = sorted(
    animal_dict.items(), # dict型ではitems()で要素をひとつづつ指定する
    key = lambda ani: ani[1],
    reverse  = True
)

# 結果を表示
print("\ndict型の並び替え")
for name, speed in faster_dict : print(name, speed)

print("\ndict -> tuple")
print("dict : " + str(animal_dict))
print("tuple : " + str(animal_dict.items()))