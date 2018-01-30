from django.conf.urls import url,include
import views
from django.conf.urls import include, url
from django.conf import  settings
from django.conf.urls.static import static
urlpatterns = [
    url(r'^sell/$',views.sell,name='sell'),
    ]
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)