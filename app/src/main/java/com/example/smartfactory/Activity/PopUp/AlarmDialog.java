package com.example.smartfactory.Activity.PopUp;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.smartfactory.Activity.MainActivity;
import com.example.smartfactory.Item;
import com.example.smartfactory.R;
import com.example.smartfactory.network.Callretrofit;
import com.example.smartfactory.network.DTO.AlarmDTO;

import java.util.List;
import java.util.Objects;


public class AlarmDialog extends Dialog implements View.OnClickListener{
    private DialogListener dialogListener;
    private Context context;
    private EditText editText_min;
    private EditText editText_max;
    protected static List<AlarmDTO> alarmDTOS;
    public AlarmDTO alarm;
    public boolean hasAlarm=false;
    private Item item;


    public AlarmDialog(@NonNull Context context, Item item) {
        super(context);
        this.context = context;
        new Thread(){
            @Override
            public void run() {
                super.run();
                AlarmDialog.alarmDTOS=Callretrofit.get_alarm(MainActivity.userId);
                if(!Objects.isNull(alarmDTOS)) {
                    for (int i = 0; i < alarmDTOS.size(); i++) {
                        if (alarmDTOS.get(i).getSensorIndex() == item.getSensorIndex()) {
                            alarm = alarmDTOS.get(i);
                            System.out.println("AlarmDialog.run: 생성자에서 찾은 내역 min = "+alarm.getMinimum()+" max = "+ alarm.getMaximum());
                        }
                    }
                }
            }
        }.start();
        this.item=item;


    }

    public void setDialogListener(DialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_dailog);
        editText_min = findViewById(R.id.minSensorValue);
        editText_max = findViewById(R.id.maxSensorValue);
        if (!Objects.isNull(alarm)) {
            editText_min.setText(alarm.getMinimum() + "");
            editText_max.setText(alarm.getMaximum() + "");
        }

        findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.onPositiveClicked(editText_min.getText().toString()+"", editText_max.getText().toString()+"");
                dismiss();
            }
        });
        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.onNegativeClicked();
                cancel();
            }
        });

        Log.e("test", "e123");
    }

    @Override
    public void onClick(View view) { }
}