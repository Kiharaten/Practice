import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("data.csv", sep=",") #index_col = "date_time")

data.plot()
plt.show()

# これだけ。
