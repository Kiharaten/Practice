import requests

url = "https://notify-api.line.me/api/notify"
token = "9AehjRbRMbg1n9vSiI02u1vhsKbaqz2mvEX78J3rIQP"
head = {"Authorization" : "Bearer " + token}
msg = "\n"
payload = {"message" : msg + "確認です"}

r = requests.post(url, headers = head, params = payload)

print(type(r), r)