package com.valesoft.tp1sharedpreferences.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.valesoft.tp1sharedpreferences.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    private static File conectar(Context context){
        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta,"datos.dat");

        return archivo;
    }

    public static void guardar(Context context, Usuario usuario){

        try {
            FileOutputStream fos = new FileOutputStream(conectar(context));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos =  new ObjectOutputStream(bos);

            oos.writeObject(usuario);
            bos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_SHORT).show();
        } catch (IOException io){
            Toast.makeText(context, "Error de entrada/salida", Toast.LENGTH_SHORT).show();
        }

    }

    public static Usuario leer(Context context){
        Usuario usuario = null;

        try {
            FileInputStream fis = new FileInputStream(conectar(context));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            usuario = (Usuario)ois.readObject();

            fis.close();


        } catch (FileNotFoundException e) {
            return usuario;//retorno usuario vacio porque vengo desde registro
            //Toast.makeText(context, "Error al leer", Toast.LENGTH_SHORT).show();
        } catch (IOException io){
            return usuario;//retorno usuario vacio porque vengo desde registro
            //Toast.makeText(context, "Error entrada/salida", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e){
            Toast.makeText(context, "Error interno", Toast.LENGTH_SHORT).show();
        }

        return usuario;
    }

    public static Usuario login(Context context,String mail, String password){
        Usuario usuario = null;

        try {
            FileInputStream fis = new FileInputStream(conectar(context));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            usuario = (Usuario)ois.readObject();

            fis.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al leer", Toast.LENGTH_SHORT).show();
        } catch (IOException io){
            Toast.makeText(context, "Error entrada/salida", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e){
            Toast.makeText(context, "Error interno", Toast.LENGTH_SHORT).show();
        }

        if(usuario != null){
            if(mail.equals(usuario.getMail().toString())&& password.equals(usuario.getPassword().toString())){
                return usuario;

            }else{
                usuario = null;
            }
        }

        return usuario;
    }
}
