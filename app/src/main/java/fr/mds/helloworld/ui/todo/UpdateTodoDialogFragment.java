package fr.mds.helloworld.ui.todo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.TodoElement;

public class UpdateTodoDialogFragment extends DialogFragment {
    private EditText mTaskTitle;
    private EditText mTaskIsDone;
    private int mTodoId;
    private TodoViewModel mViewModel;
    private boolean mIsTitleValid = false;
    private boolean mIsDoneValid = false;

    public static UpdateTodoDialogFragment newInstance(int todoId) {
        UpdateTodoDialogFragment fragment = new UpdateTodoDialogFragment();
        fragment.mTodoId = todoId;

        return fragment;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        @SuppressLint("inflateParams")
        final View dialogView = requireActivity().getLayoutInflater().inflate(R.layout.dialog_todo_update, null);

        builder.setView(dialogView)
                .setPositiveButton("Valider", (dialog, which) -> {
                    if (!mIsDoneValid || !mIsTitleValid) { return; }
                    String title = mTaskTitle.getText().toString();
                    String isDoneStr = mTaskIsDone.getText().toString();
                    boolean isDone = isDoneStr.equals("todo") ? false : isDoneStr.equals("done") ? true : null;
                    updateTodo(title, isDone);
                })
                .setNegativeButton("Annuler", (dialog, which) -> {
                    requireDialog().cancel();
                });

        mTaskTitle = dialogView.findViewById(R.id.todo_update_editText);
        mTaskIsDone = dialogView.findViewById(R.id.todo_update_editText2);

        mViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        mViewModel.setDao(AppDatabase.getInstance(getContext()).getTodoDao());

        mViewModel.getById(mTodoId).observe(this, (todo) -> {
            mTaskTitle.setText(todo.getTitle());
            mTaskIsDone.setText(todo.isDone() ? "done" : "todo");
        });

        final AlertDialog dialog = builder.create();

        mTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mIsTitleValid = isTitleValid(s.toString());
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(mIsDoneValid && mIsTitleValid);
            }
        });

        mTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mIsDoneValid = isDoneValid(s.toString());
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(mIsDoneValid && mIsTitleValid);
            }
        });

        return dialog;
    }

    private boolean isTitleValid(String text) {
        boolean isLongEnough = text.length() >= 2;
        boolean isShortEnough = text.length() <= 30;
        return isLongEnough && isShortEnough;
    }

    private boolean isDoneValid(String text) {
        return text.equals("done") || text.equals("todo");
    }

    private void updateTodo(String title, boolean isDone) {
        mViewModel.getById(mTodoId).observe(this, (todo) -> {
            todo.setTitle(title);
            todo.setDone(isDone);
            mViewModel.updateTodoTask(todo);
        });
    }
}
