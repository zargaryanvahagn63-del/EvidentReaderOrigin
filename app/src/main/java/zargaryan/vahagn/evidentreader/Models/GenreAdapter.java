package zargaryan.vahagn.evidentreader.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zargaryan.vahagn.evidentreader.R;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    List<String> genres;

    public GenreAdapter(List<String> genres) {
        this.genres = genres;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.genre_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }
}