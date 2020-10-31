package org.younhong.singlediary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import lib.kingja.switchbutton.SwitchMultiButton;

public class Fragment1 extends Fragment {
    RecyclerView recyclerView;
    NoteAdapter adapter;
    Context context;
    OnTabItemSelectedListener listener;
    
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        
        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) listener;
        }
    }
    
    public void onDetach() {
        super.onDetach();
        
        if (context != null) {
            context = null;
            listener = null;
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        Button todayWriteButton = rootView.findViewById(R.id.todayWriteButton);
        todayWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchButton = rootView.findViewById(R.id.switchButton);
        switchButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getContext(), tabText, Toast.LENGTH_LONG).show();
                adapter.switchLayout(position);
                adapter.notifyDataSetChanged();
            }
        });
        
        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        
        adapter = new NoteAdapter();
        adapter.addItem(new Note(0, "6", "포항 장성동", "", 
                "", "행복해", "0", null, "2월 10일"));
        adapter.addItem(new Note(0, "2", "포항 장성동", "",
                "", "행복해", "2", null, "2월 10일"));
        adapter.addItem(new Note(0, "3", "포항 장성동", "",
                "", "행복해", "4", null, "2월 10일"));
        
        recyclerView.setAdapter(adapter);
        
        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void OnItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);
                Toast.makeText(getContext(), "Selected Item: " + item.getContents(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
