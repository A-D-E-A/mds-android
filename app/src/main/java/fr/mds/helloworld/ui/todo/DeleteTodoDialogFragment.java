package fr.mds.helloworld.ui.todo;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import fr.mds.helloworld.data.database.AppDatabase;

public class DeleteTodoDialogFragment extends DialogFragment {
    private int mTodoId;

    public static DeleteTodoDialogFragment newInstance(int todoId) {
        DeleteTodoDialogFragment fragment = new DeleteTodoDialogFragment();
        fragment.mTodoId = todoId;

        return fragment;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setMessage("Voulez-vous vraiment supprimer cette tâche ?")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    deleteTask();
                })
                .setNegativeButton("Annuler", (dialog, which) -> {
                    dialog.cancel();
                });

        return builder.create();
    }

    private void deleteTask() {
        final TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
        vm.setDao(AppDatabase.getInstance(getContext()).getTodoDao());
        vm.getById(mTodoId).observe(this, vm::deleteTodoTask);
    }
}
