from django import forms
class registeruser(forms.Form):
    username=forms.CharField(label='name',max_length=20)
    password=forms.CharField(label='yourpassword',max_length=20)
    ageinpassword=forms.CharField(label='agein enter your password',max_length=20)
    phone=forms.CharField(label='your phone',max_length=20)
    email=forms.CharField(label='your email',max_length=30)

class loginform(forms.Form):
    username=forms.CharField(label='name',max_length=20)
    password=forms.CharField(label='password',max_length=20,widget=forms.PasswordInput)

    #type.widget.attrs['hidden']='hidden'
class selectform(forms.Form):
    username = forms.CharField(label='name', max_length = 20)