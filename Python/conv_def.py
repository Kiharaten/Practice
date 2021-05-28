def convCtoI(char):
    '''漢数字を全てint型に置き換える'''
    re_num = []

    integer =[
        ['一', '1'],
        ['二', '2'],
        ['三', '3'],
        ['四', '4'],
        ['五', '5'],
        ['六', '6'],
        ['七', '7'],
        ['八', '8'],
        ['九', '9'],
    ]

    unit = [
        ['十', '0'],
        ['百', '00'],
        ['千', '000'],
        ['万', '0000'],
        ['億', '00000000'],
        ['兆', '000000000000'],
    ]

    for i in char:
        for j in range(len(integer)):

            if i == integer[j][0]:
                char = char.replace(integer[j][0], integer[j][1])

        for k in range(len(unit)):
            if i == unit[k][0]:
                char = char.replace(unit[k][0],unit[k][1])

    return int(char)


ger_v = convCtoI(input("漢数字を入力してください\n"))
print(ger_v)
print(type(ger_v))