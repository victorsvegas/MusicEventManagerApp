package com.example.gruppprojektet_musicapp.ui.concerts;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;

import java.util.ArrayList;

public class ConcertsFragment extends Fragment {

    private ConcertsViewModel concertsViewModel;

    private static final String TAG = "ConcertsFragment";
    Database mDatabaseHelper;
    private ListView ListViewIntersted;
    private ListView ListViewGoing;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        concertsViewModel =
                ViewModelProviders.of(this).get(ConcertsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_concerts, container, false);

        ListViewIntersted = (ListView) root.findViewById(R.id.listMyShowsIntereted);
        ListViewGoing = (ListView) root.findViewById(R.id.listMyShowsGoing);
        mDatabaseHelper = new Database(getActivity());
        populateListView();
        return root;
    }

    public void populateListView()
    {
        mDatabaseHelper = new Database(getActivity());

        String emailFromIntent = getActivity().getIntent().getStringExtra("EMAIL");

        Cursor interested = mDatabaseHelper.getIntrested(emailFromIntent);
        ArrayList<String> listIntrested = new ArrayList<>();
        while(interested.moveToNext())
        {
            listIntrested.add(interested.getString(0));
        }
        ListAdapter adapterIntrested = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listIntrested);
        ListViewIntersted.setAdapter(adapterIntrested);

        Cursor going = mDatabaseHelper.getGoing(emailFromIntent);
        ArrayList<String> listGoing = new ArrayList<>();
        while(going.moveToNext())
        {
            listGoing.add(going.getString(0));
        }
        ListAdapter adapterGoing = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listGoing);
        ListViewGoing.setAdapter(adapterGoing);
    }

    private void toastMessage(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}