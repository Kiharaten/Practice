import requests

# url = requests.get('https://news.yahoo.co.jp')
# main_params = '東北ずん子'
# sub_params = 'クズん子'

# param = 'p=' + main_params + '+' + sub_params
# r = requests.get(url,params=param)

# print(r.headers)
# print("--------")
# print(r.encoding)
# print(r.content)

url = 'https://www.google.co.jp/search'
q_str = input("値を入力")

params = {'q': q_str, 'tbm': 'isch'}

r = requests.get(url, params=params)
print(r.text)

# url = 'http://www13.plala.or.jp/bigdata/google.html'

# r = requests.get(url)
# print(r.text)