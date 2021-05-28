#if文を追加
height = input('身長は？(cm)')
weight = input('体重は？(kg)')
bmi = float(weight) / ((float(height) / 100) ** 2)
print('身長:' + str(height) + 'cm 体重:' + str(weight) + 'kg')
print('BMI:' + str(bmi))
if bmi < 18.5:
    print('低体重（痩せ）')
elif bmi < 25.0:
    print('標準')
elif bmi < 30.0:
    print('肥満（１度）')
elif bmi < 35.0:
    print('肥満（２度）')
elif bmi < 40.0:
    print('肥満（３度）')
else:
    print('肥満（４度）')