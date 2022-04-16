package com.yoteayudo.yoteayudo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.yoteayudo.yoteayudo.R;

import java.io.ByteArrayOutputStream;

public class PerfilFragment extends Fragment {
    TextView txtNombreUsuario, txtCorreo, txtSubirFoto;
    ProgressBar progressBarPerfil;
    ImageView imgFotoPerfil;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;
    StorageReference reference;

    int TAKE_IMAGE_CODE = 100001;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        txtNombreUsuario = view.findViewById(R.id.txtNombreUsuario);
        txtSubirFoto = view.findViewById(R.id.txtSubirFoto);
        txtCorreo = view.findViewById(R.id.txtCorreo);
        progressBarPerfil = view.findViewById(R.id.progressBarPerfil);
        imgFotoPerfil = view.findViewById(R.id.imgFotoPerfil);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        txtSubirFoto.setOnClickListener(view1 -> {
            Intent camera_intent
                    = new Intent(MediaStore
                    .ACTION_IMAGE_CAPTURE);

            startActivityForResult(camera_intent, TAKE_IMAGE_CODE);
        });
        getuserInfo();


        return view;
    }
    private void getuserInfo() {
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue().toString();
                    String correo = snapshot.child("email").getValue().toString();
                    txtNombreUsuario.setText(name);
                    txtCorreo.setText(correo);
                }
                if(mUser.getPhotoUrl()!= null){
                    Glide.with(PerfilFragment.this)
                            .load(mUser.getPhotoUrl())
                            .into(imgFotoPerfil);

                }

                progressBarPerfil.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_IMAGE_CODE) {
            switch (resultCode) {
                case Activity
                        .RESULT_OK:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imgFotoPerfil.setImageBitmap(bitmap);
                    handleUpload(bitmap);
            }
        }
    }
    private void handleUpload(Bitmap bitmap) {
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Uploadgin Image");
        pd.show();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference = FirebaseStorage.getInstance().getReference()
                .child("ProfileImages")
                .child(uid + ".jpeg");

        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        getDownloadUrl(reference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Log.e("Perfilfragment", "onFailure", e.getCause());
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("percentage: " + (int) progressPercent + "%");
            }
        });
    }
    private void getDownloadUrl(StorageReference reference) {
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("Perfilfragment", "onSuccess: " + uri);
                        setuserProfileUrl(uri);
                    }
                });
    }
    private void setuserProfileUrl(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(getView().findViewById(R.id.frame), "Actualizaste tu foto de perfil", Snackbar.LENGTH_SHORT).show();
                        /*Toast.makeText(getActivity(), "Actualizaste tu foto de perfil", Toast.LENGTH_SHORT).show();*/
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "No se pudo cargar la foto", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}