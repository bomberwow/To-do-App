package com.spirovski.todoister.data;


import android.app.Application;

import com.spirovski.todoister.model.Task;
import com.spirovski.todoister.util.TaskRoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DoisterRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public DoisterRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
         TaskRoomDatabase.databaseWriterExecutor.execute( () -> taskDao.insertTask(task));
    }
    public LiveData<Task> get(long id) { return taskDao.get(id); }

    public void update(Task task) {
         TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
         TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.delete(task));
    }

}
