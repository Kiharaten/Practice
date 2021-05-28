print("""今から冒険が始まります！
あなたのステータスを,それぞれ1~100の間で入力してくださいね！""" )

attack = int(input("まず攻撃力を入力"))
deffence = int(input("次に防御力を"))
hitpoint = int(input("最後に体力を入力してください"))


if attack > 100 and deffence > 100 and hitpoint > 100:
    text = "気に入らないので全てゼロにします"
    attack = 0
    deffence = 0
    hitpoint = 0

elif attack > 100:
    text = "攻めすぎです！"
    attack = attack * 50
    deffence = deffence * 100
    hitpoint = hitpoint * 100

elif deffence > 100:
    text = "守りすぎです！"
    attack = attack * 100
    deffence = deffence * 50
    hitpoint = hitpoint * 100


elif hitpoint > 100:
    text = "なんなんですかあなたは！"
    attack = attack * 100
    deffence = deffence * 100
    hitpoint = hitpoint * 50


elif attack == 0 and deffence == 0 and hitpoint == 0:
    text = "全てゼロということは、何にでもなれるという意味でもあるのです。"
    attack = 9999
    deffence = 9999
    hitpoint = 9999


else:
    text = "いいバランスですね。"
    attack = attack * 80
    deffence = deffence * 80
    hitpoint = hitpoint * 80


print("{text}発表します。あなたのステータスは、".format(text = text))
print("""
攻撃力 {0}
防御力 {1}
体力 {2}""" .format(attack, deffence, hitpoint))

print("のようです！頑張ってくださいね！")

print("最初のダンジョン")
array1 = [
    "野生",
    "ごまめ",
    "1",
]
print("{0}の{1}LV.{2}が現れた！".format(array1[0], array1[1], array1[2]))

for char in 'hello':
    print(char)
