from django import forms
class commodityform(forms.Form):
    country=forms.CharField(label='country',max_length=20)
    province=forms.CharField(label='province',max_length=20)
    cityname=forms.CharField(label='cityname',max_length=20)
    extra=forms.CharField(label='extra',max_length=20)
    name=forms.CharField(label='name',max_length=20)
    sort=forms.IntegerField(label='sort')
    price=forms.IntegerField(label='price')
    note=forms.CharField(label='note',max_length=20)
    img=forms.ImageField()
    type=forms.IntegerField(label='type',max_value=2)
    type.widget.attrs['hidden']='hidden'
    
    