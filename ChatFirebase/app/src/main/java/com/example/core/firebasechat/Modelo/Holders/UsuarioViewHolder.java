package com.example.core.firebasechat.Modelo.Holders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.core.firebasechat.R;

<<<<<<< HEAD
/**
 * Clase UsuarioViewHolder para manejar los componentes
 * @version 1.0, 30/07/2020
 * @author Carrera,Taday
 */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
public class UsuarioViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView civFotoPerfil;
    private TextView txtNombreUsuario;
    private LinearLayout layoutPrincipal;
    private TextView txtUltimoMensaje;
    private TextView txtHoraUltimoMensaje;

<<<<<<< HEAD
    /**
     * Metodo constructor
     * @param itemView
     */
=======
>>>>>>> 1fa891d32283ce8e20e52be6afe065f2288053aa
    public UsuarioViewHolder(@NonNull View itemView) {
        super(itemView);
        civFotoPerfil = itemView.findViewById(R.id.civFotoPerfil);
        txtNombreUsuario =itemView.findViewById(R.id.txtNombreUsuario);
        txtHoraUltimoMensaje=itemView.findViewById(R.id.txtHoraUltimoMensaje);
        txtUltimoMensaje=itemView.findViewById(R.id.txtUltimoMensaje);
        layoutPrincipal = itemView.findViewById(R.id.layoutPrincipal);
    }

    public CircleImageView getCivFotoPerfil() {
        return civFotoPerfil;
    }

    public void setCivFotoPerfil(CircleImageView civFotoPerfil) {
        this.civFotoPerfil = civFotoPerfil;
    }

    public TextView getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public void setTxtNombreUsuario(TextView txtNombreUsuario) {
        this.txtNombreUsuario = txtNombreUsuario;
    }


    public LinearLayout getLayoutPrincipal() {
        return layoutPrincipal;
    }

    public void setLayoutPrincipal(LinearLayout layoutPrincipal) {
        this.layoutPrincipal = layoutPrincipal;
    }

    public TextView getTxtUltimoMensaje() {
        return txtUltimoMensaje;
    }

    public void setTxtUltimoMensaje(TextView txtUltimoMensaje) {
        this.txtUltimoMensaje = txtUltimoMensaje;
    }

    public TextView getTxtHoraUltimoMensaje() {
        return txtHoraUltimoMensaje;
    }

    public void setTxtHoraUltimoMensaje(TextView txtHoraUltimoMensaje) {
        this.txtHoraUltimoMensaje = txtHoraUltimoMensaje;
    }
}
