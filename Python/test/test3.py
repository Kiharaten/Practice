#簡単な入出力
height = input('身長は？(cm)')
bmi = 22
std_weight = bmi * (float(height) / 100) ** 2
print('身長:' + str(height) + 'cm')
print('標準体重:' + str(std_weight) + 'kg')