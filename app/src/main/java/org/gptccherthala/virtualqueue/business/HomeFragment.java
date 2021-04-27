package org.gptccherthala.virtualqueue.business;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import org.gptccherthala.virtualqueue.R;

public class HomeFragment extends Fragment {

    View view;
    TextView openOrClose;
    SwitchMaterial openCloseSwitch;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_business, container, false);
        openOrClose = view.findViewById(R.id.shop_detail);
        openCloseSwitch = view.findViewById(R.id.openOrClosed);

        openCloseSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (openCloseSwitch.isChecked())
                openOrClose.setText("Shop Open");
            else
                openOrClose.setText("Shop closed");
        });
        return view;
    }
}