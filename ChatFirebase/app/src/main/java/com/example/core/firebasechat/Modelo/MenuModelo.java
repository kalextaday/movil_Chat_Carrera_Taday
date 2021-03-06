package com.example.core.firebasechat.Modelo;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.core.firebasechat.Modelo.Constantes.Constantes;
import com.example.core.firebasechat.Modelo.Entidades.Firebase.Usuario;
import com.example.core.firebasechat.Modelo.Persistencia.UsuarioDAO;
import com.example.core.firebasechat.Presentador.MenuPresentador;
import com.example.core.firebasechat.Vista.InicioSesionActivity;
import com.example.core.firebasechat.Vista.MenuActivity;
import com.example.core.firebasechat.Vista.ListaUsuariosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

<<<<<<< HEAD
/**
 * Clase MenuModelo para mostrar el menu
 * @version 1.0, 30/07/2020
 * @author Carrera,Taday
 */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
public class MenuModelo {
    MenuPresentador menuPresentador;

    public MenuModelo(MenuPresentador menuPresentador, final MenuActivity menuActivity,
                      final TextView txtNombreUsuario, final CircleImageView fotoPerfil){
        this.menuPresentador=menuPresentador;
        FirebaseDatabase.getInstance().getReference(Constantes.nodoUsuarios)
                .child(UsuarioDAO.getInstancia().getIdUsuario())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        txtNombreUsuario.setText(usuario.getNombre());
                        Glide.with(menuActivity).
                                load(usuario.getFotoPerfilURL()).into(fotoPerfil);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

<<<<<<< HEAD
    /**
     * Metodo para ver usuarios en la actividad
     * @param menuActivity
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void verUsuarios(MenuActivity menuActivity){
        Intent intent = new Intent(menuActivity, ListaUsuariosActivity.class);
        menuActivity.startActivity(intent);
    }

<<<<<<< HEAD
    /**
     * Metodo para cerrar la sesion
     * @param menuActivity
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void cerrarSesion(MenuActivity menuActivity){
        FirebaseAuth.getInstance().signOut();
        returnLogin(menuActivity);
    }

<<<<<<< HEAD
    /**
     * Metodo para abrir la actividad del login
     * @param menuActivity
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    private void returnLogin(MenuActivity menuActivity){
        menuActivity.startActivity(new Intent(menuActivity, InicioSesionActivity.class));
        menuActivity.finish();
    }

}
