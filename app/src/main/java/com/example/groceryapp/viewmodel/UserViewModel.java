package com.example.groceryapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserViewModel extends ViewModel {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    //adding login failure
    private final MutableLiveData<Boolean> loginFailed = new MutableLiveData<>();

    public void loginUser(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                user.setValue(firebaseAuth.getCurrentUser());
                loginFailed.setValue(false);
            }
            else {
                user.setValue(null);
                loginFailed.setValue(true);
            }
        });
    }
    public LiveData<FirebaseUser> getUser(){
        return user;
    }

    public LiveData<Boolean> getLoginFailed(){
        return loginFailed;
    }
}