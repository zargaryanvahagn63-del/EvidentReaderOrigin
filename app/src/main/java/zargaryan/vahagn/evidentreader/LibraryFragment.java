package zargaryan.vahagn.evidentreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import zargaryan.vahagn.evidentreader.Models.BookAdapter;

public class LibraryFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        View view = inflater.inflate(R.layout.fragment_library, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler_library);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        // пока пусто или тестовые данные
        recycler.setAdapter(new BookAdapter(new ArrayList<>(), book -> {}));

        return view;
    }
}