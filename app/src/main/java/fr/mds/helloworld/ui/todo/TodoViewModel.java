package fr.mds.helloworld.ui.todo;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mds.helloworld.data.dao.TodoDao;
import fr.mds.helloworld.data.models.Student;
import fr.mds.helloworld.data.models.TodoElement;
import fr.mds.helloworld.utils.AsyncRunner;

public class TodoViewModel extends AndroidViewModel {
    public TodoViewModel(@NonNull Application application) {
        super(application);
    }

    private TodoDao mDao;

    public void setDao(TodoDao dao) {
        mDao = dao;
    }

    public LiveData<List<TodoElement>> getAll() {
        return mDao.getAllTodo();
    }

    public LiveData<TodoElement> getById(int id) {
        return mDao.getTodo(id);
    }

    public void createTodoTask(String title) {
        TodoElement todo = new TodoElement(title, false);
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.insertTodo(todo);
        });
    }

    public void updateTodoTask(TodoElement task) {
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.updateTodo(task);
        });
    }

    public void deleteTodoTask(TodoElement task) {
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.deleteTodo(task);
        });
    }
}
