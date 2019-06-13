package com.example.downloadimageapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

    private MainActivity pantallaImagen;

    public DownloadImage(MainActivity mainActivity) {
        this.pantallaImagen = mainActivity;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        InputStream in = null;
        int respuesta = -1;
        URL url = null;
        HttpURLConnection httpConn = null;

        Log.d(getClass().getCanonicalName(), "Iniciando la descarga.");
        try {
            Log.d("MIAPP", "Descargando . . .");
            url = new URL(strings[0]);
            httpConn = (HttpURLConnection) url.openConnection();

            respuesta = httpConn.getResponseCode();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
                Log.d(getClass().getCanonicalName(), "Descarga terminada, correcta.");
            } else {
                Log.d(getClass().getCanonicalName(), "Descarga terminada con código de error: '" + respuesta + "'.");
            }

        } catch (Exception e) {
            // Auto-generated catch block
            Log.d(getClass().getCanonicalName(), "ERROR descargando la imagen", e);
        }

        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(getClass().getCanonicalName(), "Antes de iniciar la descarga.");
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.d(getClass().getCanonicalName(), "Terminada la descarga.");

        this.pantallaImagen.mostrarImagenDescargada(bitmap);
    }
}
