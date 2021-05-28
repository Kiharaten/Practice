import datetime
import printFunction as printf

def Make():
    makeOrder = [
        str(input('タスクの説明文を入力してください？ 例: 宿題する\n>')),
        datetime.date.fromisoformat(input('タスクの期日を決めてください？ 例: 2020-02-02\n>')),
        int(input('タスクの優先度を決めてください？ 例: 1 〜 5\n>')),
        datetime.time.fromisoformat(input('タスクの所要時間はどのくらいですか？ 例: 00:30\n>'))
    ]

    printf.format1({'title': ' タスクを作成しています '})

    main.taskList.append(main.Task(makeOrder[0],makeOrder[1],makeOrder[2],makeOrder[3]))
    main.taskList[0].viewDetail()
    taskListLen = taskListLen + 1
    printf({'title': ' タスクが作成されました '})

def hogehoge():
    print('読み込み確認')
    return
