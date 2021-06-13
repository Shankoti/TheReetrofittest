package com.example.emptyness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
     String TAG =" MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //نبدتي بقي نشوف لو النت شغال ولا لا
        if(isNetworkAvailable(getApplication())){

            Log.d(TAG, "internet is available ");



            // البيلدر بتاع ريتروفيت المحترم
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.pray.zone/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitInterrface retrofitInterrface = retrofit.create(RetrofitInterrface.class);

                Call<Example> ExampleCall = retrofitInterrface.getdata("cairo");
                ExampleCall.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {

                        ArrayList<Datetime> datetimesList = response.body().getResults().getDatetime();

                        Log.d(TAG, "onResponse: "+datetimesList.get(0).getDate().getGregorian());


                    // اعمل داتاسيت اوبجيكت وحط فيه الداتا من الريترو فيت عشان نكيش الداتا دي
                        databasetest databasetest = new databasetest(datetimesList.get(0).getDate().getGregorian()
                                ,datetimesList.get(0).getTimes().getFajr()
                                ,datetimesList.get(0).getTimes().getDhuhr()
                                ,datetimesList.get(0).getTimes().getAsr()
                                ,datetimesList.get(0).getTimes().getMaghrib()
                                ,datetimesList.get(0).getTimes().getIsha());


                        // اكشلي بكايش الداتا
                        postThred postThred = new postThred(getApplicationContext(),databasetest);
                        postThred.start();

                        // الاوبجيكت بتاع الكلاس بتاع الثريد بتاع رووم
                        getThred customthred = new getThred(getApplicationContext());
                        customthred.start();


                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.toString());
                        Log.d(TAG, "onFailure: "+call);

                    }
                });




        }
        //لو النت مش شغال بقي
        else{
            Log.d(TAG, "internet is down");
            getThred customthred = new getThred(getApplicationContext());
            customthred.start();

        }

    }


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }






   //حاجه حديثه شكلها بتتشك فيه نت ولا
    private Boolean isNetworkAvailable(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }
}








//باكجراوند ثريد عشان رووم مش بتشتغل علي ماين ثريد
class getThred extends Thread{
    Context context;
    //  كونستراكنور بيباصي الكونتكست عشان الاوبجيكت بتاع روم البضان محتاجه ومش هينفع يوصله من الكلاس بتاع الثريد ده
    public getThred(Context context) {
        this.context = context;
    }

    @Override
    public void run() {


        AzanDataBase azanDataBase = AzanDataBase.getInstance(context);
        Log.d(" MainActivity", "run: "+ azanDataBase.postdao().getdatatest());



    }
}







// ثريد تاني بيحط الداتا جوه الرووم
class postThred extends  Thread{


    Context context;
    databasetest databasetest;
    // كونستراجتور بيباصي الكونتكست والداتا الي مفروض تتحط جوه الروم
    public postThred(Context context, com.example.emptyness.databasetest databasetest) {
        this.context = context;
        this.databasetest = databasetest;
    }

    @Override
    public void run() {
        AzanDataBase.getInstance(context).postdao().insert(databasetest);

    }
}