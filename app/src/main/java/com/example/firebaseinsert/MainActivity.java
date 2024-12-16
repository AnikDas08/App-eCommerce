package com.example.firebaseinsert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText rollEdit,nameEdit,contactEdit,courseEdit;
    Button button,detailButton;
    ImageView imageView;
    Uri filePath;
    Bitmap bitmap;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollEdit=findViewById(R.id.RollId);
        nameEdit=findViewById(R.id.NameEdit);
        contactEdit=findViewById(R.id.DuarationEdit);
        courseEdit=findViewById(R.id.CourseEdit);
        button=findViewById(R.id.Button);
        imageView=findViewById(R.id.ImageView);
        detailButton=findViewById(R.id.DetailId);

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DetailsActivity.class));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(MainActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Please Selelect Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtofirebase();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            filePath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filePath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }catch (Exception e){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void uploadtofirebase(){
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("Uploading");
        dialog.show();

        String roll=rollEdit.getText().toString();
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference reference=storage.getReference("insert").child("image "+roll);
        reference.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                FirebaseDatabase db=FirebaseDatabase.getInstance();
                                DatabaseReference root=db.getReference("students");
                                DataHolder obj=new DataHolder(nameEdit.getText().toString(),contactEdit.getText().toString(),courseEdit.getText().toString(),uri.toString(),rollEdit.getText().toString());
                                root.child(roll).setValue(obj);

                                nameEdit.setText("");
                                contactEdit.setText("");
                                rollEdit.setText("");
                                courseEdit.setText("");
                                imageView.setImageResource(R.drawable.ic_launcher_background);
                                Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        float percent=(100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploading :"+(int)percent+"%");
                    }
                });
    }
}