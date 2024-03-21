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

public class AddTodoDialogFragment extends DialogFragment {
    private EditText mTaskInput;

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        @SuppressLint("InflateParams")
        View dialogView = requireActivity().getLayoutInflater().inflate(R.layout.dialog_todo_add, null);

        builder.setView(dialogView)
                .setPositiveButton("Valider", (dialog, which) -> {
                    String title = mTaskInput.getText().toString();
                    if (title.length() >= 2 && title.length() <= 30) {
                        addTodoElement(title);
                    }
                }).setNegativeButton("Annuler", (dialog, which) -> {
                    requireDialog().cancel();
                });

        final AlertDialog dialog = builder.create();

        mTaskInput = dialogView.findViewById(R.id.todo_add_editText);
        mTaskInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                boolean isLongEnough = text.length() >= 2;
                boolean isShortEnough = text.length() <= 30;
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(isShortEnough && isLongEnough);
            }
        });

        return dialog;
    }

    private void addTodoElement(String title) {
        TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
        vm.setDao(AppDatabase.getInstance(getContext()).getTodoDao());
        vm.createTodoTask(title);
    }

    public static AddTodoDialogFragment newInstance() {
        return new AddTodoDialogFragment();
    }
}
