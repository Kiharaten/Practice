class Apple:
    def __init__(self, w, c):
        self.weight = w
        self.color = c

    def printf(self):
        print("このリンゴはcolor {0}で、weight {1}なリンゴ".format(self.color,self.weight))

    def change_type(self, w, c):
        self.weight = w
        self.color = c

apple1 = Apple(10, 'red')
apple1.printf()
print(apple1.weight)

apple2 = Apple(20, 'blue')
apple2.printf()

apple2.change_type(1000, 'green')
apple2.printf()