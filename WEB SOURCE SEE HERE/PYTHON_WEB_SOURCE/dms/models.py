# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from .storage import ImageStorage
from django.db import models
from django.db.models.fields.related import ManyToManyField, ForeignKey, OneToOneField


class city(models.Model):
    province = models.CharField(max_length=20)
    country = models.CharField(max_length=20)
    cityname = models.CharField(max_length=20)


class location(models.Model):
    extra = models.CharField(max_length=20)
    cityid = ForeignKey(city)


class security(models.Model):
    password = models.CharField(max_length=15)
    email = models.CharField(max_length=45)
    tel = models.CharField(max_length=15)


class campus(models.Model):
    name = models.CharField(max_length=45)


class user(models.Model):
    username = models.CharField(max_length=45)
    gender = models.CharField(max_length=1, blank=True)
    age = models.IntegerField()
    introduce = models.CharField(max_length=45, blank=True)
    unique = models.CharField(max_length=45)
    portrait = models.CharField(max_length=45, blank=True)
    campus_id = ForeignKey(campus)
    security_id = ForeignKey(security)
    addressid = ForeignKey(location, related_name='addressid')  # 收件人地址
    locationid = ForeignKey(location, related_name='locationid')  # 家乡地址


class commodity(models.Model):
    user_id = ForeignKey(user)
    location_id = ForeignKey(location)
    name = models.CharField(max_length=20)
    sort = models.IntegerField()
    price = models.PositiveIntegerField()
    note = models.CharField(max_length=45)
    img = models.ImageField(upload_to='./commodityimg/', storage=ImageStorage())


class collection(models.Model):
    user_id = ForeignKey(user)
    commodity_id = ForeignKey(commodity)


class indent(models.Model):
    purchase = ForeignKey(user)
    commodity_id = ForeignKey(commodity)
    location_id = ForeignKey(location)
    datetime = models.TimeField()
    remask = models.CharField(max_length=45)


class delegation(models.Model):
    title = models.CharField(max_length=45)
    publisher = ForeignKey(user)
    description = models.CharField(max_length=45)


class delegation_order(models.Model):
    purchaser = ForeignKey(user)
    delegation_id = ForeignKey(delegation)
    datetime = models.TimeField()
    remask = models.CharField(max_length=45)
# Create your models here.
