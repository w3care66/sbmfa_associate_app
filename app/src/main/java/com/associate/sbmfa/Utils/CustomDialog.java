package com.associate.sbmfa.Utils;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.associate.sbmfa.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_WRITE;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {
  public Activity c;
  public Dialog d;
  ImageButton closeBtn,downloadBtn;
  WebView imageView;
  String filename;
  Context context;
  public CustomDialog(Activity a,String filename) {
    super(a);
    this.c = a;
    this.context = c.getApplicationContext();
    this.filename = filename;
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.view_image_dialog);
    closeBtn = (ImageButton) findViewById(R.id.imageButtonClose);
    downloadBtn = (ImageButton) findViewById(R.id.imageButtonDownload);
    imageView = (WebView) findViewById(R.id.imageView31);
    closeBtn.setOnClickListener(this);
    downloadBtn.setOnClickListener(this);
    imageView.getSettings().setLoadWithOverviewMode(true);
    imageView.getSettings().setSupportZoom(true);
    imageView.getSettings().setBuiltInZoomControls(true);
    imageView.getSettings().setDisplayZoomControls(false);
    imageView.getSettings().setJavaScriptEnabled(true);
    imageView.loadUrl(filename);
  }
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.imageButtonClose:
        dismiss();
        break;
      case R.id.imageButtonDownload:
        if (checkPermission()){
          Log.e("ImageName",""+filename);
          new Downloading().execute(filename);
        }

        break;
      default:
        break;
    }
    dismiss();
  }
  private class DownloadFile extends AsyncTask<String, String, String> {
    private ProgressDialog progressDialog;
    private String fileName;
    private String folder;
    private boolean isDownloaded;

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      this.progressDialog = new ProgressDialog(c);
      this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
      this.progressDialog.setCancelable(false);
      this.progressDialog.show();
    }
    @Override
    protected String doInBackground(String... f_url) {
      int count;
      try {
        URL url = new URL(f_url[0]);
        URLConnection connection = url.openConnection();
        connection.connect();
        // getting file length
        int lengthOfFile = connection.getContentLength();
        // input stream to read file - with 8k buffer
        InputStream input = new BufferedInputStream(url.openStream(), 8192);
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        //Extract file name from URL
        fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());
        //Append timestamp to file name
        fileName = timestamp + "_" + fileName;
        //External directory path to save file
        folder = getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/";
        //Create androiddeft folder if it does not exist
        File directory = new File(folder);
        if (!directory.exists()) {
          if (directory.isDirectory()){
                            Log.e("isDirectory",""+directory.isDirectory());
                        }else {
                            Log.e("isDirectory",""+directory.isDirectory());
                            directory.mkdirs();
                        }
        }
        // Output stream to write file
        OutputStream output = new FileOutputStream(folder + fileName);
        byte data[] = new byte[1024];
        long total = 0;
        while ((count = input.read(data)) != -1) {
          total += count;
          // publishing the progress....
          // After this onProgressUpdate will be called
          publishProgress("" + (int) ((total * 100) / lengthOfFile));
          Log.e("TAG", "Progress: " + (int) ((total * 100) / lengthOfFile));
          // writing data to file
          output.write(data, 0, count);
        }
        // flushing output
        output.flush();
        // closing streams
        output.close();
        input.close();
        return "Downloaded at: " + folder + fileName;
      } catch (Exception e) {
        Log.e("Error: ", e.getMessage());
      }
      return "Something went wrong";
    }
    protected void onProgressUpdate(String... progress) {
      progressDialog.setProgress(Integer.parseInt(progress[0]));
    }
    @Override
    protected void onPostExecute(String message) {
      this.progressDialog.dismiss();
    }
  }


  public class Downloading extends AsyncTask<String, Integer, String> {
    private ProgressDialog progressDialog;
    @Override
    public void onPreExecute() {
      super .onPreExecute();
      this.progressDialog = new ProgressDialog(c);
      this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
      this.progressDialog.setCancelable(false);
      this.progressDialog.show();
    }
    @Override
    protected String doInBackground(String... url) {
      File mydir = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/SBMFA");
      if (!mydir.exists()) {
        mydir.mkdirs();
      }
      DownloadManager manager = (DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
      Uri downloadUri = Uri.parse(url[0]);
      DownloadManager.Request request = new DownloadManager.Request(downloadUri);
      SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmyyyyhhmmss");
      String date = dateFormat.format(new Date());
      request.setAllowedNetworkTypes(
              DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
              .setAllowedOverRoaming(false)
              .setTitle("Downloading")
              .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/SBMFA/"+date + ".jpg");
      manager.enqueue(request);
      return mydir.getAbsolutePath() + File.separator + date + ".jpg";
    }
    protected void onProgressUpdate(Integer... progress) {
      progressDialog.setProgress(progress[0]);
    }
    @Override
    public void onPostExecute(String s) {
      progressDialog.dismiss();
      Toast.makeText(c, "Image Saved", Toast.LENGTH_SHORT).show();
    }
  }

  //runtime storage permission
  public boolean checkPermission() {
    int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE);
    if((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(c, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_WRITE);
      return false;
    }
    return true;
  }
  public class MyWebChromeClient extends WebChromeClient {
    public void onProgressChanged(WebView view, int newProgress) {

    }
  }

  public class webClient extends WebViewClient {
    public boolean  shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl(url);
      return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);

    }
  }

  @Override
  public void onBackPressed() {
    if (imageView.canGoBack()) {
      imageView.goBack();
    } else  {
      super.onBackPressed();
    }
  }
}