package et.edu.aait.itsc.at.android.abo;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    LinearLayout full;
    Button button;
    TextView name,date,feels_like,dew_point,humidity_,wind_speed,pressure_,discription_,
            sat,sun,mon,tue,wen,thu,fri,temp,

            sat_feels_like,sat_dew_point,sat_discription,
            sun_feels_like,sun_dew_point,sun_discription,
            mon_feels_like,mon_dew_point,mon_discription,
            tue_feels_like,tue_dew_point,tue_discription,
            wen_feels_like,wen_dew_point,wen_discription,
            thu_feels_like,thu_dew_point,thu_discription,
            fri_feels_like,fri_dew_point,fri_discription;

    Spinner spinner;
    ImageView homeWeatherIconImg,sat_imageView11,sun_imageView12,mon_imageView13,tue_imageView14,
              wen_imageView15,thu_imageView16,fri_imageView17,weatherBgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        full=(LinearLayout)findViewById(R.id.full);
        temp=(TextView)findViewById(R.id.temp);
        sat_imageView11=(ImageView)findViewById(R.id.imageView11);
        sun_imageView12=(ImageView)findViewById(R.id.imageView12);
        mon_imageView13=(ImageView)findViewById(R.id.imageView13);
        tue_imageView14=(ImageView)findViewById(R.id.imageView14);
        wen_imageView15=(ImageView)findViewById(R.id.imageView15);
        thu_imageView16=(ImageView)findViewById(R.id.imageView16);
        fri_imageView17=(ImageView)findViewById(R.id.imageView17);
        weatherBgImage=(ImageView)findViewById(R.id.weatherBgImage);

        name=(TextView)findViewById(R.id.textView4);
        date=(TextView)findViewById(R.id.textView);
        feels_like=(TextView)findViewById(R.id.textView7);
        dew_point=(TextView)findViewById(R.id.textView9);
        humidity_=(TextView)findViewById(R.id.textView25);
        wind_speed=(TextView)findViewById(R.id.textView27);
        pressure_=(TextView)findViewById(R.id.textView29);
        discription_=(TextView)findViewById(R.id.textView30);


        sat=(TextView)findViewById(R.id.textView11);
        sun=(TextView)findViewById(R.id.textView13);
        mon=(TextView)findViewById(R.id.textView15);
        tue=(TextView)findViewById(R.id.textView17);
        wen=(TextView)findViewById(R.id.textView19);
        thu=(TextView)findViewById(R.id.textView21);
        fri=(TextView)findViewById(R.id.textView23);



        sat_feels_like=(TextView)findViewById(R.id.textViewM1);
        sat_dew_point=(TextView)findViewById(R.id.textViewS1);
        sat_discription=(TextView)findViewById(R.id.textView12);

        sun_feels_like=(TextView)findViewById(R.id.textViewM2);
        sun_dew_point=(TextView)findViewById(R.id.textViewS2);
        sun_discription=(TextView)findViewById(R.id.textView14);

        mon_feels_like=(TextView)findViewById(R.id.textViewM3);
        mon_dew_point=(TextView)findViewById(R.id.textViewS3);
        mon_discription=(TextView)findViewById(R.id.textView16);

        tue_feels_like=(TextView)findViewById(R.id.textViewM4);
        tue_dew_point=(TextView)findViewById(R.id.textViewS4);
        tue_discription=(TextView)findViewById(R.id.textView18);

        wen_feels_like=(TextView)findViewById(R.id.textViewM5);
        wen_dew_point=(TextView)findViewById(R.id.textViewS5);
        wen_discription=(TextView)findViewById(R.id.textView20);

        thu_feels_like=(TextView)findViewById(R.id.textViewM6);
        thu_dew_point=(TextView)findViewById(R.id.textViewS6);
        thu_discription=(TextView)findViewById(R.id.textView22);

        fri_feels_like=(TextView)findViewById(R.id.textViewM7);
        fri_dew_point=(TextView)findViewById(R.id.textViewS7);
        fri_discription=(TextView)findViewById(R.id.textView24);

        homeWeatherIconImg=(ImageView)findViewById(R.id.homeWeatherIconImg);

        button=(Button)findViewById(R.id.button);
        spinner=(Spinner)findViewById(R.id.spinner);
        //requestQueue= Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName=String.valueOf(spinner.getSelectedItem());
                String city = null;
                try {
                   city = URLEncoder.encode(cityName, "UTF-8");
               } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
             //   String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=c293ad0707b69c8654d44443ec04b03d";
                String url="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C%20Ethiopia%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject jsonObject_query=response.getJSONObject("query");

                                    JSONObject jsonObject_results=jsonObject_query.getJSONObject("results");
                                    JSONObject jsonObject_channel=jsonObject_results.getJSONObject("channel");
                                    //String title=jsonObject2.optString("title");
                                    JSONObject jsonObject_location=jsonObject_channel.getJSONObject("location");
                                    String city=jsonObject_location.optString("city");
                                    String country=jsonObject_location.optString("country");
                                    name.setText(city+","+country);
                                    JSONObject jsonObject_atmosphere=jsonObject_channel.getJSONObject("atmosphere");
                                    String humidity=jsonObject_atmosphere.optString("humidity");
                                    String pressure=jsonObject_atmosphere.optString("pressure");
                                    humidity_.setText(humidity);
                                    pressure_.setText(pressure);
                                    JSONObject jsonObject_wind=jsonObject_channel.getJSONObject("wind");
                                    String speed=jsonObject_wind.optString("speed");
                                    wind_speed.setText(speed);

                                    JSONObject jsonObject_astronomy=jsonObject_channel.getJSONObject("astronomy");
                                    String sunrise=jsonObject_astronomy.optString("sunrise");
                                    String sunset=jsonObject_astronomy.optString("sunset");
                                    feels_like.setText(sunrise);
                                    dew_point.setText(sunset);


                                    JSONObject jsonObject_item=jsonObject_channel.getJSONObject("item");
                                    JSONObject jsonObject_condition=jsonObject_item.getJSONObject("condition");
                                    String description_home=jsonObject_condition.optString("text");

                                    discription_.setText(description_home);

                                    String date_home=jsonObject_condition.optString("date");
                                    date.setText(date_home);
                                    int temp1=jsonObject_condition.getInt("temp");
                                    String uri3 = "drawable/icon_";
                                    String homeWeatherIconImg1=jsonObject_condition.optString("code");
                                    int imageResourceIcon = getResources().getIdentifier(uri3 + homeWeatherIconImg1, null, getPackageName());
                                    Drawable imageIcon = getResources().getDrawable(imageResourceIcon);
                                    homeWeatherIconImg.setImageDrawable(imageIcon);
