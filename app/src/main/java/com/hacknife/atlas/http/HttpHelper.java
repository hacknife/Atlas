package com.hacknife.atlas.http;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ResponseBody;

public class HttpHelper {


    public static File bodyTpng(ResponseBody body) throws Exception {
        File file = new File(System.getProperty("user.dir") + File.separator + "code.png");
        if (file.exists())
            file.delete();
        OutputStream outputStream = new FileOutputStream(file);
        byte[] bytes = readInputStream(body.byteStream());
        outputStream.write(bytes, 0, bytes.length);
        outputStream.close();
        return file;
    }

    static byte[] readInputStream(InputStream input) throws IOException {
        byte[] buf = new byte[1024];
        byte[] bytes = new byte[0];
        int bytesRead;
        while ((bytesRead = input.read(buf)) > 0) {
            byte[] tmp = new byte[bytes.length + bytesRead];
            System.arraycopy(bytes, 0, tmp, 0, bytes.length);
            System.arraycopy(buf, 0, tmp, bytes.length, bytesRead);
            bytes = tmp;
        }
        return bytes;
    }

    public static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    public static class TrustAllManager implements X509TrustManager {


        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s)   {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }


    }

    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


}
