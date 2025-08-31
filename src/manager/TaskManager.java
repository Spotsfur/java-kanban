package manager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    //Методы для типа Задача
    ArrayList<Task> getTasks();

    void clearTasks();

    Task getTask(int id);

    void putTask(Task task);

    void updateTask(Task task, int id);

    void removeTask(int id);

    //Методы для типа Эпик
    ArrayList<Epic> getEpics();

    void clearEpic();

    Epic getEpic(int id);

    void putEpic(Epic epic);

    void updateEpic(Epic epic, int id);

    void removeEpic(int epicId);

    ArrayList<SubTask> getEpicSubTasks(int id);

    //Методы для типа Подзадача
    ArrayList<SubTask> getSubTasks();

    void clearSubTasks();

    SubTask getSubTask(int id);

    void putSubTask(SubTask subTask);

    void updateSubTask(SubTask subTask, int id);

    void removeSubTask(int id);

    List<Task> getHistory();
}
