package com.example.smartfactory.Activity.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.smartfactory.Activity.SensorValueAdapter.SensorValueItem;
import com.example.smartfactory.Activity.SensorValueAdapter.SensorValueAdapter;
import com.example.smartfactory.Activity.UserItemAdapter.Context;
import com.example.smartfactory.Activity.UserItemAdapter.UserItemAdapter;
import com.example.smartfactory.Activity.UserItemAdapter.UserItem;
import com.example.smartfactory.Activity.FollowershipSearchActivity;
import com.example.smartfactory.R;
import com.example.smartfactory.network.Callretrofit;
import com.example.smartfactory.network.DTO.SensorValue;
import com.example.smartfactory.network.GetMyrelation;
import com.example.smartfactory.network.VO.Sensor;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SensorValueAdapter mRecyclerAdapter;
    private ArrayList<SensorValueItem> mSensorValueItems;
    private DrawerLayout drawerLayout;
    private View drawerView;
    public static String userId;
    Button btn_open;
    private UserItemAdapter followAdapter;
    private ArrayList<UserItem> followsArray;
    private RecyclerView followsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId=getIntent().getStringExtra("user_id");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView =(View)findViewById(R.id.drawer);
        //메뉴 열기 버튼
        btn_open = (Button)findViewById(R.id.btn_open);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        followsRecyclerView= findViewById(R.id.followRecycler);


        initDrawer();
        initmRecycler();
       //------------------------------//



        Thread getSensorValueThread= new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Sensor[] sensors=Callretrofit.get_all_sensor(userId);
                    SensorValue[] sensorValues = Callretrofit.get_sensor_value_resent_one(userId);
                    mSensorValueItems =new ArrayList<>();
                    for (int i = 0; i < sensors.length; i++) {
                        for (int j = 0;  j<sensorValues.length ; j++) {
                            if(sensors[i].getName().equals(sensorValues[j].getName())) {
                                long idx=sensors[i].getIndex();
                                String n = sensorValues[j].getName();
                                String v = sensorValues[j].getValue();
                                mSensorValueItems.add(new SensorValueItem(idx, n, v));
                            }
                        }
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerAdapter.setFriendList(mSensorValueItems);
                            mRecyclerAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        getSensorValueThread.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                GetMyrelation getMyrelation=new GetMyrelation();
                getMyrelation.start();
                try {
                    getMyrelation.join();//릴레이션 분류 작업이 끝나야 버튼 클릭 수행 가능함
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        followsArray=getMyrelation.getMoveContextUsers();
                        followAdapter.setFriendList(followsArray);
                    }
                });

            }
        }.start();
    }

    private void initmRecycler() {
        /* initiate adapter */
        mRecyclerAdapter = new SensorValueAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        /* adapt data */
        mSensorValueItems = new ArrayList<>();
        for(int i=1;i<=10;i++){
            mSensorValueItems.add(new SensorValueItem(i,"상태"+i,"상태메시지"));
        }
        mRecyclerAdapter.setFriendList(mSensorValueItems);


        followAdapter=new UserItemAdapter(followsArray);
        followsRecyclerView.setAdapter(followAdapter);
        followsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        followsArray=new ArrayList<>();

        for(int i=1;i<=10;i++){
            followsArray.add(new UserItem(i,"상태"+i, Context.move));
        }
        followAdapter.setFriendList(followsArray);

    }

    private void initDrawer() {
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        //메뉴 닫기 버튼
        Button btn_close = (Button)findViewById(R.id.drawer_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });
        Button btn_move= findViewById(R.id.drawerMoveToFollowBtn);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), FollowershipSearchActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    //DrawerLayout 명령어들
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}