//                                    char s = (char) 0x00B0;
//                                    temp.setText(String.format("%.1f%fF", temp, s));
                                    temp.setText(temp1 + " F");
                                   // JSONObject jsonObjectlolo=jsonObject_condition.getJSONObject("")

//                                    if(jsonObject_condition.optString("text").equals("Cloudy")){
//
//                                    String uri2 = "drawable/";
//                                    String code111="clody";
//                                    String code111="clody";
//
//
//
//                                    int imageResource111 = getResources().getIdentifier(uri2+code111, null, getPackageName());
//                                    Drawable image111 = getResources().getDrawable(imageResource111);
//
//                                    weatherBgImage.setImageDrawable(image111);
//                                    }else if(jsonObject_condition.optString("text").equals("Partly Cloudy")){
//                                        String uri2 = "drawable/";
//                                        String code111="thunderstorm";
//
//
//
//                                        int imageResource111 = getResources().getIdentifier(uri2+code111, null, getPackageName());
//                                        Drawable image111 = getResources().getDrawable(imageResource111);
//                                        weatherBgImage.setImageDrawable(image111);
//                                    }else {
//                                        String uri2 = "drawable/";
//                                        String code111="suny";
//
//
//
//                                        int imageResource111 = getResources().getIdentifier(uri2+code111, null, getPackageName());
//                                        Drawable image111 = getResources().getDrawable(imageResource111);
//                                        weatherBgImage.setImageDrawable(image111);
//                                    }

                                    String uri1 = "drawable/icon_";
                                    JSONArray jsonArray_forecast=jsonObject_item.getJSONArray("forecast");
                                   // for(int i=0;i<=8;i++){
                                        JSONObject jsonObject_1=jsonArray_forecast.getJSONObject(1);

                                        String high=jsonObject_1.optString("high");
                                        String low=jsonObject_1.optString("low");
                                        String description=jsonObject_1.optString("text");
                                        String day=jsonObject_1.optString("day");
                                        String code=jsonObject_1.optString("code");

                                        int imageResource = getResources().getIdentifier(uri1 + code, null, getPackageName());
                                        Drawable image = getResources().getDrawable(imageResource);

                                        sat.setText(day);
                                        sat_imageView11.setImageDrawable(image);
                                        sat_feels_like.setText(high +" F");
                                        sat_dew_point.setText(low+"F");
                                        sat_discription.setText(description);


                                        JSONObject jsonObject_2=jsonArray_forecast.getJSONObject(2);
                                        String high1=jsonObject_2.optString("high");
                                        String low1=jsonObject_2.optString("low");
                                        String description1=jsonObject_2.optString("text");
                                        String day1=jsonObject_2.optString("day");
                                    String code1=jsonObject_2.optString("code");
                                      // int code1=jsonObject_2.getInt("Code");
                                        //String uri2 = "drawable/icon_";

                                        int imageResource1 = getResources().getIdentifier(uri1 + code1, null, getPackageName());
                                        Drawable image1 = getResources().getDrawable(imageResource1);



                                        sun.setText(day1);
                                        sun_imageView12.setImageDrawable(image1);
                                        sun_feels_like.setText(high1);
                                        sun_dew_point.setText(low1);
                                        sun_discription.setText(description1);








                                        JSONObject jsonObject_3=jsonArray_forecast.getJSONObject(3);
                                        String high2=jsonObject_3.optString("high");
                                        String low2=jsonObject_3.optString("low");
                                        String description2=jsonObject_3.optString("text");
                                        String day2=jsonObject_3.optString("day");
                                    String code2=jsonObject_3.optString("code");
                                        //int code2=jsonObject_3.getInt("Code");

                                        int imageResource2 = getResources().getIdentifier(uri1 + code2, null, getPackageName());
                                        Drawable image2 = getResources().getDrawable(imageResource2);


                                        mon.setText(day2);
                                        mon_imageView13.setImageDrawable(image2);
                                        mon_feels_like.setText(high2);
                                        mon_dew_point.setText(low2);
                                        mon_discription.setText(description2);

                                        JSONObject jsonObject_4=jsonArray_forecast.getJSONObject(4);
                                        String high3=jsonObject_4.optString("high");
                                        String low3=jsonObject_4.optString("low");
                                        String description3=jsonObject_4.optString("text");
                                        String day3=jsonObject_4.optString("day");
                                        //int code3=jsonObject_4.getInt("Code");
                                    String code3=jsonObject_4.optString("code");

                                        int imageResource3 = getResources().getIdentifier(uri1 + code3, null, getPackageName());
                                        Drawable image3 = getResources().getDrawable(imageResource3);


                                        tue.setText(day3);
                                        tue_imageView14.setImageDrawable(image3);
                                        tue_feels_like.setText(high3);
                                        tue_dew_point.setText(low3);
                                        tue_discription.setText(description3);

                                        JSONObject jsonObject_5=jsonArray_forecast.getJSONObject(5);
                                        String high4=jsonObject_5.optString("high");
                                        String low4=jsonObject_5.optString("low");
                                        String description4=jsonObject_5.optString("text");
                                        String day4=jsonObject_5.optString("day");
                                        //int code4=jsonObject_5.getInt("Code");
                                       String code4=jsonObject_5.optString("code");

                                        int imageResource4 = getResources().getIdentifier(uri1 + code4, null, getPackageName());
                                        Drawable image4 = getResources().getDrawable(imageResource4);



                                        wen.setText(day4);
                                        wen_imageView15.setImageDrawable(image4);
                                        wen_feels_like.setText(high4);
                                        wen_dew_point.setText(low4);
                                        wen_discription.setText(description4);

                                        JSONObject jsonObject_6=jsonArray_forecast.getJSONObject(6);
                                        String high5=jsonObject_6.optString("high");
                                        String low5=jsonObject_6.optString("low");
                                        String description5=jsonObject_6.optString("text");
                                        String day5=jsonObject_6.optString("day");
                                        //int code5=jsonObject_6.getInt("Code");
                                      String code5=jsonObject_6.optString("code");
                                        int imageResource5 = getResources().getIdentifier(uri1 + code5, null, getPackageName());
                                        Drawable image5 = getResources().getDrawable(imageResource5);


                                        thu.setText(day5);
                                        thu_imageView16.setImageDrawable(image5);
                                        thu_feels_like.setText(high5);
                                        thu_dew_point.setText(low5);
                                        thu_discription.setText(description5);

                                        JSONObject jsonObject_7=jsonArray_forecast.getJSONObject(7);
                                        String high6=jsonObject_7.optString("high");
                                        String low6=jsonObject_7.optString("low");
                                        String description6=jsonObject_7.optString("text");
                                        String day6=jsonObject_7.optString("day");
                                       // int code6=jsonObject_7.getInt("Code");
                                       String code6=jsonObject_7.optString("code");
                                        int imageResource6 = getResources().getIdentifier(uri1 + code6, null, getPackageName());
                                        Drawable image6 = getResources().getDrawable(imageResource6);


                                        fri.setText(day6);
                                        fri_imageView17.setImageDrawable(image6);
                                        fri_feels_like.setText(high6);
                                        fri_dew_point.setText(low6);
                                        fri_discription.setText(description6);






                                  //  }


                                   // String region=jsonObject3.optString("region");

//                                   String abrham=response.getString("name");
//                                    JSONArray jsonArray = response.getJSONArray("weather");
//                                 for (int i = 0; i < jsonArray.length(); i++) {
//                                        JSONObject forecast = jsonArray.getJSONObject(i);
//                                       String day=forecast.getString("main");
//                                        String high=forecast.getString("description");
                                        //String low=forecast.getString("low");
                                        //String text=forecast.getString("text");
                                      // textView.setText(city);
                                       // textView1.setText(country);
                                    //textView2.setText(region);
                                        //textView2.setText(text);
                                       //textView.append(day+high+low+text);
                                   // }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("gfg", "fgghf");

                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("volley", "error");
                                Toast.makeText(getApplicationContext(), "Server not found ", Toast.LENGTH_LONG).show();
                            }
                        }
                );
                Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
            }
        });


    }
}
