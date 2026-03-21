package zargaryan.vahagn.evidentreader;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import zargaryan.vahagn.evidentreader.Models.User;
import zargaryan.vahagn.evidentreader.R;

public class CreateAccountActivity extends AppCompatActivity {

    /* FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        MaterialEditText name = findViewById(R.id.name);
        MaterialEditText email = findViewById(R.id.email);
        MaterialEditText password = findViewById(R.id.password);
        MaterialEditText repeatPassword = findViewById(R.id.repeat_password);
        ConstraintLayout container = findViewById(R.id.container);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Create account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Snackbar.make(container, "Please enter your name", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(container, "Please enter your email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
                    Snackbar.make(container, "Please enter your password correctly", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User(name.getText().toString(), email.getText().toString(), password.getText().toString());
                        users.child(user.getName()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(container, "Account created successfully", Snackbar.LENGTH_SHORT).show();

                            }
                    });
                }
                });
            }
        });

        // dialog.show(); */

    }
}