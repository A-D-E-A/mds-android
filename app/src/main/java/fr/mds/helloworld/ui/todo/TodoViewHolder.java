package fr.mds.helloworld.ui.todo;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.models.TodoElement;

public class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private int mTaskId;
    private final TextView mTitle;
    private final TextView mDone;
    private final ImageButton mUpdateButton;
    private final ImageButton mDeleteButton;
    private final FragmentManager mFragmentManager;

    public TodoViewHolder(@NonNull View itemView, @NonNull FragmentManager fragmentManager) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.todo_title);
        mDone = itemView.findViewById(R.id.todo_done);
        mUpdateButton = itemView.findViewById(R.id.todo_update);
        mDeleteButton = itemView.findViewById(R.id.todo_delete);
        mFragmentManager = fragmentManager;
    }

    public void bindTo(TodoElement element) {
        mTitle.setText(element.getTitle());
        mDone.setText(element.isDone() ? "Done" : "Todo");

        mUpdateButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();

        if (viewId == R.id.todo_update) {
            UpdateTodoDialogFragment fragment = UpdateTodoDialogFragment.newInstance(mTaskId);
            fragment.show(mFragmentManager, "dialog");
            return;
        }

        if (viewId == R.id.todo_delete) {
            DeleteTodoDialogFragment fragment = DeleteTodoDialogFragment.newInstance(mTaskId);
            fragment.show(mFragmentManager, "dialog");
            return;
        }
    }
}
