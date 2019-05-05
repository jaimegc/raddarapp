package com.raddarapp.data.android.repository.datasource.origin.remote.api.config;

import android.os.Build;

import com.raddarapp.BuildConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.interceptor.AuthInterceptor;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.interceptor.BasicInterceptor;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerApiConfig {

    private static ServerApiConfig singleton;
    private static String token = "";
    private final boolean debug;
    private final Retrofit retrofit;
    private static final String SERVER_HTTPS = "https://";
    private static final String SERVER_API_VERSION = BuildConfig.SERVER_API_VERSION;
    private static final String SERVER_API = BuildConfig.SERVER_API;
    private static final String SERVER_API_PORT = BuildConfig.SERVER_API_PORT;
    private static final Integer TIME_OUT_SECONDS = 45;

    public ServerApiConfig(String actualToken, Retrofit retrofit, boolean debug) {
        token = actualToken;
        this.retrofit = retrofit;
        this.debug = debug;
    }

    public static ServerApiConfig with(String actualToken) {
        if (!token.equals(actualToken) || (token.isEmpty() && actualToken.isEmpty())) {
            singleton = new Builder(actualToken).build();
        }

        return singleton;
    }

    public static ServerApiConfig withTestToken(String token, String baseUrl) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new AuthInterceptor(token));

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return new ServerApiConfig(token, retrofit, true);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static class Builder {
        private static String DATA_ORIGIN = BuildConfig.DATA_ORIGIN;
        private static final String API_LOCAL = "API_LOCAL";

        private static final String SERVER_LOCAL_IP = "192.168.8.100";
        private static final String SERVER_EMULATOR_IP = "10.0.2.2";

        private static String SERVER_URL;
        private static String SERVER_PORT;
        private final String token;
        private Retrofit retrofit;
        private String baseUrl = configureBaseUrl();
        private OkHttpClient.Builder client;
        private final boolean debug = BuildConfig.IS_DEBUG;

        public Builder(String token) {
            this.token = token;
        }

        public Builder baseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        public Builder retrofit(Retrofit retrofit) {
            if (retrofit == null) {
                throw new IllegalArgumentException("retrofit must not be null.");
            }
            this.retrofit = retrofit;
            return this;
        }

        public ServerApiConfig build() {
            if (retrofit == null) {
                retrofit = createDefaultRetrofit(baseUrl, debug);
            }

            return new ServerApiConfig(token, retrofit, debug);
        }

        private AuthInterceptor addAuthorization(String token) {
            return new AuthInterceptor(token);
        }

        private BasicInterceptor addBasic() {
            return new BasicInterceptor();
        }

        private Retrofit createDefaultRetrofit(String baseUrl, boolean debug) {
            client = new OkHttpClient.Builder();

            // FIXME: Changing timeout
            client.connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS);
            client.readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS);

            if (debug) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.interceptors().add(interceptor);
                //addUnsafeOkHttpClient(client);
            }

            /*if (!token.isEmpty()) {
                client.addInterceptor(addAuthorization(token));
            } else {
                client.addInterceptor(addBasic());
            }*/

            client.addInterceptor(addAuthorization(token));

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        }

        private String configureBaseUrl() {
            switch (DATA_ORIGIN) {
                case API_LOCAL:
                    SERVER_URL = (!isEmulator() ? SERVER_LOCAL_IP : SERVER_EMULATOR_IP);
                    break;
                default:
                    SERVER_URL = SERVER_API;
                    break;
            }

            SERVER_URL = SERVER_API;

            return SERVER_HTTPS + SERVER_URL + SERVER_API_PORT + SERVER_API_VERSION;
        }
    }

    private static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    // Unsafe https for debug (only if https is unsafe)
    private static OkHttpClient addUnsafeOkHttpClient(OkHttpClient.Builder client) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            client.sslSocketFactory(sslSocketFactory);
            client.hostnameVerifier((hostname, session) -> true);

            OkHttpClient okHttpClient = client.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
