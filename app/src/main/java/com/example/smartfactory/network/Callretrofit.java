package com.example.smartfactory.network;


import com.example.smartfactory.network.DTO.SensorValueDTO;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;

import java.io.IOException;
class LoginStatus{
    public static final int PERMITTED=0;
    public static final int DENIED=1;
    public static final int EXIST_ID=2;
}
class Env{
    public static final int FACTORY=0;
    public static final int CLIENT=1;
}
public class Callretrofit {

    public static String post_login_request(String ID, String PW) {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_login_request(ID,PW);
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static String post_signUp_request(String ID,String PW) {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_signUp_request(ID,PW);
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public SensorValueDTO[] get_sensor_value_resent_one(){
        SensorValueDTO[] sensorValueDTOS = new SensorValueDTO[0];
        return sensorValueDTOS;
    }
    public SensorValueDTO[][] get_sensor_values_period(@Path("ID") String id, @Body String token){
        SensorValueDTO[][] sensorValueDTOS = new SensorValueDTO[0][];
        return sensorValueDTOS;
    }
    public SensorValueDTO[][] get_sensor_value_all(){
        SensorValueDTO[][] sensorValueDTOS = new SensorValueDTO[0][];
        return sensorValueDTOS;
    }

    public String post_follower(@Body String myID, @Body String targetID){
        return "";
    }
    public String get_follower(@Body String myID){
        return "";
    }
    public String patch_follower(@Body Long followerShipIndex, @Body boolean enable){
        return "";
    }

    public String post_alarm(@Body String myID,@Body Long sensorIndex,  @Body double from, @Body double to){
        return "";
    }
    public String get_alarm(@Body String myID){
        return "";
    }
    public String patch_alarm(@Body Long alarmIndex, @Body double from, @Body double to){
        return "";
    }
    public String delete_alarm(@Body Long alarmIndex){
        return "";
    }
}
