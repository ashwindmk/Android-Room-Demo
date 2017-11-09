package com.example.roomdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomdemo.db.AppDatabase;
import com.example.roomdemo.db.User;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDatabase;
    private static String usersString = "";

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
        initViews();
    }

    private void initDatabase() {
        mDatabase = AppDatabase.getInMemoryDatabase(getApplicationContext());
    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.textView);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usersString.equals("")) {
                    addUsers();
                    loadUsers();
                    mButton.setText("DELETE");
                } else {
                    deleteAllUsers();
                    mButton.setText("LOAD");
                }
            }
        });
    }

    private void addUsers() {
        addUser("1", "Alice", 23);
        addUser("2", "Brenda", 25);
        addUser("3", "Clara", 21);
    }

    private void addUser(String id, String name, int age) {
        User user = new User();
        user.id = id;
        user.age = age;
        user.name = name;
        mDatabase.userModel().insertUser(user);
    }

    private void loadUsers() {
        StringBuilder sb = new StringBuilder();
        List<User> users = mDatabase.userModel().loadAllUsers();
        for (User user : users) {
                sb.append(String.format(Locale.US, "%s. %s (%d)\n\n", user.id, user.name, user.age));
        }
        usersString = sb.toString();
        mTextView.setText(usersString);
    }

    private void deleteAllUsers() {
        mDatabase.userModel().deleteAll();
        loadUsers();
    }

}
