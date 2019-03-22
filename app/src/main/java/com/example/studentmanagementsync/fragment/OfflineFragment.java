package com.example.studentmanagementsync.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.studentmanagementsync.R;
import com.example.studentmanagementsync.core.Sync;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class OfflineFragment extends Fragment {

    private TabLayout tabLayout;
    private Button btn_sync;
    private TextView no_data_id;
    private FrameLayout frameLayout;
    private Sync sync;

    public OfflineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_offline, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.id_tab_layout);
        btn_sync = (Button) view.findViewById(R.id.btn_sync);

        frameLayout = (FrameLayout) view.findViewById(R.id.id_offline);
        no_data_id = (TextView) view.findViewById(R.id.no_data_id);

        sync = new Sync(view.getContext());

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.id_offline, new OfflineInsertFragment());
        ft.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        ft.replace(R.id.id_offline, new OfflineInsertFragment());
                        ft.commit();
                        return;
                    case 1:
                        ft.replace(R.id.id_offline, new OfflineDeleteFragment());
                        ft.commit();
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sync.isNetworkAvailable()) {
                    sync.getSync();
                    no_data_id.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                } else {
                    Snackbar.make(v, "NO INTERNET CONNECTION", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
