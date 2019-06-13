package com.example.downloadimageapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DownloadImage di;
    private static final String URL_IMAGEN = "https://en.wikipedia.org/wiki/Lion#/media/File:Lion_waiting_in_Namibia.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.di = new DownloadImage(this);
        di.execute(URL_IMAGEN);
    }

    public void mostrarImagenDescargada(Bitmap bitmap) {
        // Mostrar la imagen o mensaje de Error.
        if (null == bitmap) {
            Toast.makeText(this, R.string.error_mainactivity_no_download, Toast.LENGTH_LONG).show();
        } else {
            ImageView iv = findViewById(R.id.downloadedImage);
            iv.setImageBitmap(bitmap);
        }
    }
}
