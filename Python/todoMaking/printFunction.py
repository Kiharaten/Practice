# import main as main

def format1(dict_obj):
    # お の時11
    # ほげほげ の時14
    # お布団をたたみます の時19
    # 10+len(日本語文字列)
    fmtJpn = '| {0:<20} : {1:>40} |'
    fmtEng = '| {0:<20} : {1:>46} |'

    for text in dict_obj:

        if text == 'title': # タイトルだけ別形式で表示
            print('\n|' + format(dict_obj['title'], '-^71') + '|')
        elif text == 'howTodo': # 日本語が化けるのでここだけ対策
            print(fmtJpn.format(str(text), str(dict_obj[text])))
        else: # 英字メインだと綺麗に出力されるんですけどね...
            print(fmtEng.format(str(text), str(dict_obj[text])))

    return

# def format2(speak_text):


def inputLine():
    order = input('\n>')
    return order