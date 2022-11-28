package com.example.smartfactory.network;


import com.example.smartfactory.network.DTO.AlarmDTO;
import com.example.smartfactory.network.DTO.SensorValue;
import com.example.smartfactory.network.VO.FollowshipVO;
import com.example.smartfactory.network.VO.LoginVO;
import com.example.smartfactory.network.VO.SignUpVO;

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
        Call<String> call = service.post_login_request(new LoginVO(ID,PW,Env.CLIENT));
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
        Call<String> call = service.post_signUp_request(new SignUpVO(ID,PW));
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static SensorValue[] get_sensor_value_resent_one(String userId){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<SensorValue[]> call = service.get_sensor_value_resent_one(userId);
        SensorValue[] response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static SensorValue[][] get_sensor_values_period( String id){
        SensorValue[][] sensorValues = new SensorValue[0][];
        return sensorValues;
    }
    public static SensorValue[][] get_sensor_value_all(){
        SensorValue[][] sensorValues = new SensorValue[0][];
        return sensorValues;
    }

    public static String post_follower(FollowshipVO followshipVO){
        return "";
    }
    public static String get_follow(String myID){
        return "";
    }
    public static String get_follower(String myID){
        return "";
    }
    public static String patch_follower(Long followerShipIndex,  boolean enable){
        return "";
    }

    public static String post_alarm(AlarmDTO alarmDTO){
        return "";
    }
    public static String get_alarm(String myID){
        return "";
    }
    public static String patch_alarm(Long alarmIndex, double from, double to){
        return "";
    }
    public static String delete_alarm(Long alarmIndex){
        return "";
    }
}
