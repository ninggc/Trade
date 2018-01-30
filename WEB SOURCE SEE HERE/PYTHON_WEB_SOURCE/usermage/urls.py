from django.conf.urls import url,include
import views
from django.contrib.staticfiles.urls import staticfiles_urlpatterns
from django.contrib import staticfiles
urlpatterns = [
    url(r'^adduser/$',views.adduser,name='adduser'),
    url(r'^login/$',views.login,name='login'),
    url(r'^select/$',views.select,name='select'),
    ]
urlpatterns += staticfiles_urlpatterns()