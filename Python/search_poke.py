def search(arg):
    '''ポケモンナンバーから個体を割り出すプログラム'''
    val = int(arg)
    if not(1 <= val <= 151):
        return False

    num1 = 1
    num2 = 151

    ans = divFunc(num1, num2, val)
    # print("ポケモンナンバーは{0}です。".format(ans))



def divFunc(minInt, maxInt, num):
    '''なんちゃって二分探索プログラム'''

    minInt = int(minInt)
    maxInt = int(maxInt)
    num = int(num)

    if num < minInt or maxInt < num:
        return False


    leng = maxInt - minInt + 1
    divNum = 0

    while leng != num:
        div = int(leng / 2) + minInt

        if num < div:
            maxInt = div - 1
        else:
            minInt = div

        leng = maxInt - minInt + 1
        divNum += 1

    else:
        print("この{0}は、{1}回のループを経て答えを見出す。".format(num, divNum))

# for I in range(0,17):
#     num = I * 9
#     search(num)

# divFunc(1,2,input("探索する数字を入力してね"))
print(int(float(input("float型を入力してね"))))
#

# 簡単な図を添えておきます
# div1|                      0|                      1| :75
# div2|          2|          3|          4|          5| :37
# div3|    6|    7|    8|    9|   10|   11|   12|   13| :18
# div4|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29| : 9
# div