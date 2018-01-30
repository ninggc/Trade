# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from .forms import commodityform
from django.shortcuts import render
from django.http import HttpResponseRedirect,HttpResponse
from dms.models import city,location,security,campus,user,commodity,collection,indent,delegation,delegation_order
def sell(request):
    if request.method=='POST':
        form=commodityform(request.POST,request.FILES)
        if form.is_valid():
            myuser=user.objects.get(pk=1)
            mylocation=location.objects.get(pk=1)
            mycommodity=commodity(user_id=myuser,location_id=mylocation,name=form.cleaned_data['name'],sort=form.cleaned_data['sort'],price=form.cleaned_data['price'],note=form.cleaned_data['note'],img=form.cleaned_data['img'])
            mycommodity.save()
            print'ok'
        return HttpResponse('commodity ok')
    else:
        form=commodityform()
        return render(request,'maket/sell.html',{'form':form})
# Create your views here.
