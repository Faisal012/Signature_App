package com.example.paintingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int defaultColor;
    SignatureView signatureView;
    ImageButton imgErase,imgColor,imgSave;
    TextView penSize;
    SeekBar seekBar;
    private static String filename;
    File path=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myPaintings");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signatureView=findViewById(R.id.signature_view);
        seekBar=findViewById(R.id.penSize);
        penSize=findViewById(R.id.TextPenSize);
        imgColor=findViewById(R.id.btnColor);
        imgErase=findViewById(R.id.btnErase);
        imgSave=findViewById(R.id.btnSave);
        askPermission();
        defaultColor= ContextCompat.getColor(MainActivity.this,R.color.black);
        imgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });
    }

    private void openColorPicker() {
    }

    private void askPermission() {
        Dexter.withContext(this).
                withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).
                withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        Toast.makeText(MainActivity.this,"Granted",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
}