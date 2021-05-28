class chooseNumAndAnswerCharacter:
    def __init__(self, num, char):
        self.chooseNum = num
        self.ansChar = char

class yourChoose:
    def __init__(self, choose):
        self.chooseNum = choose

    def slider(self, choose):
        self.chooseNum = choose

class inputCheck:

    def checkChooseIsNum(self):
        if not (order.chooseNum.isdecimal()):
            print("※入力エラー※\n英数字で入力してください！")
            exit()

    def checkChooseIn2_7(self):
        if not (2 <= int(order.chooseNum) <= 7):
            print("※入力エラー※\n2〜7の範囲で入力してください！")
            exit()

    def checkChooseIn9_13(self):
        if not (9 <= int(order.chooseNum) <= 13):
            print("※入力エラー※\n9〜13の範囲で入力してください！")
            exit()
