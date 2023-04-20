package com.fitwebappclient.loginandregistration.loginpage;



public class LoginPageViewModel extends ViewModel   {


    public LoginPageViewModel(Activity activity) {

    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus.setValue(loginStatus);
    }

    public LiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {

    }

    public void logIntoAccount(String userLogin, String userPassword){

    }

    public void setConnectionErrorAlert() {

    }
}