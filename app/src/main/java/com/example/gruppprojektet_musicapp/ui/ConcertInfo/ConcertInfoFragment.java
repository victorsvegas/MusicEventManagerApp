package com.example.gruppprojektet_musicapp.ui.ConcertInfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ConcertInfoFragment extends Fragment {

    private ConcertInfoViewModel concertInfoViewModel;
    Database database;
    //private ListView listView;
    public static SQLiteDatabase setupDB = null;
    Button btnInterested, btnGoing;
    String entry;
    TextView topText,bandText,genreText,ratingText,infoText,whenText,whereText,priceText;
    ArrayList artistList,concertList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        concertInfoViewModel =
                ViewModelProviders.of(this).get(ConcertInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_concertinfo, container, false);

        //varibles
        topText = root.findViewById(R.id.textViewTop);
        bandText = root.findViewById(R.id.textViewBand);
        genreText = root.findViewById(R.id.textViewGenre);
        ratingText = root.findViewById(R.id.textViewRating);
        infoText = root.findViewById(R.id.textViewInfo);
        whenText = root.findViewById(R.id.textViewWhen);
        whereText = root.findViewById(R.id.textViewWhere);
        priceText = root.findViewById(R.id.textViewPrice);
        Bundle bundle= getArguments();


        if (bundle.containsKey("data"))
        {
            Log.d(TAG, "From Map");
            entry = bundle.get("data").toString();
            ArrayList<String> artistList = new ArrayList<>();
            ArrayList<String> concertList = new ArrayList<>();

            //String entry = bundle.toString();
            String trimdEntry = entry.trim();

            Database database = new Database(getActivity());
            setupDB = database.getWritableDatabase();

            Cursor check = setupDB.rawQuery("select * from " + "concert", null);
            check.moveToFirst();

            if (check.moveToFirst())
            {
            }
            else
            {
                database.addData();
                Log.d(TAG, "Adding data to database");
            }


            //Get Concert ID
            Log.d(TAG, "RHCP " + entry);
            String concertID = database.getConcertId(entry);
            //Get all info about concert
            Cursor concert = database.getConcert(concertID);
            concert.moveToFirst();

            //Get all info about artist
            Cursor artist = database.getArtist(concertID);

            if (artist.moveToFirst())
            {
                artistList.add(artist.getString(0));
                artistList.add(artist.getString(1));
                artistList.add(artist.getString(2));
                artistList.add(artist.getString(3));
                artistList.add(artist.getString(4));
                artistList.add(artist.getString(5));
                artistList.add(artist.getString(6));

                concertList.add(concert.getString(0));
                concertList.add(concert.getString(1));
                concertList.add(concert.getString(2));
                concertList.add(concert.getString(3));
                concertList.add(concert.getString(4));
                concertList.add(concert.getString(5));
                concertList.add(concert.getString(6));
                concertList.add(concert.getString(7));
                concertList.add(concert.getString(8));

                DisplayConcert(artistList,concertList);
            }
            else
            {
                Log.d(TAG, "Artist Empty");
            }
        }
        //////////////////FRÅN RECOMENDED//////////////////////////////
        else if (bundle.containsKey("artist"))
        {
            Log.d(TAG, "From Recommended");

            artistList = bundle.getStringArrayList("artist");
            concertList = bundle.getStringArrayList("concert");
            entry = concertList.get(2).toString();
            DisplayConcert(artistList, concertList);

        }

        else
        {

            bandText.setText("");
            whereText.setText("");
            infoText.setText("");
            whenText.setText("");
            priceText.setText("");
            ratingText.setText("");
            topText.setText("");
            genreText.setText("");
        }


        final String emailFromIntent = getActivity().getIntent().getStringExtra("EMAIL");
        btnInterested = root.findViewById(R.id.buttonInterested);
        btnInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new Database(getActivity());
                database.setMyShowsInterested(emailFromIntent, entry);
                toast("Added");

            }


        });

        btnGoing = root.findViewById(R.id.buttonGoing);
        btnGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new Database(getActivity());
                database.setMyShowsGoing(emailFromIntent, entry);
                toast("Added");

            }


        });


        return root;
    }

    public void DisplayConcert(ArrayList artist, ArrayList concert)
    {
        if (artist.size() != 0)
        {
            //Från Artist
            bandText.setText(artist.get(6).toString());
            infoText.setText(artist.get(1).toString());
            ratingText.setText(artist.get(2).toString());
            genreText.setText(artist.get(3).toString());

            if (concert.size()!= 0) {
                //Från Concert
                whereText.setText("    " + concert.get(4).toString());
                whenText.setText("    " + concert.get(6).toString());
                priceText.setText("    " + concert.get(5).toString());

                topText.setText(artist.get(6).toString() + " / " + concert.get(4).toString());
            }
            else
            {
                Log.d(TAG, "Concert Empty");
            }
        }
        else
        {
            Log.d(TAG, "Artist Empty");
        }
    }

    private void toast(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}