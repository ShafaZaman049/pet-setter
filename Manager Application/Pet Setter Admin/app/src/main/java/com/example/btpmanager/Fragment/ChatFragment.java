package com.example.btpmanager.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.btpmanager.Adapter.BotMessageAdapter;
import com.example.btpmanager.Model.BotResponseMessage;
import com.example.btpmanager.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ChatFragment extends Fragment {
    EditText userInput;
    RecyclerView recyclerView;
    BotMessageAdapter messageAdapter;
    List<BotResponseMessage> responseMessageList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_chat, container, false);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        userInput = view.findViewById(R.id.userInput);
        recyclerView = view.findViewById(R.id.conversation);
        responseMessageList = new ArrayList<>();
        messageAdapter = new BotMessageAdapter(responseMessageList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);
        BotResponseMessage responseMessage4 = new BotResponseMessage("Thank you for filling up the information and being a helpful hand.".toString(), false);
        responseMessageList.add(responseMessage4);
        BotResponseMessage responseMessage3 = new BotResponseMessage("I am Foodie your virtual assistant here to guide you with food delivery process.".toString(), false);
        responseMessageList.add(responseMessage3);


        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {

                    if (userInput.getText().toString().toLowerCase().contains("hi")) {
                        BotResponseMessage responseMessage = new BotResponseMessage(userInput.getText().toString(), true);
                        responseMessageList.add(responseMessage);
                        BotResponseMessage responseMessage5 = new BotResponseMessage("Hello".toString(), false);
                        responseMessageList.add(responseMessage5);
                        userInput.setText("");
                    } else if (userInput.getText().toString().toLowerCase().contains("time")) {
                        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   HH:mm");
                        String date = sdf.format((Date) (new java.sql.Date(timeStamp.getTime())));
                        BotResponseMessage responseMessage = new BotResponseMessage(userInput.getText().toString(), true);
                        responseMessageList.add(responseMessage);
                        BotResponseMessage responseMessage7 = new BotResponseMessage(date.toString(), false);
                        responseMessageList.add(responseMessage7);
                        userInput.setText("");
                    } else if (userInput.getText().toString().toLowerCase().contains("how are you")) {
                        BotResponseMessage responseMessage = new BotResponseMessage(userInput.getText().toString(), true);
                        responseMessageList.add(responseMessage);
                        BotResponseMessage responseMessage6 = new BotResponseMessage("Pretty good! How about you?".toString(), false);
                        responseMessageList.add(responseMessage6);
                        userInput.setText("");
                    } else if (userInput.getText().toString().toLowerCase().contains("open") && userInput.getText().toString().toLowerCase().contains("google")) {

                        Intent site = new Intent("android.intent.action.VIEW");
                        site.setData(Uri.parse("https://www.google.com/"));
                        getContext().startActivity(site);

                        BotResponseMessage responseMessage = new BotResponseMessage(userInput.getText().toString(), true);
                        responseMessageList.add(responseMessage);
                        BotResponseMessage responseMessage8 = new BotResponseMessage("Opening Google...", false);
                        responseMessageList.add(responseMessage8);
                        userInput.setText("");
                    } else {
                        BotResponseMessage responseMessage = new BotResponseMessage(userInput.getText().toString(), true);
                        responseMessageList.add(responseMessage);
                        BotResponseMessage responseMessage2 = new BotResponseMessage("Im still learning".toString(), false);
                        userInput.setText("");
                        responseMessageList.add(responseMessage2);
                        messageAdapter.notifyDataSetChanged();
                        if (!isLastVisible())
                            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    }
                }
                return false;
            }
        });
        return view;
    }


    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }

}