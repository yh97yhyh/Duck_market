package com.example.duck_market.ui.Message;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import com.example.duck_market.R;
import com.example.duck_market.User2;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    private View root;
    private List<String> idlist;
    ListView m_ListView;
    CustomAdapter m_Adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_message, container, false);

        //아이디 검색창
        idlist=new ArrayList<String>();
        idlist.add("leeminju");
        idlist.add("kimmunju");
        idlist.add("hongjiyeon");

        final AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)root.findViewById(R.id.id_search_textview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, idlist);
        autoCompleteTextView.setAdapter(adapter);

        ArrayList<User2> user2Array = new ArrayList<>();
        User2 mj=new User2("leeminju","민주","디원스 상점");
        User2 yh=new User2("yeonghyun","영현","방탄점");
        User2 yg=new User2("Jiyugyeong","유경","재환점");

        user2Array.add(mj);
        user2Array.add(yh);
        user2Array.add(yg);

        ArrayAdapter<User2> idadapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, user2Array);
        final ListView listView = (ListView)root.findViewById(R.id.chat_listView);
        listView.setAdapter(idadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent intent;
                intent = (Intent)new Intent(getActivity(), com.example.duck_market.ui.Message.Activity_chatting.class);
                startActivity(intent);
            }
        });


        return root;
    }

}
