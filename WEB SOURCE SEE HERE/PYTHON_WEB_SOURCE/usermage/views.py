# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.contrib.sessions import serializers
from django.http import HttpResponseRedirect, HttpResponse
from django.shortcuts import render
from .forms import registeruser, loginform, selectform
from dms.models import city, location, security, campus, user, commodity, collection, indent, delegation, \
    delegation_order
from dms import models

import json


def adduser(request):
    if request.method == 'POST':
        form = registeruser(request.POST)
        print form
        print 'post'
        if form.is_valid():
            print type(user.objects.filter(username=form.cleaned_data['username']))
            if form.cleaned_data['password'] == form.cleaned_data['ageinpassword']:
                print 'password is right'
            else:
                # print "password error"
                information = 'ok'
                return HttpResponse(information)
            if user.objects.filter(username=form.cleaned_data['username']):
                # print "yonghuchongfu"
                information = '用户名已经存在'
                return render(request, 'usermas/regins.html', {'information': information})

            if campus.objects.filter(name='default'):
                default = campus.objects.get(name='default')
                # print 'have default'
            else:
                default = campus(name='default')
                default.save()
                # print 'no default'
            if location.objects.filter(extra='default'):
                defaultlocation = location.objects.get(extra='default')
                # print 'have default'
            else:
                defaultcity = city(province='default', country='default', cityname='default')
                defaultcity.save()
                defaultlocation = location(extra='default', cityid=defaultcity)
                defaultlocation.save()
                # print 'no default'
            uniquequery = request.POST.get('unique', 'null')
            mysecurity = security(password=form.cleaned_data['password'], tel=form.cleaned_data['phone'],
                                  email=form.cleaned_data['email'])
            mysecurity.save()
            myuser = user(username=form.cleaned_data['username'], age=0, unique=uniquequery, security_id=mysecurity,
                          campus_id=default, addressid=defaultlocation, locationid=defaultlocation)
            myuser.save()

            information = 'save ok'
            return HttpResponse(information)
        else:
            return HttpResponse('errot')
    else:

        return render(request, 'usermas/regins.html')
        # return HttpResponse('error')


def login(request):
    if request.method == 'POST':
        form = loginform(request.POST)
        if form.is_valid():
            print 'rrr'
            myuser = user.objects.filter(username__exact=form.cleaned_data['username'],
                                         security_id__password__exact=form.cleaned_data['password'])
            if myuser:
                information = 'wellcome ' + form.cleaned_data['username']
                return HttpResponse(information)
            else:
                information = 'password or username error'
                return render(request, 'usermas/login.html', {'information': information})
        else:
            print 'ssss'
            information = 'fei fa'
            return render(request, 'usermas/login.html', {'information': information})
    else:

        return render(request, 'usermas/login.html')


# !Author Mr.Ning
def select(request):
    information = "???"
    if request.method == 'POST' or request.method == 'GET':
        form = selectform(request.POST)
        # if form.is_valid():
        id = form.cleaned_data['user_id']
        users = user.objects.filter(username__exact='ning')
        if users:
            information += 'success'
        else:
            information += 'wrong'

        return HttpResponse(information)

# Create your views here.
