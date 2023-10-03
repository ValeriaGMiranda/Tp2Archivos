package com.valesoft.tp1sharedpreferences.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.valesoft.tp1sharedpreferences.model.Usuario;
import com.valesoft.tp1sharedpreferences.request.ApiClient;
import com.valesoft.tp1sharedpreferences.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario>mUsuario;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public  void mostrarUsuario(){
        Usuario usuario = null;
        usuario = ApiClient.leer(context);

        if(usuario != null){
            mUsuario.setValue(usuario);
        }
    }

    public void guardarUsuario(String nombre, String apellido,Long dni, String mail,String password){
        Usuario usuario = new Usuario(nombre,apellido,dni,mail,password);
        ApiClient.guardar(context,usuario);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
