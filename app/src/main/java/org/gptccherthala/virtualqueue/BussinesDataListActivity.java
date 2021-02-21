package org.gptccherthala.virtualqueue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BussinesDataListActivity extends AppCompatActivity {

    private RecyclerView businessRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussines_data_list);

        businessRecView = findViewById(R.id.businessRecView);

        ArrayList<BusinessDatabase> businessDatabase = new ArrayList<>();
        businessDatabase.add(new BusinessDatabase("Jacob"));

        BusinessRecViewAdapter adapter = new BusinessRecViewAdapter();
        adapter.setBusinessDatabase(businessDatabase);

        businessRecView.setAdapter(adapter);

        businessRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}