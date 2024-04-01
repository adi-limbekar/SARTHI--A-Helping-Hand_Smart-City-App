package com.fyp.saarthi;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaterDept extends Fragment {


    public WaterDept() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_water_dept, container, false);


        CardView cardView1= view.findViewById(R.id.CARD1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ActivityDisplayWaterTiming.class);
                getActivity().startActivity(myIntent);
            }
        });
        CardView cardView2= view.findViewById(R.id.CARD2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ActivityWaterTankNumbers.class);
                getActivity().startActivity(myIntent);
            }
        });
        return view;
    }

}
