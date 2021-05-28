import numpy as np
from matplotlib import pyplot
x = np.linspace(0, 2 * np.pi, 100)
y_sin = np.sin(x)
y_cos = np.cos(x)

pyplot.plot(x, y_sin, label='sin', color='red',
            linestyle='solid', linewidth=1.0, marker='o')

pyplot.plot(x, y_cos, label='cos', color='blue', linestyle='dashed')

pyplot.title('Sin & Cos graph')
pyplot.xlabel('X-Axis')
pyplot.ylabel('Y-Axis')
pyplot.legend()
pyplot.grid()

pyplot.show()