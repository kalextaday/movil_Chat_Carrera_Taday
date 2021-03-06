package com.example.core.firebasechat.Modelo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.core.firebasechat.Modelo.Constantes.Constantes;
import com.example.core.firebasechat.Modelo.Persistencia.UsuarioDAO;
import com.example.core.firebasechat.Modelo.Validaciones.CamposUsuario;
import com.example.core.firebasechat.Presentador.RegistroPresentador;
import com.example.core.firebasechat.Vista.RegistroActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kbeanie.multipicker.api.CacheLocation;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

<<<<<<< HEAD
/**
 * Clase RegistroModelo para registrar usuarios
 * @version 1.0, 30/07/2020
 * @author Carrera,Taday
 */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
public class RegistroModelo {
    RegistroPresentador registroPresentador;

    private ImagePicker imagePicker;

    private CameraImagePicker cameraPicker;

    private String pickerPath;

    private Uri fotoPerfilUri;

<<<<<<< HEAD
    /**
     * Metodo Constructor
     * @param registroPresentador la clase de la capa presentador para la comunicación
     * @param registroActivity la actividad de registro
     * @param fotoPerfil el componente CircleImageView de la foto del usuario
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public RegistroModelo(RegistroPresentador registroPresentador,
                          final RegistroActivity registroActivity,
                          final CircleImageView fotoPerfil){
        this.registroPresentador=registroPresentador;
        imagePicker = new ImagePicker(registroActivity);
        cameraPicker = new CameraImagePicker(registroActivity);
        cameraPicker.setCacheLocation(CacheLocation.EXTERNAL_STORAGE_APP_DIR);

        imagePicker.setImagePickerCallback(new ImagePickerCallback() {
            @Override
            public void onImagesChosen(List<ChosenImage> list) {
                if(!list.isEmpty()){
                    String path = list.get(0).getOriginalPath();
                    fotoPerfilUri = Uri.parse(path);
                    fotoPerfil.setImageURI(fotoPerfilUri);
                }
            }

            @Override
            public void onError(String s) {
                Toast.makeText(registroActivity,"Error: "+s, Toast.LENGTH_SHORT).show();
            }
        });

        cameraPicker.setImagePickerCallback(new ImagePickerCallback() {
            @Override
            public void onImagesChosen(List<ChosenImage> list) {
                String path = list.get(0).getOriginalPath();
                fotoPerfilUri = Uri.fromFile(new File(path));
                fotoPerfil.setImageURI(fotoPerfilUri);
            }

            @Override
            public void onError(String s) {
                Toast.makeText(registroActivity, "Error: "+s,Toast.LENGTH_SHORT).show();
            }
        });
    }

<<<<<<< HEAD
    /**
     * Metodo que registra el usuario en firebase
     * @param correo de tipo string del usuario
     * @param contraseña de tipo string del usuario
     * @param contraseñaRepetida del tipo string para verificar el ingreso correcto
     * @param nombre del usuario
     * @param mAuth la autorizacion para registrar en firebase
     * @param registroActivity la actividad de registro
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void registrarUsuario(final String correo, String contraseña, String contraseñaRepetida,
                                 final String nombre, FirebaseAuth mAuth,
                                 final RegistroActivity registroActivity){
        if(CamposUsuario.getInstancia().validarCorreo(correo) &&
                CamposUsuario.getInstancia().validarContraseña(contraseña,
                        contraseñaRepetida) &&
                CamposUsuario.getInstancia().validarNombre(nombre)){
            mAuth.createUserWithEmailAndPassword(correo, contraseña)
                    .addOnCompleteListener(registroActivity,
                            new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(fotoPerfilUri!=null) {
                                    UsuarioDAO.getInstancia().
                                            subirFotoUri(fotoPerfilUri,
                                                    new UsuarioDAO.IDevolverUrlFoto() {
                                                @Override
                                                public void devolerUrlString(String url) {
                                                    UsuarioDAO.getInstancia().registrarUsuario(
                                                            registroActivity,correo,nombre,url);
                                                    registroActivity.finish();
                                                }
                                            });
                                }else{
                                    UsuarioDAO.getInstancia().registrarUsuario(
                                            registroActivity,correo,nombre,
                                            Constantes.urlFotoPorDefecto);
                                    registroActivity.finish();
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                registroPresentador.mostrarMensajeError();
                            }
                        }
                    });
        }else{
            registroPresentador.mostrarMensajeRevisarCampos();
        }
    }

<<<<<<< HEAD
    /**
     * Metodo para seleccionar un foto y registrarlo para el usuario
     * @param registroActivity
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void seleccionarFoto(RegistroActivity registroActivity){
        AlertDialog.Builder dialog = new AlertDialog.Builder(registroActivity);
        dialog.setTitle("Foto de perfil");

        String[] items = {"Galeria","Camara"};

        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        imagePicker.pickImage();
                        break;
                    case 1:
                        pickerPath = cameraPicker.pickImage();
                        break;
                }
            }
        });
        AlertDialog dialogConstruido = dialog.create();
        dialogConstruido.show();
    }

<<<<<<< HEAD
    /** Envia la imagen seleccionada*/
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void submitImagePicker(Intent data){
        imagePicker.submit(data);
    }

<<<<<<< HEAD
    /** Envia la imagen de la camara seleccionada*/
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void submitCamaraPicker(Intent data){
        cameraPicker.reinitialize(pickerPath);
        cameraPicker.submit(data);
    }

<<<<<<< HEAD
    /** Almacena la imagen seleccionada*/
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("picker_path", pickerPath);
    }

<<<<<<< HEAD
    /** Restaura la imagen */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        pickerPath = savedInstanceState.getString("picker_path");
    }

}
