package com.example.sa_f.fragment_f;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sa_f.R;
import com.example.sa_f.database.Infor;
import com.example.sa_f.database.MyAPIService;
import com.example.sa_f.database.RetrofitManager;
import com.example.sa_f.database.record;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    private hot_Adapter adapter;
    private RecyclerView recyclerView;
    MyAPIService myAPIService;
    //ArrayList<Infor> list = new ArrayList<>();
    View view;

    public HotFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);

        return view;
    }

    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myAPIService = RetrofitManager.getInstance().getAPI();
        Call<record> call = myAPIService.getInfor();

        call.enqueue(new Callback<record>() {
            @Override
            public void onResponse(Call<record> call, Response<record> response) {
                load(response.body().getRecordList());
            }

            @Override
            public void onFailure(Call<record> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void load(ArrayList<Infor> list){

        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        adapter = new hot_Adapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}

