# テキストから<～>で挟まれた部分を削除する
def remove_htmlTag(text):
    check = True # whileループの終了条件に使用
    word_s = '<' # タグの先頭<を検索する時に使用
    word_e = '>' # タグの末尾>を検索する時に使用

    # <>のセットが無くなるまでループ
    while check:
        start = text.find(word_s) # <が何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e) # >が何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は<と>で囲まれた範囲を空白に置換

        remove_word = text[start:end + 1] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text

# 和暦から西暦にする
def remove_htmlTag(text):

    text_data = open("./hoge.rtf", "r")

    # 行ごとにすべて読み込んでリストデータにする
    lines = text_data.readlines()

    # ファイルをクローズする
    text_data.close()

    for i in lines:
        lines[i] = text.replace('昭和', '')
        lines[i] = text.replace('大正', '')
        lines[i] = text.replace('平成', '')


    for line in lines:
        print line
    return