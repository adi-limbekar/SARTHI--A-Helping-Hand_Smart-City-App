package com.fyp.saarthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class waterTankNumbersAdapter extends FirestoreRecyclerAdapter<waterTankNumbersModel, waterTankNumbersAdapter.waterTankNumbersViewHolder> {
    private OnWaterListItemClick onWaterListItemClick;
    private Context context;

    public waterTankNumbersAdapter(@NonNull FirestoreRecyclerOptions<waterTankNumbersModel> options,OnWaterListItemClick onWaterListItemClick,Context context) {
        super(options);
        this.onWaterListItemClick=onWaterListItemClick;
        this.context=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull waterTankNumbersViewHolder holder, int position, @NonNull waterTankNumbersModel model) {
        holder.tank_name.setText(model.getTankName());
        holder.tank_number.setText(model.getTankNumber());
        holder.area.setText(model.getArea());
    }

    @NonNull
    @Override
    public waterTankNumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.watertanklist_item_single,parent,false);
        return new waterTankNumbersViewHolder(view);

    }

    public class waterTankNumbersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tank_name;
        private TextView tank_number;
        private TextView area;
        public waterTankNumbersViewHolder(@NonNull View itemView) {
            super(itemView);
            tank_name=itemView.findViewById(R.id.tank_name);
            tank_number=itemView.findViewById(R.id.tank_number);
            area = itemView.findViewById(R.id.area);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onWaterListItemClick.onItemClick(getItem(getAdapterPosition()),getAdapterPosition(),itemView);

        }
    }
    public interface OnWaterListItemClick{
        void onItemClick(waterTankNumbersModel snapshot, int position, View view);

    }
}
