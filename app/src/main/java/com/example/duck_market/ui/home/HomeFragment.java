package com.example.duck_market.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.duck_market.DataManger;
import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View root;
    private TextView textView;;

    final ArrayList<Merchandise> MDarry = new ArrayList<>();
    private List<String> searchlist;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        searchlist=new ArrayList<String>();
        searchlist.add("aaa");
        searchlist.add("aaabbbb");
        searchlist.add("bbb");

        final AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)root.findViewById(R.id.autoSearchView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, searchlist);
        autoCompleteTextView.setAdapter(adapter);


        Button btn_search = root.findViewById(R.id.searchButton);

        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = (Intent) new Intent(getActivity(), SearchList.class);
                DataManger dataManger=DataManger.getInstance();
                dataManger.setSerarch_word(autoCompleteTextView.getText().toString());
                startActivity(intent);

            }
        });
        return root;
    }


}