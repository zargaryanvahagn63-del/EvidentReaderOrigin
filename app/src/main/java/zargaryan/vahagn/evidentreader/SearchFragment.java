package zargaryan.vahagn.evidentreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import zargaryan.vahagn.evidentreader.Models.Book;
import zargaryan.vahagn.evidentreader.Models.BookAdapter;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class SearchFragment extends Fragment {

    private final List<Book> allBooks = new ArrayList<>();
    private final List<Book> filtered = new ArrayList<>();
    private BookAdapter adapter;

    public SearchFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        TextInputEditText input = view.findViewById(R.id.search_input);
        RecyclerView recycler = view.findViewById(R.id.recycler_search);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BookAdapter(filtered, book -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(book.getFilePath()));
            startActivity(intent);
        });

        recycler.setAdapter(adapter);

        loadBooks(); // загрузка из Firebase

        input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s,int a,int b,int c){}
            public void onTextChanged(CharSequence s,int a,int b,int c){
                filter(s.toString());
            }
            public void afterTextChanged(Editable e){}
        });

        return view;
    }

    private void loadBooks() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("books").get()
                .addOnSuccessListener(query -> {
                    allBooks.clear();

                    for (DocumentSnapshot doc : query) {
                        Book book = doc.toObject(Book.class);
                        allBooks.add(book);
                    }
                });
    }

    // --- ТРИГРАММЫ ---

    private List<String> getTrigrams(String text) {
        List<String> list = new ArrayList<>();
        text = text.toLowerCase();

        for (int i = 0; i < text.length() - 2; i++) {
            list.add(text.substring(i, i + 3));
        }
        return list;
    }

    private double similarity(String input, String tag) {
        List<String> a = getTrigrams(input);
        List<String> b = getTrigrams(tag.toLowerCase());

        int match = 0;

        for (String s : a) {
            if (b.contains(s)) match++;
        }

        return a.isEmpty() ? 0 : (double) match / a.size();
    }

    private void filter(String text) {
        filtered.clear();

        for (Book book : allBooks) {

            // поиск по названию
            if (book.getName() != null &&
                    book.getName().toLowerCase().contains(text.toLowerCase())) {

                filtered.add(book);
                continue;
            }

            // поиск по тегам (умный).

            if (book.getTags() != null) {
                for (String tag : book.getTags()) {
                    if (similarity(text, tag) >= 0.6) {
                        filtered.add(book);
                        break;
                    }
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}