package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sportsFragment extends Fragment {

    String api = "de3ca9148d6d4f5c89463a94d448adc6";
    ArrayList<ModalClass> modalClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewofsports;
    private String category = "sports" ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.sportsfragment , null);
        recyclerViewofsports = v.findViewById(R.id.recyclerviewofsports);
        recyclerViewofsports.setLayoutManager(new LinearLayoutManager(getContext()));
        modalClassArrayList = new ArrayList<>();
        adapter = new Adapter(getContext(), modalClassArrayList);
        recyclerViewofsports.setAdapter(adapter);

        findNews();

        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategorynews(country , category , 100 , api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    modalClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}


