package com.example.smartfactory.network;

import com.example.smartfactory.Activity.UserItemAdapter.Context;
import com.example.smartfactory.Activity.mainActivity.MainActivity;
import com.example.smartfactory.Activity.UserItemAdapter.UserItem;
import com.example.smartfactory.network.DTO.FollowerShipDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GetMyrelation extends Thread {
    private static ArrayList<UserItem> moveContextUsers;
    private static ArrayList<UserItem> cancelContextUsers;
    private static ArrayList<UserItem> removeContextUsers;
    private static ArrayList<UserItem> requestContextUsers;

    @Override
    public void run() {
        List<FollowerShipDTO> followList = Callretrofit.get_follow(MainActivity.userId);
        List<FollowerShipDTO> followerList = Callretrofit.get_follower(MainActivity.userId);

        List<FollowerShipDTO> rawFollowTrueList = new ArrayList<>();//무브
        List<FollowerShipDTO> rawFollowFalseList = new ArrayList<>();//취소
        List<FollowerShipDTO> rawFollowerTrueList = new ArrayList<>();//삭제
        List<FollowerShipDTO> rawFollowerFalseList = new ArrayList<>();//요청
        for (FollowerShipDTO f : followList) {
            if (f.isEnable()) {
                rawFollowTrueList.add(f);

            } else {
                rawFollowFalseList.add(f);

            }
        }
        for (FollowerShipDTO f : followerList) {
            if (f.isEnable()) {
                rawFollowerTrueList.add(f);
                System.out.println("GetMyrelation.run:: f.isEnabled so ->" + f.getIndex() + " " + f.getFollowerUserIndex() + " follows -> " + f.getFollowUserIndex());
            } else {
                rawFollowerFalseList.add(f);

            }
        }
        this.moveContextUsers = initData(rawFollowTrueList, Context.move);
        this.cancelContextUsers = initData(rawFollowFalseList, Context.requested);
        this.removeContextUsers = initData(rawFollowerTrueList, Context.delete);
        this.requestContextUsers = initData(rawFollowerFalseList, Context.request);

    }
    private ArrayList<UserItem> initData(List<FollowerShipDTO>list, String context){
        ArrayList<UserItem> resultArray=new ArrayList<>();
        BlockingQueue<Runnable> blockingQueue= new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,100,30, TimeUnit.SECONDS,blockingQueue);
        List<Future<String>> jobRunnableFuture= new ArrayList<>();
        for (FollowerShipDTO follow:list) {
            jobRunnableFuture.add (threadPoolExecutor.submit(() -> {
                String result = "";
                if(context.equals(Context.move)||context.equals(Context.requested)) {
                    result = Callretrofit.get_userID_of_by_index(follow.getFollowUserIndex());
                }
                if(context.equals(Context.request)||context.equals(Context.delete)) {
                    result = Callretrofit.get_userID_of_by_index(follow.getFollowerUserIndex());
                }
                return result;
            }));
        } //풀에 넣어 아이디를 가져옴

        for (int i = 0; i < list.size(); i++) {
            Future<String> result=jobRunnableFuture.get(i);
            try {
                resultArray.add(new UserItem(list.get(i).getIndex(), result.get(), context));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultArray;
    }

    public ArrayList<UserItem> getMoveContextUsers() {
        try {
            this.join();
        }catch (InterruptedException e){e.printStackTrace();}
            return moveContextUsers;
    }

    public ArrayList<UserItem> getCancelContextUsers() {
        try {
            this.join();
        }catch (InterruptedException e){e.printStackTrace();}
        return cancelContextUsers;
    }

    public ArrayList<UserItem> getRemoveContextUsers() {
        try {
            this.join();
        }catch (InterruptedException e){e.printStackTrace();}
        return removeContextUsers;
    }

    public ArrayList<UserItem> getRequestContextUsers() {
        try {
            this.join();
        }catch (InterruptedException e){e.printStackTrace();}
        return requestContextUsers;
    }
}