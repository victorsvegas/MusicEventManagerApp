package com.example.gruppprojektet_musicapp.ui.addConcert;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Concert;
import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;
import com.example.gruppprojektet_musicapp.ui.addArtist.AddArtistViewModel;
import com.example.gruppprojektet_musicapp.ui.recomended.RecomendedFragment;

public class AddConcertFragment extends Fragment {

    private AddArtistViewModel addConcertViewModel;

    Database mDatabaseHelper;
    private Button btnAddConcert, btnShowConcertList;
    private EditText concertNameText, concertVenueText, concertLocationText, concertDateText, concertTimeText, concertPriceText, concertRatingText;
    private Concert concert;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addConcertViewModel =
                ViewModelProviders.of(this).get(AddArtistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_concert, container, false);

        // Inputs
        concertNameText = (EditText)root.findViewById(R.id.concertNameText);
        concertVenueText = (EditText)root.findViewById(R.id.concertVenueText);
        concertLocationText = (EditText)root.findViewById(R.id.concertLocationText);
        concertDateText = (EditText)root.findViewById(R.id.concertDateText);
        concertTimeText = (EditText)root.findViewById(R.id.concertTimeText);
        concertPriceText = (EditText)root.findViewById(R.id.concertPriceText);
        concertRatingText = (EditText)root.findViewById(R.id.concertRatingText);

        // Buttons
        btnAddConcert = (Button)root.findViewById(R.id.btnAddConcert);
        btnShowConcertList = (Button)root.findViewById(R.id.btnShowConcertList);

        mDatabaseHelper = new Database(getActivity());
        concert = new Concert();

        btnAddConcert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (concertNameText.length() != 0)
                {
                    AddData();
                }
                else
                {
                    toastMessage("Field cannot be empty!");
                }
            }
        });

        btnShowConcertList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RecomendedFragment recomendedFragment = new RecomendedFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, recomendedFragment).commit();
            }
        });
        return root;
    }

    public void AddData()
    {
        concert.setName(concertNameText.getText().toString().trim());
        concert.setVenue(concertVenueText.getText().toString().trim());
        concert.setLocation(concertLocationText.getText().toString().trim());
        concert.setDate(concertDateText.getText().toString().trim());
        concert.setTime(concertTimeText.getText().toString().trim());
        concert.setPrice(concertPriceText.getText().toString().trim());
        concert.setRating(concertRatingText.getText().toString());
        mDatabaseHelper.addConcert(concert);

        toastMessage("Data succesfully saved!");
    }

    public void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
