package com.rifqit19.arapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rifqit19.arapp.AR_ADD.AR4Activity;
import com.rifqit19.arapp.AR_OBJ_GERAK.AR2Activity;
import com.rifqit19.arapp.AR_SCAN.AR1Activity;
import com.rifqit19.arapp.AR_VIDEO.AR3Activity;
import com.rifqit19.arapp.AR_GAME.AR5Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import static java.lang.System.out;

public class MenuActivity extends AppCompatActivity {

    Button btn_menu1,btn_menu2,btn_menu3,btn_menu4,btn_menu5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        btn_menu1 = findViewById(R.id.btn_menu1);
        btn_menu2 = findViewById(R.id.btn_menu2);
        btn_menu3 = findViewById(R.id.btn_menu3);
        btn_menu4 = findViewById(R.id.btn_menu4);
        btn_menu5 = findViewById(R.id.btn_menu5);

        btn_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoAR1();
            }
        });

        btn_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu2 = new Intent(MenuActivity.this, AR2Activity.class);
                startActivity(toMenu2);
            }
        });

        btn_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoAR3();
            }
        });

        btn_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu4 = new Intent(MenuActivity.this, AR4Activity.class);
                startActivity(toMenu4);

            }
        });

        btn_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu5 = new Intent(MenuActivity.this, AR5Activity.class);
                startActivity(toMenu5);

            }
        });
    }

    public void dialoAR1(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Penting!");
        alertDialogBuilder
                .setMessage("Apakah anda memiliki gambar khusus untuk AR ini?")
                .setIcon(R.drawable.bg_splash)
                .setCancelable(false)
                .setNeutralButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Lewati",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent toMenu1 = new Intent(MenuActivity.this, AR1Activity.class);
                        startActivity(toMenu1);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Dapatkan Gambar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        downloadImg("https://drive.google.com/file/d/1wzyIcKsCR0wnQ4lJb69VvTtpKIkPaZsp/view");
                    dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void dialoAR3(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Penting!");
        alertDialogBuilder
                .setMessage("Apakah anda memiliki gambar khusus untuk AR ini?")
                .setIcon(R.drawable.bg_splash)
                .setCancelable(false)
                .setNeutralButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Lewati",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent toMenu3 = new Intent(MenuActivity.this, AR3Activity.class);
                        startActivity(toMenu3);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Dapatkan Gambar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        downloadImg("https://drive.google.com/open?id=10j6QQPay66CPqWc8YEsxyotXlYuk6lCz");
                    dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void downloadImg(String url){

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        dialoExit();
    }

    public void dialoExit(){
        String[] array = this.getResources().getStringArray(R.array.QOTD);
        String Quote = array[new Random().nextInt(array.length)];

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Keluar?");
        alertDialogBuilder
                .setMessage(getString(R.string.ttlQOTD)+ "\n" +  Quote + "\n")
                .setIcon(R.drawable.bg_splash)
                .setCancelable(false)
                .setPositiveButton("Keluar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                       MenuActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
