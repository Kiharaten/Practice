#coding:utf-8

import json
int_dic = {'A':100, 'B':200, 'C':300}
strE_dic = {'A':'abcd', 'B':'efgh', 'C':'ijkl'}
strJ_dic = {'A':'あいうえお', 'B':'かきくけこ', 'C':'さしすせそ'}

# print(int_dic)
# print(int_json)
# print(strE_dic)
# print(strE_json)
# print(strJ_dic)
# print(strJ_json)

file = []
file.append(open('int.json', 'w'))
file.append(open('strE.json', 'w'))
file.append(open('strJ.json', 'w'))

json.dump(int_dic, file[0], ensure_ascii=False, indent=4)
json.dump(strE_dic, file[1], ensure_ascii=False, indent=4)
json.dump(strJ_dic, file[2], ensure_ascii=False, indent=4)

for i in file:
    i.close()

# test = open('test.json', 'w')
# json = json.load(test)

# print(json)