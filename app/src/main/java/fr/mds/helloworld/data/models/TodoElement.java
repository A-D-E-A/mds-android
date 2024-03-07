package fr.mds.helloworld.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_element")
public class TodoElement {
    public int getId() {
        return id;
    }

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @ColumnInfo(name = "title")
    private String mTitle;

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean mDone) {
        this.mDone = mDone;
    }

    @ColumnInfo(name = "done")
    private boolean mDone;

    public TodoElement(String name, boolean done) {
        mDone = done;
        mTitle = name;
    }
}
