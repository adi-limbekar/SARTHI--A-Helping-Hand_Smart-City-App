package com.fyp.saarthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityDisplayWaterTiming extends AppCompatActivity {
    private FirebaseFirestore wtfirebaseFirestore;
    private RecyclerView wtFirestoreList;
    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_water_timing);
        waterTimingModel waterTiming = (waterTimingModel) getIntent().getSerializableExtra("waterTiming");
        wtfirebaseFirestore = FirebaseFirestore.getInstance();
        wtFirestoreList = findViewById(R.id.waterTimingList);

        Query query = wtfirebaseFirestore.collection("waterDateAndTime");
        FirestoreRecyclerOptions<waterTimingModel> options = new FirestoreRecyclerOptions.Builder<waterTimingModel>()
                .setQuery(query, waterTimingModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<waterTimingModel, ActivityDisplayWaterTiming.waterTimingViewHolder>(options) {
            @NonNull
            @Override
            public ActivityDisplayWaterTiming.waterTimingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.watertiming_item_single, parent, false);
                return new waterTimingViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ActivityDisplayWaterTiming.waterTimingViewHolder holder, int position, @NonNull waterTimingModel model) {
                holder.area.setText(model.getArea());
                holder.date.setText(model.getDate());
                holder.time.setText(model.getTime());
            }
        };
        wtFirestoreList.setHasFixedSize(true);
        wtFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        wtFirestoreList.setAdapter(adapter);
    }



    public static class waterTimingViewHolder extends RecyclerView.ViewHolder  {
        private TextView area;
        private TextView date;
        private TextView time;

        public waterTimingViewHolder(@NonNull View itemView) {
            super(itemView);
            area = itemView.findViewById(R.id.area);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


}

