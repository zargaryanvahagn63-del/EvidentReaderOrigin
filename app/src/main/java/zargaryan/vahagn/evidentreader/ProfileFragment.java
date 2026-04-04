package zargaryan.vahagn.evidentreader;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import zargaryan.vahagn.evidentreader.Models.User;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView userName = view.findViewById(R.id.user_name);
        TextView userEmail = view.findViewById(R.id.user_email);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("users");

        if (auth.getCurrentUser() != null) {
            String uid = auth.getCurrentUser().getUid();

            users.child(uid).get().addOnSuccessListener(snapshot -> {
                if (!isAdded()) return;

                if (!snapshot.exists()) {
                    userEmail.setText("No data found");
                    return;
                }

                User user = snapshot.getValue(User.class);
                if (user != null) {
                    userName.setText(user.getName() != null ? user.getName() : "No name");
                    userEmail.setText(user.getEmail() != null ? user.getEmail() : "No email");
                }
            }).addOnFailureListener(e -> {
                if (!isAdded()) return;
                userEmail.setText("Error: " + e.getMessage());
            });
        }

        // Fixed the ClassCastException by using TextView instead of Button
        TextView signOut = view.findViewById(R.id.sign_out);
        if (signOut != null) {
            signOut.setOnClickListener(v -> {
                auth.signOut();
                if (getActivity() != null) {
                    getActivity().finish();
                }
            });
        }

        return view;
    }
}