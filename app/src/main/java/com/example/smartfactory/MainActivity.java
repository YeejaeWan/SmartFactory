package com.example.smartfactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.smartfactory.network.Callretrofit;
import com.example.smartfactory.network.DTO.SensorValue;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<Item> mItems;
    private DrawerLayout drawerLayout;
    private View drawerView;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId=getIntent().getStringExtra("user_id");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView =(View)findViewById(R.id.drawer);

        //메뉴 열기 버튼
        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        //메뉴 닫기 버튼
        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });


        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
       //------------------------------//



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /* initiate adapter */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        Thread thread= new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SensorValue[] sensorValues = Callretrofit.get_sensor_value_resent_one(userId);
                    mItems=new ArrayList<>();
                    for(int i = 0; i< sensorValues.length; i++){
                        mItems.add(new Item(sensorValues[i].getSensorName(), sensorValues[i].getSensorValue()));
                    }
                    mRecyclerAdapter.notifyDataSetChanged();

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });
                }

            }
        };
        /* adapt data */
        mItems = new ArrayList<>();
        for(int i=1;i<=10;i++){
            mItems.add(new Item("상태"+i,"상태메시지"));
        }
        mRecyclerAdapter.setFriendList(mItems);
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