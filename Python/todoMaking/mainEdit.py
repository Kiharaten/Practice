def Edit(taskList):
    taskListLen = len(taskList) - 1

    if taskListLen == -1:
        print('タスクはまだ一つもありませんよ')
        return

    taskCheck = int(input('タスク番号を教えてください\n>'))
    if (taskCheck < 0) or (taskListLen < taskCheck):
        print(' そのタスクは存在しません ')

    taskReplace = input('どの項目を修正しますか？')
    taskList[0].replaceAchieve(taskReplace)
    taskList[0].viewDetail()
    print('このように変更されました')

def hogehoge():
    print('読み込み確認')
    return
