# 無名関数について
# Pythonでは、全ての値がオブジェクトである。
# 故に、関数を変数に代入したり、関数に別名をつけて利用したり、
# 関数の引数に関数を指定したり、監視委の戻り値に別の関数を指定することができる。

# 関数を変数に代入するプログラム
def mul_func(a, b): # aとbを掛け算する関数
    return a * b

def div_func(a, b): #aとbを割り算する関数
    return a / b

def print_join(array):
    textSir = " ".join(array)
    print(textSir)


#mul_func関数を変数に代入
func = mul_func
#代入した変数で関数を使う
result_int = func(2, 3)

textList = []
textList.append("print(result = func(2, 3))")
textList.append("=>")
textList.append(str(result_int))
textList.append(str(type(result_int)))
print_join(textList)

#div_fund関数を変数で扱う
func2 = div_func
#代入した変数で関数を使う
result_float = func2(10, 5)

textList = []
textList.append("print(result = func2(10, 5))")
textList.append(" => ")
textList.append(str(result_float))
textList.append(str(type(result_float)))
print_join(textList)


# 関数の引数に関数を指定するプログラム
# 関数を定義
# 上記にて定義済み
# 引数に関数を要求する関数を定義
def calc_5_3(func):
    return func(5, 3)

result = calc_5_3(mul_func)
print(result)

result = calc_5_3(div_func)
print(result)

# 無名関数の使用
# ２倍する無名関数
double = lambda number : number * 2
print(double(2))

# 三角形の面積を求める無名関数
tri = lambda a, b : a * b / 2
print(tri(5, 8))

# 無名関数で関数の引数を指定する
result = calc_5_3(lambda a, b : a * b)
print("mult " + str(result))

result = calc_5_3(lambda a, b : a + b)
print("add " + str(result))

# 関数の引数に関数を指定する
# リストを生成
nums = [1, 3, 5, 7, 9]
# 値を2倍にする無名関数を定義
x2 = lambda x : x * 2 # 引数xに対し、x*2を実行


# map()を使う
print("map()を使う")
list(map(x2, nums)) # x2に代入された関数オブジェクトを、numsの要素全てに実行し、リスト化
print(list(map(x2, nums)))
print(map(x2, nums))

# もっと短く書く
print("\n\nもっと短く")
nums = [1, 3, 5, 7, 9]
print(list(map(lambda x : x * 2, nums)))
print(list(map(lambda x : x * 3, nums)))
print(list(map(lambda x : x * 4, nums)))

# filter()を使う
print("\n\nfilter()をつかう")
nums = [1,2,3,11,12,13,21,22,23]
# 偶数を抽出する
print(list(filter(lambda x : (x % 2) == 0, nums)))
print(list(filter(lambda x : (x > 13), nums)))
print(list(filter(lambda x : (x < 8), nums)))
print(list(filter(lambda x : x == 4, nums)))