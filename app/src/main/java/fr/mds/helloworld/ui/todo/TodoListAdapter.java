package fr.mds.helloworld.ui.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.models.TodoElement;

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private final List<TodoElement> mDataSet;
    private final FragmentManager mFragmentManager;

    public TodoListAdapter(List<TodoElement> dataset, @NonNull FragmentManager fm) {
        mDataSet = dataset;
        mFragmentManager = fm;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo, parent, false);
        return new TodoViewHolder(view, mFragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.bindTo(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
