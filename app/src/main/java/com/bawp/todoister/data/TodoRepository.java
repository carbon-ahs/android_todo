package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class TodoRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public TodoRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.insert(task));
    }

    public LiveData<Task> get(long id) { return taskDao.get(id); }

    public void update(Task task) {
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.delete(task));
    }
}
