package com.bawei.liushaojie.model.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils<T> {
    //进行静态式单例封装
    private HttpUtils(){}

    public static HttpUtils getHttpUtilsInstance(){
        return HttpUtilsConnaction.httpUtils;
    }
    private static class HttpUtilsConnaction{
        private static HttpUtils httpUtils=new HttpUtils();
    }
    //OKHttp日志拦截器
    private class OkHttpLogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e("myMessage","----------------");
            Response response = chain.proceed(request);
            Log.e("myMessage","----------------");
            return response;
        }
    }
    private CallBackData mCallBackData;
    //Get请求
    public void getData(String url, final Class<T> tClass,final CallBackData callBackData){
        this.mCallBackData=callBackData;
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new OkHttpLogInterceptor())
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=Message.obtain();
                message.what=1;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                T t = gson.fromJson(string, tClass);
                Message message=Message.obtain();
                message.what=0;
                message.obj=t;
                handler.sendMessage(message);
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    T t= (T) msg.obj;
                    mCallBackData.onResponce(t);
                    break;
                case 1:
                    String err= (String) msg.obj;
                    mCallBackData.onFail(err);
                    break;
            }
        }
    };

    //接口
    public interface CallBackData<D>{
        public void onResponce(D d);
        public void onFail(String err);
    }
}
