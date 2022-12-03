package com.example.smartfactory.Activity.mainActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfactory.Activity.mainActivity.PopUp.AlarmDialog;
import com.example.smartfactory.Activity.mainActivity.PopUp.DialogListener;
import com.example.smartfactory.R;
import com.example.smartfactory.network.Callretrofit;
import com.example.smartfactory.network.DTO.AlarmDTO;

import java.util.ArrayList;
import java.util.Objects;

public class SensorValueRecyclerAdapter extends RecyclerView.Adapter<SensorValueRecyclerAdapter.ViewHolder> {

    private ArrayList<SensorValueItem> mList;
    ViewGroup parent;
    @NonNull
    @Override
    public SensorValueRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent=parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_value_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorValueRecyclerAdapter.ViewHolder holder, int position) {
        mList.get(position).setResourceId(position);
        holder.onBind(mList.get(position));
    }


    public void setFriendList(ArrayList<SensorValueItem> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView message;
        Button addAlarmButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.name);
            message = (TextView) itemView.findViewById(R.id.message);
            addAlarmButton= (Button) itemView.findViewById(R.id.addAlarmButton);
        }

        void onBind(SensorValueItem sensorValueItem){

            name.setText(sensorValueItem.getName());
            message.setText(sensorValueItem.getMessage());
            AlarmDialog dialog = new AlarmDialog(parent.getContext(), sensorValueItem);
            addAlarmButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100,240,240,240)));

                    dialog.setDialogListener(new DialogListener() {
                        @Override
                        public void onPositiveClicked(String minSensorValue, String maxSensorValue) {
                            //알람 내용이 없으면 생성(post)요청
                            if (Objects.isNull(dialog.alarm)) {
                                Callretrofit.post_alarm(MainActivity.userId,new AlarmDTO(null, sensorValueItem.getSensorIndex(),Double.parseDouble(minSensorValue),Double.parseDouble(maxSensorValue),null,null));
                                System.out.println("ViewHolder.onPositiveClicked: "+ sensorValueItem.getSensorIndex()+"번 센서"+Double.parseDouble(minSensorValue)+"<최소|최대>"+Double.parseDouble(maxSensorValue));
                            }
                            //알람 내용이 있으면 수정(patch)요청인데
                            else {//빈칸으로 확인하면 삭제(delete)
                                if (minSensorValue.equals("") || maxSensorValue.equals("")) {
                                    Callretrofit.delete_alarm(dialog.alarm.getIndex());
                                    System.out.println("ViewHolder.onPositiveClicked: "+ sensorValueItem.getSensorIndex()+"번 센서 알람 삭제");
                                    return;
                                } else {//값이 있다면 수정(patch)
                                    Callretrofit.patch_alarm(new AlarmDTO(dialog.alarm.getIndex(), dialog.alarm.getSensorIndex(),Double.parseDouble(minSensorValue),Double.parseDouble(maxSensorValue),null,null));
                                    System.out.println("ViewHolder.onPositiveClicked: "+dialog.alarm.getSensorIndex()+"번 센서"+Double.parseDouble(minSensorValue)+"<최소|최대>"+Double.parseDouble(maxSensorValue));

                                }

                            }

                        }

                        @Override
                        public void onNegativeClicked() {
                            Toast.makeText(parent.getContext(), "iiii", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
            });

        }
    }
}