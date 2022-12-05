package com.example.smartfactory.network;


import android.util.Log;

import com.example.smartfactory.network.DTO.AlarmDTO;
import com.example.smartfactory.network.DTO.FollowerShipDTO;
import com.example.smartfactory.network.DTO.SensorValue;
import com.example.smartfactory.network.VO.FollowshipVO;
import com.example.smartfactory.network.VO.LoginVO;
import com.example.smartfactory.network.VO.Sensor;
import com.example.smartfactory.network.VO.SignUpVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

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

    public static String post_login_request(String userId, String pw) {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_login_request(new LoginVO(userId,pw,Env.CLIENT));
        String response = null;
        try {
            response= call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public static Sensor[] get_all_sensor(String userId){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<Sensor[]> call = service.get_all_sensor(userId);
        Sensor[] response = null;
        try {
            response=call.execute().body();
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
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static SensorValue[][] get_sensor_values_period(String userId, String from, String to){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<SensorValue[][]> call = service.get_sensor_values_period(userId,from,to);
        SensorValue[][] response = null;
        try {
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static SensorValue[][] get_sensor_value_all(String userId){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<SensorValue[][]> call = service.get_sensor_value_all(userId);
        SensorValue[][] response = null;
        try {
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public static void post_follower(FollowshipVO followshipVO){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_follower(followshipVO);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("post_follower.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("post_follower.onFailure");
            }
        });
    }
    public static List<FollowerShipDTO> get_follow(String myID){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<List<FollowerShipDTO>> call = service.get_follow(myID);
        List<FollowerShipDTO> response = null;
        try {
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static List<FollowerShipDTO> get_follower(String myID){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<List<FollowerShipDTO>> call = service.get_follower(myID);
        List<FollowerShipDTO> response = null;
        try {
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static void patch_follower(Long followerShipIndex,  boolean enable){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.patch_follower(followerShipIndex,enable);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("patch_follower.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                 System.out.println("patch_follower.onFailure");

            }
        });
    }
    public static void delete_follower(Long followerShipIndex){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.delete_follower(followerShipIndex);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("patch_follower.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("patch_follower.onFailure");

            }
        });
    }

    public static void post_alarm(String userId,AlarmDTO alarmDTO){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_alarm(userId,alarmDTO);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("post_alarm.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("post_alarm.onFailure");
            }
        });
    }

    public static List<AlarmDTO> get_alarm(String myID){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<List<AlarmDTO>> call = service.get_alarm(myID);
        List<AlarmDTO> response = null;
        try {
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static void patch_alarm(AlarmDTO alarmDTO){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.patch_alarm(alarmDTO);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("patch_alarm.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("patch_alarm.onFailure");
            }
        });
    }
    public static void delete_alarm(Long alarmIndex){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.delete_alarm(alarmIndex);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("delete_alarm.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("delete_alarm.onFailure");
            }
        });
    }

    public static void post_push_token(String pushToken ,String userId){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.postPushToken(pushToken,userId);
        String response = null;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("post_push_token.onResponse");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("post_push_token.onFailure");
            }
        });
    }
    public static List<String> get_list_of_user_by(String findingUser){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<List<String>> call = service.get_list_of_user_by(findingUser);
        List<String> response = null;
        try {

            response=call.execute().body();
            for (String s:response) {
                System.out.println("Callretrofit.get_list_of_user_by:: receive "+s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static String get_userID_of_by_index(long index){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.get_userID_of_by_index(index);
        String response = null;
        try {
            response=call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
