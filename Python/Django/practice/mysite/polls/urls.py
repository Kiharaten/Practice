from django.urls import path

from . import views

app_name = 'polls'
urlpatterns = [
    path('', views.index, name='index'),
    path('specifics/<int:question_id>/', views.detail, name='detail'),
    path('<int:question_id>/results/', views.results, name='results'),
    path('<int:question_id>/vote/', views.vote, name='vote'),

    # ex:
    # index   -> /polls/
    # detail  -> /polls/specifics/5/
    # results -> /polls/5/results/
    # vote    -> /polls/5/vote/
]