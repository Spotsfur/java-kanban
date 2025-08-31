package manager;

import manager.Managers;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.Status;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int taskId;
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    public InMemoryTaskManager() {
        taskId = 0;
    }

    //Методы для типа Задача
    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public Task getTask(int id) {
        historyManager.addTask(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public void putTask(Task task) {
        taskId++;
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    @Override
    public void updateTask(Task task, int id) {
        if (tasks.containsKey(id)) {
            tasks.get(id).setName(task.getName());
            tasks.get(id).setDescription(task.getDescription());
        }
    }

    @Override
    public void removeTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

    //Методы для типа Эпик
    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void clearEpic() {
        subTasks.clear();
        epics.clear();
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.addTask(epics.get(id));
        return epics.get(id);
    }

    @Override
    public void putEpic(Epic epic) {
        taskId++;
        epic.setId(taskId);
        epics.put(taskId, epic);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        if (epics.containsKey(id)) {
            epics.get(id).setName(epic.getName());
            epics.get(id).setDescription(epic.getDescription());
        }
    }

    @Override
    public void removeEpic(int epicId) {
        if (epics.containsKey(epicId)) {
            ArrayList<Integer> subTasksIds = epics.get(epicId).getSubTasksIds();
            for (int subTaskId : subTasksIds) {
                subTasks.remove(subTaskId);
            }
            epics.remove(epicId);
        }
    }

    private Status updateEpicStatus(int epicId) {
        Status result = epics.get(epicId).getStatus();
        ArrayList<Integer> subTasksIds = epics.get(epicId).getSubTasksIds();
        if (!subTasksIds.isEmpty()) {
            int firstSubTaskId = subTasksIds.getFirst();
            result = subTasks.get(firstSubTaskId).getStatus();
            epics.get(epicId).setStatus(result);
            Status subTaskStatus;
            if (result == Status.NEW) {
                for (int subTaskId : subTasksIds) {
                    subTaskStatus = subTasks.get(subTaskId).getStatus();
                    if (result != subTaskStatus) {
                        result = Status.IN_PROGRESS;
                        epics.get(epicId).setStatus(result);
                        return result;
                    }
                }
            } else if (result == Status.DONE) {
                for (int subTaskId : subTasksIds) {
                    subTaskStatus = subTasks.get(subTaskId).getStatus();
                    if (result != subTaskStatus) {
                        result = Status.IN_PROGRESS;
                        epics.get(epicId).setStatus(result);
                        return result;
                    }
                }
            }
        }
        epics.get(epicId).setStatus(result);
        return result;
    }

    @Override
    public ArrayList<SubTask> getEpicSubTasks(int id) {
        if (epics.containsKey(id)) {
            ArrayList<Integer> childrenIds = epics.get(id).getSubTasksIds();
            ArrayList<SubTask> epicSubTasks = new ArrayList<>();
            for (int childrenId : childrenIds) {
                epicSubTasks.add(subTasks.get(childrenId));
            }
            return epicSubTasks;
        }
        return null;
    }

    //Методы для типа Подзадача
    @Override
    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void clearSubTasks() {
        for (Integer id : epics.keySet()) {
            ArrayList<Integer> subTasksIds = epics.get(id).getSubTasksIds();
            subTasksIds.clear();
            updateEpicStatus(id);
        }
        subTasks.clear();
    }

    @Override
    public SubTask getSubTask(int id) {
        historyManager.addTask(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void putSubTask(SubTask subTask) {
        //Если эпика с id, соответствующему epicId в объекте subTask не существует, то дальше не работаем
        int epicId = subTask.getEpicId();
        if (epics.containsKey(epicId)) {
            taskId++;
            subTask.setId(taskId);

            //В список subTasksIds передаём список нужного epic, добавляем в него taskId и возвращаем список обратно
            ArrayList<Integer> subTasksIds = epics.get(epicId).getSubTasksIds();
            subTasksIds.add(taskId);
            epics.get(epicId).setSubtasksIds(subTasksIds);

            subTasks.put(taskId, subTask);
            //Обновляем статус эпика
            updateEpicStatus(epicId);
        }
    }

    @Override
    public void updateSubTask(SubTask subTask, int id) {
        if (subTasks.containsKey(id)) {
            subTasks.get(id).setName(subTask.getName());
            subTasks.get(id).setDescription(subTask.getDescription());
            //Обновляем статус эпика
            updateEpicStatus(subTasks.get(id).getEpicId());
        }
    }

    @Override
    public void removeSubTask(int id) {
        if (subTasks.containsKey(id)) {
            int epicId = subTasks.get(id).getEpicId();
            //Получаем список детей, стираем нужного, возвращаем список
            ArrayList<Integer> childrenIds = epics.get(epicId).getSubTasksIds();
            childrenIds.remove(id);
            epics.get(epicId).setSubtasksIds(childrenIds);
            //Обновляем статус эпика
            updateEpicStatus(epicId);
            subTasks.remove(id);
        }
    }

    //Геттер для истории
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}