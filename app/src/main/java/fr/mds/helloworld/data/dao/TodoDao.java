package fr.mds.helloworld.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.mds.helloworld.data.models.TodoElement;

@Dao
public interface TodoDao {
    @Insert
    public void insertTodo(TodoElement... elements);

    @Update
    public void updateTodo(TodoElement element);

    @Delete
    public void deleteTodo(TodoElement element);

    @Query("SELECT * FROM `todo_element`")
    public LiveData<List<TodoElement>> getAllTodo();

    @Query("SELECT * FROM `todo_element` where `todo_element`.`id` = :id")
    public LiveData<TodoElement> getTodo(int id);
}
