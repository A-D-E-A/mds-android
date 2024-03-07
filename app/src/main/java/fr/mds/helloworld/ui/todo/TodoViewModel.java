package fr.mds.helloworld.ui.todo;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mds.helloworld.data.dao.TodoDao;
import fr.mds.helloworld.data.models.TodoElement;

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
}
