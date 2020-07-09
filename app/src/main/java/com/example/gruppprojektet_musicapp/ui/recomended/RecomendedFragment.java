package com.example.gruppprojektet_musicapp.ui.recomended;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;
import com.example.gruppprojektet_musicapp.ui.ConcertInfo.ConcertInfoFragment;
import com.example.gruppprojektet_musicapp.ui.addArtist.AddArtistFragment;

import java.util.ArrayList;

public class RecomendedFragment extends Fragment {

    private RecomendedViewModel recomendedViewModel;

    private static final String TAG = "RecomendedFragment";
    Database mDatabaseHelper;
    private ListView mListView;
    private Button btnAddNew;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recomendedViewModel =
                ViewModelProviders.of(this).get(RecomendedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recomended, container, false);

        mListView = (ListView) root.findViewById(R.id.listRecomended);
        mDatabaseHelper = new Database(getActivity());
        btnAddNew = (Button) root.findViewById(R.id.addNewArtist);

        btnAddNew.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddArtistFragment addArtistFragment = new AddArtistFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, addArtistFragment).commit();
            }
        });
        populateListView();
        return root;
    }

    public void populateListView()
    {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        // Get data and bind to list
        Cursor data = mDatabaseHelper.getAllConcerts();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {

            listData.add(data.getString(0));
        }
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                mDatabaseHelper = new Database(getActivity());
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                //Getting data from the right concert/artist
                Cursor data;
                String concertID = mDatabaseHelper.getConcertId(name);
                Cursor artist = mDatabaseHelper.getArtist(concertID);
                artist.moveToFirst();
                Cursor concert = mDatabaseHelper.getConcert(concertID);
                concert.moveToFirst();

                ArrayList<String> artistList = new ArrayList<>();
                ArrayList<String> concertList = new ArrayList<>();

                //Adding Data to the lists
                artistList.add(artist.getString(0));
                artistList.add(artist.getString(1));
                artistList.add(artist.getString(2));
                artistList.add(artist.getString(3));
                artistList.add(artist.getString(4));
                artistList.add(artist.getString(5));
                artistList.add(artist.getString(6));
                //Concert
                concertList.add(concert.getString(0));
                concertList.add(concert.getString(1));
                concertList.add(concert.getString(2));
                concertList.add(concert.getString(3));
                concertList.add(concert.getString(4));
                concertList.add(concert.getString(5));
                concertList.add(concert.getString(6));
                concertList.add(concert.getString(7));
                concertList.add(concert.getString(8));

                //Sending the list via bundle to ConcertInfo
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("artist", artistList);
                bundle.putStringArrayList("concert", concertList);
                //Skickar värden till send fragment
                ConcertInfoFragment concertInfoFragment = new ConcertInfoFragment();
                concertInfoFragment.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, concertInfoFragment).commit();

            }


        });

    }

    private void toastMessage(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}