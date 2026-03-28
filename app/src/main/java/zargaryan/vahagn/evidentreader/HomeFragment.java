package zargaryan.vahagn.evidentreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import zargaryan.vahagn.evidentreader.Models.GenreAdapter;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler_genres);

        recycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        List<String> genres = Arrays.asList(
                "Fantasy","Sci-Fi","Mystery","Romance","Horror",
                "Thriller","Adventure","Drama","Comedy","History"
        );

        recycler.setAdapter(new GenreAdapter(genres));

        return view;
    }
}