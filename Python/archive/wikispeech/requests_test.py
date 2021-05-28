#ライブラリの読み込み
import requests
import sys
import bs4
import rmText_function as rm
import pickText_function as pi

#ファイル実行時に入力された引数をリストとしてargsに格納
args = sys.argv

#URLの設定
# url = 'https://www.google.co.jp/search'#...URL of google
url = 'https://ja.wikipedia.org/wiki/'#...URL of wikipedia


#呼び出し時の引数を配列に加える
q_str = args[1]

# params = {'q': q_str, 'tbm': 'isch'}
# r = requests.get(url, params=params)
# ...use with google

#クエリ実行
r = requests.get(url + q_str)
# ...use with wikipedia

#取ってきた結果を整える
string = r.text

# string = pi.pickup_bodyContent(string)

string = rm.remove_titleContent(string)
string = rm.remove_h1Content(string)
string = rm.remove_beforeContent(string)
string = rm.remove_afterContent(string)
string = rm.remove_scriptContent(string)
string = rm.remove_htmlTag(string)
string = rm.remove_bracketsContent(string)
string = rm.remove_brackets2Content(string)
string = rm.remove_special_text(string)
string = string.strip()


#結果を出力する
# print(r.text) formed html
print(string) # formed string


# googleの検索文字列一覧
# http://www13.plala.or.jp/bigdata/google.html