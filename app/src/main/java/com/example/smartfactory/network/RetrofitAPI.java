package com.example.smartfactory.network;


import com.example.smartfactory.network.DTO.AlarmDTO;
import com.example.smartfactory.network.DTO.FollowerShipDTO;
import com.example.smartfactory.network.DTO.SensorValue;
import com.example.smartfactory.network.VO.FollowshipVO;
import com.example.smartfactory.network.VO.LoginVO;
import com.example.smartfactory.network.VO.Sensor;
import com.example.smartfactory.network.VO.SignUpVO;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitAPI {

@POST ("login") Call<String> post_login_request(@Body LoginVO loginVO);
@POST ("sign_up") Call<String> post_signUp_request(@Body SignUpVO signUpVO);

@GET("sensors/{ID}") Call<Sensor[]> get_all_sensor(@Path("ID") String id);

@GET ("sensors_value/{ID}/resent_one") Call<SensorValue[]>get_sensor_value_resent_one(@Path("ID")String id);
@GET ("sensors_value/{ID}?from={from}&to={to}") Call<SensorValue[][]>get_sensor_values_period(@Path("ID") String ID, @Path("from") String from, @Path("to") String to);
@GET ("sensors_value/{ID}/all") Call<SensorValue[][]>get_sensor_value_all(@Path("ID") String ID);

@POST ("followership") Call<String> post_follower(@Body FollowshipVO followshipVO);
@GET ("followership/{ID}/follow") Call<List<FollowerShipDTO>> get_follow(@Path("ID") String myID);
@GET ("followership/{ID}/follower") Call<List<FollowerShipDTO>> get_follower(@Path("ID") String myID);
@PATCH ("followership?enable={enable}") Call<String> patch_follower(@Body Long followerShipIndex,@Path("enable") boolean enable);
@DELETE ("followership/{ID}") Call<String> delete_follower(@Path("ID") Long followerShipIndex);

@POST ("alarm/{ID}") Call<String> post_alarm(@Path("ID") String myID ,@Body AlarmDTO alarmDTO);
@GET ("alarm/{ID}") Call<List<AlarmDTO>> get_alarm(@Path("ID") String myID);
@PATCH ("alarm") Call<String> patch_alarm(@Body AlarmDTO alarmDTO);
@DELETE ("alarm/{index}") Call<String> delete_alarm(@Path("index") Long alarmIndex);

@POST("pushToken/{ID}") Call<String> postPushToken(@Body String pushToken, @Path("ID") String user_id);

}