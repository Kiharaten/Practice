import datetime
import json

import printFunction as printf

import mainHelp as help
import mainMake as make
import mainCheck as check
import mainEdit as edit
import mainEnd as end

import mainTask as task

test_obj = task.Task('ほげほげ', '2020-02-02', '1', '00:30')
test_obj.viewDetail()

# 時間があれば、タスクを同じ意味の所要時間の短い複数のタスクに置き換える関数も作りたい。

# ------------------ 対話型実行部分 ------------------ #
# taskList = []
# taskList.append('Null')

# print(' 私は対話型タスク管理ができます。名前はまだ与えられていません ')
# while 1:
#     printf.format1({'title': ' 何をお手伝いしましょうか？ '})
#     print(len(taskList))
#     order = input('\n>')

#     if order == 'help':
#         # help.hogehoge()
#         help.Help()


#     if order == 'make':
#         pass
#         # make.hogehoge()

#     if order == 'check':
#         # check.hogehoge()
#         check.Check(taskList)


#     if order == 'edit':
#         edit.Edit(taskList)
#         # edit.hogehoge()


#     if order == 'end':
#         # end.hogehoge()
#         end.End()
#         break


# ------------------ 対話型実行部分 ------------------ #




# print(datetime.date(2020, 3, 2))
# print(type(datetime.date(2020, 3, 2)))

# print('\n\ntest partition\n\n')

# value = "2020-02-02"
# print(type(value))
# print(type(datetime.date.fromisoformat(value)))

# value = "12:30"
# print(type(value))
# print(type(datetime.time.fromisoformat(value)))
