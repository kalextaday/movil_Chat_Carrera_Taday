package com.example.core.firebasechat.Modelo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.firebasechat.Modelo.Adaptadores.MensajeAdaptador;
import com.example.core.firebasechat.Modelo.Constantes.Constantes;
import com.example.core.firebasechat.Modelo.Entidades.Firebase.Mensaje;
import com.example.core.firebasechat.Modelo.Persistencia.MensajeDAO;
import com.example.core.firebasechat.Modelo.Persistencia.UsuarioDAO;
import com.example.core.firebasechat.Presentador.MensajesPresentador;
import com.example.core.firebasechat.Vista.MensajesActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

<<<<<<< HEAD
/**
 * Clase MensajesModelo para mostrar los mensajes
 * @version 1.0, 30/07/2020
 * @author Carrera,Taday
 */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
public class MensajesModelo {

    MensajesPresentador mensajesPresentador;

    private FirebaseStorage storage;

    private StorageReference storageReference;

    private MensajeAdaptador mensajeAdaptador;

<<<<<<< HEAD
    /**
     * Metodo constructor
     * @param mensajesPresentador para la comunicacion con el presentador
     * @param rvMensajes componente visual para mostrar los mensajes
     * @param mensajesActivity la actividad que muestra los mensajes
     * @param idReceptor el id del receptor del mensaje
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public MensajesModelo(MensajesPresentador mensajesPresentador, final RecyclerView rvMensajes,
                          MensajesActivity mensajesActivity, String idReceptor){
        this.mensajesPresentador = mensajesPresentador;
        storage = FirebaseStorage.getInstance();
        mensajeAdaptador = new MensajeAdaptador(mensajesActivity);
        rvMensajes.setAdapter(mensajeAdaptador);
        mensajeAdaptador.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar(rvMensajes, mensajeAdaptador);
            }
        });

        MensajeDAO.getInstancia().obtenerMensajes(mensajesActivity, mensajeAdaptador, idReceptor);

        verifyStoragePermissions(mensajesActivity);
    }

<<<<<<< HEAD
    /**
     * Metodo para enviar mensaje
     * @param txtMensaje el contenido del mensaje
     * @param idReceptor el id del receptor
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void enviarMensaje(EditText txtMensaje, String idReceptor){
        String mensajeEnviar = txtMensaje.getText().toString();
        if(!mensajeEnviar.isEmpty()){
            guardarMensaje(null,idReceptor,mensajeEnviar);
            txtMensaje.setText("");
        }
    }

<<<<<<< HEAD
    /**
     * Metodo que llama la actividad que envia imagenes
     * @param mensajesActivity
     * @param EnviarFoto
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void llamarActividadEnviarFoto(MensajesActivity mensajesActivity, int EnviarFoto){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/jpeg");
        i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        mensajesActivity.startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),
                EnviarFoto);
    }

<<<<<<< HEAD
    /**
     * Metodo para enviar imagenes
     * @param u
     * @param idReceptor
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public void enviarFoto(final Uri u, final String idReceptor){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "SSS.ss-mm-hh-dd-MM-yyyy", Locale.getDefault());
        String nombreFoto = simpleDateFormat.format(date);
        storageReference = storage.getReference(Constantes.nodoImagenes);
        final StorageReference fotoReferencia = storageReference.
                child(UsuarioDAO.getInstancia().getIdUsuario()).child(idReceptor)
                .child(u.getLastPathSegment()+"_"+nombreFoto);
        fotoReferencia.putFile(u).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
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
                   guardarMensaje(uri,idReceptor,"Imagen");
                }
            }
        });
    }

<<<<<<< HEAD
    /**
     * Metodo para habilitar el componente setScrollBar
     * @param rvMensajes
     * @param adapter
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    private void setScrollbar(RecyclerView rvMensajes, MensajeAdaptador adapter){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }

<<<<<<< HEAD
    /**
     * Metodo para verificar los permisos de almacenamiento
     * @param activity
     * @return
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public boolean verifyStoragePermissions(Activity activity) {
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int REQUEST_EXTERNAL_STORAGE = 1;
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return false;
        }else{
            return true;
        }
    }

    public void guardarMensaje(Uri uri,String idReceptor,String contenidoMensaje){
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(contenidoMensaje);
        if(uri!=null){
            mensaje.setUrlFoto(uri.toString());
        }
        mensaje.setIdEmisor(UsuarioDAO.getInstancia().getIdUsuario());
        MensajeDAO.getInstancia().crearNuevoMensaje(UsuarioDAO.getInstancia().getIdUsuario(),
                idReceptor,mensaje);
    }

}
