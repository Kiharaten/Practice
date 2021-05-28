# テキストから<～>で挟まれた部分を抽出する関数
def pickup_anchorContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<a' # タグの開始位置を検索する時に使用
    word_e = '</a>'  # タグの終了位置を検索する時に使用
    respick = []

    # <>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break

        # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換
        pickup_word = text[start: end + 4]  # 抽出するワードをスライス
        respick.append(pickup_word)
        text = text.replace(pickup_word, '')  # pickup_wordを空白に置換

    return respick


def pickup_listContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<li' # タグの開始位置を検索する時に使用
    word_e = '</li>'  # タグの終了位置を検索する時に使用
    respick = []

    # <body>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break

        # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換
        pickup_word = text[start: end + 5]  # 抽出するワードをスライス
        respick.append(pickup_word)
        text = text.replace(pickup_word, '')  # pickup_wordを空白に置換

    return respick


def pickup_bodyContent(text):
    check = True # whileループの終了条件に使用
    word_s = '<body>' # タグの開始位置を検索する時に使用
    word_e = '</body>'  # タグの終了位置を検索する時に使用
    respick = []

    # <>タグが無くなるまでループ
    while check:
        start = text.find(word_s)  # 開始タグが何番目の指標かを検索
        if start == -1:
            break
        # word_sが存在する場合

        end = text.find(word_e)  # 終了タグが何番目の指標かを検索
        if end == -1:
            break

        # word_sとword_eの両方がセットである場合は囲まれた範囲を空白に置換
        pickup_word = text[start: end + 7]  # 抽出するワードをスライス

    return pickup_word