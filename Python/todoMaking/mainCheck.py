def Check(taskList):

    taskListLen = len(taskList) - 1

    if taskListLen == 0:
        print('タスクはまだ一つもありませんよ')
        return

    taskCheck = int(input('タスク番号を教えてください\n>'))

    if (taskCheck < 0) or (taskListLen < taskCheck):
        print('そのタスクは存在しません')
        return

    taskList[taskCheck].viewDetail()
    print('タスク' + str(taskCheck) + '番はこのようになっています')

def hogehoge():
    print('読み込み確認')
    return
