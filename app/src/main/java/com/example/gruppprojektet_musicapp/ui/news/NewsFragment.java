package com.example.gruppprojektet_musicapp.ui.news;

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
import com.example.gruppprojektet_musicapp.ui.NewsInfo.NewsInfoFragment;
import com.example.gruppprojektet_musicapp.ui.addNews.AddNewsFragment;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;

    private static final String TAG = "NewsFragment";
    Database mDatabaseHelper;
    private ListView mListView;
    private Button btnAddNew;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        mListView = (ListView) root.findViewById(R.id.listNews);
        mDatabaseHelper = new Database(getActivity());
        btnAddNew = (Button) root.findViewById(R.id.addNewNews);

        btnAddNew.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddNewsFragment addNewsFragment = new AddNewsFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment, addNewsFragment).commit();
            }
        });
        populateListView();
        return root;
    }

    public void populateListView()
    {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        // Get data and bind to list
        Cursor data = mDatabaseHelper.getAllNews();
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
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getAllNews();
                int itemID = -1;
                while(data.moveToNext())
                {
                    itemID = data.getInt(0);
                }
                if (itemID > -1)
                {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);

                    // GÅ TILL METOD FÖR NEWSINFO
                    NewsInfoFragment newsInfoFragment = new NewsInfoFragment();

                    itemID++;
                    String sendId = Integer.toString(itemID);
                    Bundle bundle = new Bundle();
                    bundle.putString("clickedID", sendId);
                    newsInfoFragment.setArguments(bundle);

                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.nav_host_fragment, newsInfoFragment).commit();
                }
                else
                {
                    toastMessage("No ID associated with that name!");
                }
            }
        });
    }

    private void toastMessage(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}