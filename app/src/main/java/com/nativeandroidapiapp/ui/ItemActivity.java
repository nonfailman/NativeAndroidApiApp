package com.nativeandroidapiapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nativeandroidapiapp.R;
import com.nativeandroidapiapp.data.ReceivedItem;
import com.nativeandroidapiapp.service.IRestService;
import com.nativeandroidapiapp.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);



        Bundle arguments = getIntent().getExtras();

        IRestService service = RetrofitClient.getInstance().getRestService();
        Call<ReceivedItem> call = service.getItem(arguments.getInt("item_id"));
        call.enqueue(new Callback<ReceivedItem>() {
            @Override
            public void onResponse(Call<ReceivedItem> call, Response<ReceivedItem> response) {
                if(response.body() != null){
                    populateActivity(response.body());
                }


            }

            @Override
            public void onFailure(Call<ReceivedItem> call, Throwable t) {
                Toast.makeText(ItemActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateActivity(ReceivedItem receivedItem){

        TextView titleView = findViewById(R.id.title);
        TextView shDescView = findViewById(R.id.short_description);
        TextView osView = findViewById(R.id.os);
        TextView processorView = findViewById(R.id.processor);
        TextView memoryView = findViewById(R.id.memory);
        TextView graphicsView = findViewById(R.id.graphics);
        TextView storageView = findViewById(R.id.storage);

        ImageView thumbnailView = findViewById(R.id.thumbnail);


        titleView.setText(receivedItem.getTitle());
        shDescView.setText(receivedItem.getShort_description());
        osView.setText(receivedItem.getSystemRequirements().getOs());
        processorView.setText(receivedItem.getSystemRequirements().getProcessor());
        memoryView.setText(receivedItem.getSystemRequirements().getMemory());
        graphicsView.setText(receivedItem.getSystemRequirements().getGraphics());
        storageView.setText(receivedItem.getSystemRequirements().getStorage());

        Picasso.Builder builder = new Picasso.Builder(ItemActivity.this);
        builder.build().load(receivedItem.getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(thumbnailView);
    }
}