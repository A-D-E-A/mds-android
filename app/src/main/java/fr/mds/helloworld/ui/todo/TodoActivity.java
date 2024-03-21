package fr.mds.helloworld.ui.todo;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.TodoElement;

public class TodoActivity extends AppCompatActivity {
    private TodoViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        mViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        mViewModel.setDao(AppDatabase.getInstance(getApplicationContext()).getTodoDao());

        final RecyclerView recyclerView = findViewById(R.id.todo_rv);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mViewModel.getAll().observe(this, todoElements -> {
            TodoListAdapter adapter = new TodoListAdapter(todoElements, getSupportFragmentManager());
            recyclerView.setAdapter(adapter);
        });

        Button addButton = findViewById(R.id.todo_add);
        addButton.setOnClickListener((view) -> {
            AddTodoDialogFragment.newInstance().show(getSupportFragmentManager(), "dialog");
        });
    }
}