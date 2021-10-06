package com.tanuz.practico1_lab3.ui.ui.Login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tanuz.practico1_lab3.model.Usuario;
import com.tanuz.practico1_lab3.request.ApiClient;
import com.tanuz.practico1_lab3.ui.ui.Registrar.RegistrarActivity;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String>error;

    private ApiClient apiClient;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
        this.apiClient= new ApiClient();
    }

    public LiveData<String> getError(){
        if(error == null){
            error= new MutableLiveData<>();
        }
        return error;
    }

    public void autenticar(String mail, String pass){
        Usuario usuario = apiClient.login(context, mail, pass);
        if (usuario != null){
            error.setValue("");
            Intent intent=new Intent(context, RegistrarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario",usuario);
            context.startActivity(intent);
        }
        else {
            error.setValue("Email o Password incorrecto ");
        }

    }
    public void aRegistrar(){
        Intent intent=new Intent(context, RegistrarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
