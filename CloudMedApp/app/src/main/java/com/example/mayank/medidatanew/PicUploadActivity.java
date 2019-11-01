package com.example.mayank.medidatanew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class PicUploadActivity extends AppCompatActivity {


    //variables

    private Button btnChoose,btnUpload,back,logout;
    private ImageView imageView;
    private Uri filePath;


    private final int PICK_IMAGE_REQUEST=71;


    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_upload);

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        btnChoose=(Button)findViewById(R.id.btn);
        btnUpload=(Button)findViewById(R.id.upload);
        imageView=(ImageView)findViewById(R.id.img);
        back=(Button)findViewById(R.id.back);
        logout=(Button)findViewById(R.id.logout);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                chooseImage();

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                uploadImage();
                
                
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToLoginActivity();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToMainActivity();
            }
        });



    }

    private void sendToLoginActivity() {




        Intent sendtoLoginActivty=new Intent(this,LoginActivity.class);
        startActivity(sendtoLoginActivty);
        sendtoLoginActivty.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();




    }

    private void sendToMainActivity() {


        Intent sendtoMainActivity=new Intent(this,MainActivity.class);
        startActivity(sendtoMainActivity);
        sendtoMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();




    }

    private void uploadImage() {


        if(filePath != null)



        {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading....");
            progressDialog.show();


            StorageReference ref = storageReference.child("imahes/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            progressDialog.dismiss();
                            Toast.makeText(PicUploadActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            progressDialog.dismiss();
                            Toast.makeText(PicUploadActivity.this, "Failed"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();



                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded"+(int)progress+"%");




                        }
                    });



        }

    }

    private void chooseImage() {

        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null  && data.getData() != null)



        {

            filePath=data.getData();

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);

            }
            catch (IOException e)



            {

            e.printStackTrace();


            }

        }

    }
}


