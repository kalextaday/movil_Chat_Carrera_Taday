package com.example.core.firebasechat.Modelo.Persistencia;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.core.firebasechat.Modelo.Entidades.Firebase.Usuario;
import com.example.core.firebasechat.Modelo.Entidades.Logica.LUsuario;
import com.example.core.firebasechat.Modelo.Constantes.Constantes;

<<<<<<< HEAD
/**
 * Clase UsuarioDAO para obtener los datos de la base de datos
 * @version 1.0, 30/07/2020
 * @author Carrera,Taday
 */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
public class UsuarioDAO {

    public interface IDevolverUsuario{
        void devolverUsuario(LUsuario lUsuario);
        void devolverError(String error);
    }

    public interface IDevolverUrlFoto{
        void devolerUrlString(String url);
    }

    private static UsuarioDAO usuarioDAO;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private DatabaseReference referenceUsuarios;
    private StorageReference referenceFotoDePerfil;

    public static UsuarioDAO getInstancia(){
        if(usuarioDAO==null) usuarioDAO = new UsuarioDAO();
        return usuarioDAO;
    }

<<<<<<< HEAD
    /**
     * Metodo para obtener el usuario de la base de datos
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    private UsuarioDAO(){
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        referenceUsuarios = database.getReference(Constantes.nodoUsuarios);
        referenceFotoDePerfil = storage.getReference(Constantes.nodoFotosPerfil+
                getIdUsuario());
    }

<<<<<<< HEAD
    /**
     * Metodo para obtener el usuario por id de la base de datos
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public String getIdUsuario(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

<<<<<<< HEAD
    /**
     * Metodo para obtener la informacion de usuario por el id
     * @param id
     * @param iDevolverUsuario
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void obtenerInformacionDeUsuarioPorId(final String id,
                                                 final IDevolverUsuario iDevolverUsuario){
        referenceUsuarios.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                LUsuario lUsuario = new LUsuario(id,usuario);
                iDevolverUsuario.devolverUsuario(lUsuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iDevolverUsuario.devolverError(databaseError.getMessage());
            }
        });

    }

<<<<<<< HEAD
    /**
     * Metodo para almacenar la direccion de la foto de usuario
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void subirFotoUri(Uri uri, final IDevolverUrlFoto iDevolverUrlFoto){
        String nombreFoto = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("SSS.ss-mm-hh-dd-MM-yyyy",
                Locale.getDefault());
        nombreFoto = simpleDateFormat.format(date);
        final StorageReference fotoReferencia = referenceFotoDePerfil.child(nombreFoto);
        fotoReferencia.putFile(uri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    throw task.getException();
                }
                return fotoReferencia.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri uri = task.getResult();
                    iDevolverUrlFoto.devolerUrlString(uri.toString());
                }
            }
        });
    }

<<<<<<< HEAD
    /**
     * Metodo para registrar usuario en la base de datos
     * @param c
     * @param correo
     * @param nombre
     * @param url
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void registrarUsuario(final Context c, String correo, String nombre, String url){
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        usuario.setFotoPerfilURL(url);
        DatabaseReference reference = database.getInstance().getReference();
        reference.child(Constantes.nodoUsuarios).
                child(getIdUsuario()).setValue(usuario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(c, "Se registro correctamente.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(c, "NO se registro correctamente.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
