package org.darkbyte.classifieds.ChatService;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.darkbyte.classifieds.R;

import java.util.ArrayList;
import java.util.List;

public class Chat_Service extends AppCompatActivity {
    private RecyclerView recyclerView;
    ChatAdapter mAdapter;
    private TextView text_Chat;
    private  RecyclerView.LayoutManager mLayoutManager;
    private  List<modelChat> modelChats;
    String user = "parvesh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__service);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        text_Chat  = (EditText)findViewById(R.id.text_chat_send);

        text_Chat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {

                    // Do whatever you want
                    if (!text_Chat.getText().toString().isEmpty()){
                             mAdapter.refresh(modelChats,Chat_Service.this,user);
                    String message = text_Chat.getText().toString();
                    modelChats.add(new modelChat(message,user));
                                               text_Chat.setText("");
                    recyclerView.scrollToPosition(modelChats.size()-1);      }

                    return true;

                }
                return false;
            }
        });
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if ( bottom < oldBottom) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.scrollToPosition(modelChats.size()-1);
                        }
                    }, 100);
                }
            }
        });
        modelChats = new ArrayList<modelChat>();
        modelChats.add(new modelChat("this is mesfsdfdgfdgfdgfdgfdgdfgfdgfdgfdgfdgdfgdfgdfgsage","parvesh"));
        modelChats.add(new modelChat("this is messagfgdfgfdgdfgfdgfdgdfgdfgdfgdfe","ok"));
        modelChats.add(new modelChat("this is message","parvesh"));
        modelChats.add(new modelChat("this is message","ok"));
        modelChats.add(new modelChat("this is message","parvesh"));
        modelChats.add(new modelChat("this is message","ok"));

        mAdapter = new ChatAdapter(modelChats,this,"parvesh");
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}
