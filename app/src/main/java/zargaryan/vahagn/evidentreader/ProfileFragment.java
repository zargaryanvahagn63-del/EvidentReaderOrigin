package zargaryan.vahagn.evidentreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name = view.findViewById(R.id.user_name);
        TextView books = view.findViewById(R.id.books_read);
        TextView genre = view.findViewById(R.id.favorite_genre);

        // пока заглушка
        name.setText("Vahag");
        books.setText("Books read: 5");
        genre.setText("Favorite genre: Detective");

        return view;
    }
}