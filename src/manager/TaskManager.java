package manager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int taskId;
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private static HashMap<Integer, Epic> epics = new HashMap<>();

    public TaskManager() {
        taskId = 0;
    }

    //Методы для типа Задача
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public void putTask(Task task) {
        taskId++;
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    public void updateTask(Task task, int id) {
        if (tasks.containsKey(id)) {
            tasks.get(id).setName(task.getName());
            tasks.get(id).setDescription(task.getDescription());
        }
    }

    public void removeTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

    //Методы для типа Эпик
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public void clearEpic() {
        subTasks.clear();
        epics.clear();
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void putEpic(Epic epic) {
        taskId++;
        epic.setId(taskId);
        epics.put(taskId, epic);
    }

    public void updateEpic(Epic epic, int id) {
        if (epics.containsKey(id)) {
            epics.get(id).setName(epic.getName());
            epics.get(id).setDescription(epic.getDescription());
        }
    }

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
    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public void clearSubTasks() {
        for (Integer id : epics.keySet()) {
            ArrayList<Integer> subTasksIdsIds = epics.get(id).getSubTasksIds();
            subTasksIdsIds.clear();
            epics.get(id).setStatus(Status.NEW);
        }
        subTasks.clear();
    }

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

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

    public void updateSubTask(SubTask subTask, int id) {
        if (subTasks.containsKey(id)) {
            subTasks.get(id).setName(subTask.getName());
            subTasks.get(id).setDescription(subTask.getDescription());
            //Обновляем статус эпика
            updateEpicStatus(subTasks.get(id).getEpicId());
        }
    }

    public void removeSubTask(int id) {
        if (subTasks.containsKey(id)) {
            int epictId = subTasks.get(id).getEpicId();
            //Получаем список детей, стираем нужного, возвращаем список
            ArrayList<Integer> childrenIds = epics.get(epictId).getSubTasksIds();
            childrenIds.remove(id);
            epics.get(epictId).setSubtasksIds(childrenIds);
            //Обновляем статус эпика
            updateEpicStatus(epictId);
            subTasks.remove(id);
        }
    }


}