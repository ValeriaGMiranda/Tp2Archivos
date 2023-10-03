package com.valesoft.tp1sharedpreferences.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.valesoft.tp1sharedpreferences.model.Usuario;
import com.valesoft.tp1sharedpreferences.request.ApiClient;
import com.valesoft.tp1sharedpreferences.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public void loguearse(String usuario, String password){
        Usuario user= null;

        user = ApiClient.login(context,usuario,password);

        if(user == null){
            Toast.makeText(context, "Usuario o Contraseña inválidos", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
