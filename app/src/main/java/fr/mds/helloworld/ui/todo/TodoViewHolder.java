package fr.mds.helloworld.ui.todo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.models.TodoElement;

public class TodoViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;
    private TextView mDone;

    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.todo_title);
        mDone = itemView.findViewById(R.id.todo_done);
    }

    public void bindTo(TodoElement element) {
        mTitle.setText(element.getTitle());
        mDone.setText(element.isDone() ? "Done" : "Todo");
    }
}
