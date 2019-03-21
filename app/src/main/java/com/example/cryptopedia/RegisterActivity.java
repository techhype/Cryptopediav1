package com.example.cryptopedia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    ImageView ImgUserPhoto;
    static int PReqCode =1;
    static int REQUESCODE =1;
    Uri pickedImgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ImgUserPhoto = findViewById(R.id.regUserPhoto);
        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Android Version Lollipop or Greater
                if(Build.VERSION.SDK_INT >= 22){

                    //Have to request for permissions - Function written down
                    checkAndRequestPermission();

                }
                else{

                    //No permission needed..Open Gallery  - Function written down
                    openGallery();

                }




            }
        });

    }

    private void openGallery() {

        //Open Gallery Intent and wait for the User to pick an image
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);


    }

    private void checkAndRequestPermission() {

        //If permission is not yet granted by User to access the External Storage
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){

            //shouldShowRequestPermissionRationale - how UI with reasons for requesting a permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(RegisterActivity.this, "Please accept for requested permission to upload photo", Toast.LENGTH_LONG).show();

            }
            else{

                ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                    PReqCode);
            }
        }
        else{
            openGallery();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){

            //User has Successfully picked the Image
            //save its reference to an Uri Variable
            pickedImgUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImgUri);



        }
    }
}
