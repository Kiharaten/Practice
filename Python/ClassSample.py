# coding:utf-8

class TextControl:
    def __init__(self, type, *text):
        self.text = []
        for i in text:
            self.text.append(i)

        # print("self.text = {0}".format(self.text))
        
    def print_all(self):
        for i in self.text:
             print("{0}, ".format(i), end = "")
        else:
            print("")

    def add_data(self, *text):
        for i in text:
            self.text.append(i)
        else:
            print("")

        # print("self.text = {0}".format(self.text))

hoge = TextControl("japanese", "sakana", "yamashita", "moka", "konoha")
hoge.print_all()
hoge.add_data("rest", "nagi", "rai")
hoge.print_all()