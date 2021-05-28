class Task():
    """ this class can making task object that has howTodo, achieveDate, priorityPoint, requiredTime. """
    def __init__(self, howTodoThisTask, achievementDate, priorityPoint, requiredTime):
        self.howTodo = howTodoThisTask # type_str
        self.achieve = achievementDate # type_date
        self.priority = priorityPoint # type_int
        self.required = requiredTime # type_time

    def viewDetail(self):
        """ check your task detail as converted str objects """
        print({
            'title' : ' task info ',
            'howTodo' : self.howTodo,
            'achieve' : self.achieve,
            'priority' : self.priority,
            'required' : self.required
        })
        return

    def replaceHowTodo(self, value):
        self.howTodo = value
        return

    def replaceAchieve(self, value):
        self.achieve = value
        return

    def replacePriority(self, value):
        self.achieve = value
        return

    def replaceRequired(self, value):
        self.required = value
        return
