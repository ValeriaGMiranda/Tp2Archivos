package com.valesoft.tp1sharedpreferences.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.valesoft.tp1sharedpreferences.R;
import com.valesoft.tp1sharedpreferences.databinding.ActivityRegistroBinding;
import com.valesoft.tp1sharedpreferences.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel rvm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);



        rvm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etNombre.setText(usuario.getNombre().toString());
                binding.etApellido.setText(usuario.getApellido().toString());
                binding.etDni.setText(String.valueOf(usuario.getDni()));
                binding.etUser.setText(usuario.getMail().toString());
                binding.etPass.setText(usuario.getPassword().toString());
            }
        });

        binding.bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                Long dni = Long.parseLong(binding.etDni.getText().toString());
                String mail = binding.etUser.getText().toString();
                String password = binding.etPass.getText().toString();

                rvm.guardarUsuario(nombre,apellido,dni,mail,password);
            }
        });

        rvm.mostrarUsuario();

    }
}