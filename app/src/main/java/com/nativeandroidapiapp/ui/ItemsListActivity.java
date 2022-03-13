package com.nativeandroidapiapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nativeandroidapiapp.R;
import com.nativeandroidapiapp.data.ReceivedItem;
import com.nativeandroidapiapp.service.IRestService;
import com.nativeandroidapiapp.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsListActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        progressDoalog = new ProgressDialog(ItemsListActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        IRestService service = RetrofitClient.getInstance().getRestService();
        Call<List<ReceivedItem>> call = service.getItems();
        call.enqueue(new Callback<List<ReceivedItem>>() {

            @Override
            public void onResponse(Call<List<ReceivedItem>> call, Response<List<ReceivedItem>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ReceivedItem>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(ItemsListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<ReceivedItem> receivedItems) {
        ItemAdapter.OnItemClickListener onItemClickListener = new ItemAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(ReceivedItem receivedItem, int position) {
                Intent intent = new Intent(ItemsListActivity.this, ItemActivity.class);
                intent.putExtra("item_id", receivedItem.getId());
                startActivity(intent);
            }
        };
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ItemAdapter(this,receivedItems, onItemClickListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ItemsListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        }
    }