package org.gptccherthala.virtualqueue.business;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.gptccherthala.virtualqueue.R;
import org.gptccherthala.virtualqueue.user.UserDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;
    TextView openOrClose;
    SwitchMaterial openCloseSwitch;
    String name;
    Long phone;
    String bId;
    boolean isOpen;
    private DatabaseReference businessRef;
    private FirebaseFirestore db;

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
        bId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        businessRef = FirebaseDatabase.getInstance().getReference().child("business").child(bId);
        isOpen = false;
        checkIsOpen();

        openCloseSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (openCloseSwitch.isChecked())
                openOrClose.setText("Shop Open");
            else
                openOrClose.setText("Shop Closed");
        });

        initializeRecView();

        return view;
    }

    public void checkIsOpen(){
        businessRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    BusinessDatabase businessDatabase = dataSnapshot.getValue(BusinessDatabase.class);
                    isOpen = businessDatabase.isOpen;
                    if (isOpen) {
                        openOrClose.setText("Shop Open");
                        openCloseSwitch.setChecked(true);
                    } else{
                        openOrClose.setText("Shop Open");
                        openOrClose.setText("Shop Closed");
                    }
                } else {
                    isOpen = false;
                }
            }
        });
    }

    public void initializeRecView(){
        ArrayList<UserDatabase> userDatabase = new ArrayList<>();

        RecyclerView mJoinedUsersListRecView = view.findViewById(R.id.joinedUsersRecView);

        JoinedUsersListRecViewAdapter adapter = new JoinedUsersListRecViewAdapter();

        businessRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("Home", "1");

                    db.collection("users").document(ds.getKey())
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()) {
                                name = documentSnapshot.get("Name").toString();
                                phone = Long.parseLong(documentSnapshot.get("Phone").toString());
                                UserDatabase data = new UserDatabase(name, phone);
                                userDatabase.add(data);
                                adapter.setUserDatabase(userDatabase);
                                Log.d("Home", "2");
                            }
                        }
                    });
                }
                Log.d("Home", "3");
            }
        });

        Log.d("Home", "4");
        mJoinedUsersListRecView.setAdapter(adapter);
        mJoinedUsersListRecView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}