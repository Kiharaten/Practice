import datetime

from django.db import models
from django.utils import timezone

# Create your models here.

class Question(models.Model):
    question_text = models.CharField(max_length=200)
    pub_date = models.DateTimeField('datepublished')
    def __str__(self):
        return self.question_text

    def was_published_recently(self):
        return self.pub_date >= timezone.now() - datetime.timedelta(days=1)

# --
# -- Create model Question
# --
# CREATE TABLE `polls_question` (
#   `id` integer AUTO_INCREMENT NOT NULL PRIMARY KEY, 
#   `question_text` varchar(200) NOT NULL, 
#   `pub_date` datetime(6) NOT NULL
# );

class Choice(models.Model):
    question = models.ForeignKey(Question, on_delete=models.CASCADE)
    choice_text = models.CharField(max_length=200)
    votes = models.IntegerField(default=0)
    def __str__(self):
        return self.choice_text

# --
# -- Create model Choice
# --
# CREATE TABLE `polls_choice` (
#   `id` integer AUTO_INCREMENT NOT NULL PRIMARY KEY, 
#   `choice_text` varchar(200) NOT NULL, 
#   `votes` integer NOT NULL, 
#   `question_id` integer NOT NULL
# );
# ALTER TABLE `polls_choice` 
# ADD CONSTRAINT `polls_choice_question_id_c5b4b260_fk_polls_question_id` 
# FOREIGN KEY (`question_id`) 
# REFERENCES `polls_question` (`id`);