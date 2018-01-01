package co.za.geartronix.providers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;


public class HttpConnectionProvider {

    private Bundle values;
    private String stringUrl;
    private String requestMethod;
    private boolean doInput;
    private boolean doOutput;
    private int connectionTimeout;
    private HttpURLConnection httpConnect;

    public HttpConnectionProvider(Bundle...values) {
        if(values != null && values.length > 0)
            this.values = values[0];
    }


    public String makeCallForData(String stringUrl, String requestMethod, boolean doInput, boolean doOutput, int connectionTimeout) throws MalformedURLException, IOException {

        String encoding = "UTF-8";
        String postData = getPostData(encoding);

        if(requestMethod.equals("GET"))
            stringUrl += "?"+ postData;

        setConnectionParams(stringUrl, requestMethod, doInput, doOutput, connectionTimeout);
        httpConnect = getHttpConnection();

        // OutputStream -------------------------------------------
        OutputStream sout = httpConnect.getOutputStream();
        BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(sout, encoding));

        if(!requestMethod.equals("GET"))
            bWriter.write(postData);

        bWriter.flush();
        bWriter.close();
        sout.close();

        // InputStream -------------------------------------------
        InputStream sin = httpConnect.getInputStream();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(sin, "iso-8859-1"));

        String result = "";
        String line;

        while ((line = bReader.readLine()) != null) {
            result += line;
        }

        // close connection -------------------------------------
        bReader.close();
        sin.close();
        httpConnect.disconnect();

        return result;
    }


    private HttpURLConnection getHttpConnection() throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpConnect = (HttpURLConnection)url.openConnection();
        httpConnect.setRequestMethod(requestMethod);
        httpConnect.setDoOutput(doOutput);
        httpConnect.setDoInput(doInput);
        httpConnect.setConnectTimeout(connectionTimeout);
        return httpConnect;
    }

    private void setConnectionParams(String stringUrl, String requestMethod, boolean doInput, boolean doOutput, int connectionTimeout) {
        this.stringUrl = stringUrl;
        this.requestMethod = requestMethod;
        this.doInput = doInput;
        this.doOutput = doOutput;
        this.connectionTimeout = connectionTimeout;
    }

    private String getPostData(String encoding) throws UnsupportedEncodingException {

        String deviceSerial = getDeviceSerial();

        String postData = URLEncoder.encode("os", encoding)+"="+URLEncoder.encode("ANDROID", encoding)
                + "&"+URLEncoder.encode("deviceType", encoding)+"="+URLEncoder.encode(deviceSerial, encoding)
                + "&"+URLEncoder.encode("deviceSerial", encoding)+"="+URLEncoder.encode(deviceSerial, encoding);
        String key;

        if(values == null)
            return  "";

        Set vals = values.keySet();

        for (Object val : vals) {
            key = val.toString();
            postData += ((!postData.isEmpty()) ? "&" : "");
            postData += URLEncoder.encode(key, encoding) + "="
                    + URLEncoder.encode(values.getString(key), encoding);
        }

        return postData;
    }

    public void setRemoteBitmap(ProgressBar imageLoading,ImageButton theImage, String path) {
        new FetchImage(imageLoading, theImage).execute(path);
    }

    private class FetchImage extends AsyncTask<String , Integer, Bitmap>{

        private ImageButton theImage;
        private ProgressBar imageLoading;

        public FetchImage(ProgressBar imageLoading, ImageButton theImage) {
            this.theImage = theImage;
            this.imageLoading = imageLoading;
        }

        @Override
        protected Bitmap doInBackground(String...params) {

            Bitmap bitmapImage;

            try {

                URL url = new URL(params[0]);
                HttpURLConnection htpc = (HttpURLConnection) url.openConnection();
                BufferedInputStream bi = new BufferedInputStream(htpc.getInputStream());

                bitmapImage = BitmapFactory.decodeStream(bi);
                bi.close();
                htpc.disconnect();
            }
            catch (Exception e){
                bitmapImage = null;
            }

            return bitmapImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageLoading.setVisibility(View.INVISIBLE);
            theImage.setImageBitmap(bitmap);
        }
    }


    private String getDeviceSerial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            return Build.getSerial();

        return "000";
    }

}