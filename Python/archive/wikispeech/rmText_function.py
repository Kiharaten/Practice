# テキストから<～>で挟まれた部分を削除する関数
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


# これ以降はタグごとの削除になってます。
# 開始タグはclassなどのオプションケアで > までに限定していません。

def remove_scriptContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<script' # タグの開始位置を検索する時に使用
    word_e = '</script>' # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 9] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text

def remove_h1Content(text):
    check = True # whileループの終了条件に使用
    word_s = '<h1' # タグの開始位置を検索する時に使用
    word_e = '</h1>' # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 5] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text

def remove_titleContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<title' # タグの開始位置を検索する時に使用
    word_e = '</title>' # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 8] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text


def remove_divContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<div' # タグの開始位置を検索する時に使用
    word_e = '</div>' # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 6] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text


def remove_bracketsContent(text):
    check = True # whileループの終了条件に使用
    word_s = '（' # タグの開始位置を検索する時に使用
    word_e = '）' # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 1] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text


def remove_brackets2Content(text):
    check = True # whileループの終了条件に使用
    word_s = '&#91;'  # タグの開始位置を検索する時に使用
    word_e = '&#93;'  # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 5] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text


def remove_beforeContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<body'  # タグの開始位置を検索する時に使用
    word_e = '<body>'  # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 0] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text


def remove_afterContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<h2'  # タグの開始位置を検索する時に使用
    word_e = '</body>'  # タグの終了位置を検索する時に使用

    # <a>タグが無くなるまでループ
    for i in range(5):
        start = text.rfind(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break
            # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換

        remove_word = text[start:end + 0] # 削除するワードをスライス
        text = text.replace(remove_word, '')  # remove_wordを空白に置換

    return text


def remove_special_text(text):
    words = [
        '出典: フリー百科事典『ウィキペディア』',
        'ナビゲーションに移動',
        '検索に移動',
    ]
    for word in words :
        text = text.replace(word, '')

    return text

