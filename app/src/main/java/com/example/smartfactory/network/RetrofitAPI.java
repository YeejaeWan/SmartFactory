package com.example.smartfactory.network;


import com.example.smartfactory.network.DTO.SensorValueDTO;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitAPI {

@POST ("login") Call<String> post_login_request(@Body String ID, @Body String PW);
@POST ("sign_up") Call<String> post_signUp_request(@Body String ID, @Body String PW);

@GET ("sensors_value/{ID}/resent_one") Call<SensorValueDTO[]>get_sensor_value_resent_one();
@GET ("sensors_value/{ID}/") Call<SensorValueDTO[][]>get_sensor_values_period(@Body String ID, @Body String token, @Body String from, @Body String to);
@GET ("sensors_value/{ID}/all") Call<SensorValueDTO[][]>get_sensor_value_all();

@POST ("followership") Call<String> post_follower(@Body String myID, @Body String targetID);
@GET ("followership") Call<String> get_follower(@Body String myID);
@PATCH ("followership") Call<String> patch_follower(@Body Long followerShipIndex,@Body boolean enable);

@POST ("alarm") Call<String> post_alarm(@Body String myID,@Body Long sensorIndex,  @Body double from, @Body double to);
@GET ("alarm") Call<String> get_alarm(@Body String myID);
@PATCH ("alarm") Call<String> patch_alarm(@Body Long alarmIndex, @Body double from, @Body double to);
@DELETE ("alarm") Call<String> delete_alarm(@Body Long alarmIndex);




}