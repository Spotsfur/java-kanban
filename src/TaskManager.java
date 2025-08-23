import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int taskId;
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private static HashMap<Integer, Epic> epics = new HashMap<>();

    TaskManager() {
        taskId = 0;
    }

    //Методы для типа Задача
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public boolean clearTasks() {
        tasks.clear();
        return true;
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

    public boolean clearEpic() {
        subTasks.clear();
        epics.clear();
        return true;
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

    public void removeEpic(int id) {
        if (epics.containsKey(id)) {
            //ЗДЕСЬ ДОЛЖЕН БЫТЬ ЦИКЛ ПЕРЕБОРА ПО САБТАСКАМ
            epics.remove(id);
        }
    }

    private Status updateEpicStatus(int epicId) {
        Status result = epics.get(epicId).getStatus();
        ArrayList<Integer> subTasksIds = epics.get(epicId).getSubTasksIds();
        if (!subTasksIds.isEmpty()) {
            int firstSubTaskId = subTasksIds.getFirst();
            result = subTasks.get(firstSubTaskId).getStatus();
            epics.get(epicId).setStatus(result);
            Status childStatus;
            if (result == Status.NEW) {
                for (int childId : subTasksIds) {
                    childStatus = subTasks.get(childId).getStatus();
                    if (result != childStatus) {
                        result = Status.IN_PROGRESS;
                        epics.get(epicId).setStatus(result);
                        return result;
                    }
                }
            } else if (result == Status.DONE) {
                for (int childId : subTasksIds) {
                    childStatus = subTasks.get(childId).getStatus();
                    if (result != childStatus) {
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

    public boolean clearSubTasks() {
        for (Integer id : epics.keySet()) {
            ArrayList<Integer> childrenIds = epics.get(id).getSubTasksIds();
            childrenIds.clear();
            epics.get(id).setChildrenIds(childrenIds);
        }
        subTasks.clear();
        return true;
    }

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    public void putSubTask(SubTask subTask) {
        //Если эпика с id, соответствующему parentId в объекте object не существует, то дальше не работаем
        int parentId = subTask.getParentId();
        if (epics.containsKey(parentId)) {
            taskId++;
            subTask.setId(taskId);

            //В список children передаём список нужного epic, добавляем в него taskId и возвращаем список обратно
            ArrayList<Integer> children = epics.get(parentId).getSubTasksIds();
            children.add(taskId);
            epics.get(parentId).setChildrenIds(children);

            subTasks.put(taskId, subTask);
            //Обновляем статус эпика
            updateEpicStatus(parentId);
        }
    }

    public void updateSubTask(SubTask subTask, int id) {
        if (subTasks.containsKey(id)) {
            subTasks.get(id).setName(subTask.getName());
            subTasks.get(id).setDescription(subTask.getDescription());
            //Обновляем статус эпика
            updateEpicStatus(subTasks.get(id).getParentId());
        }
    }

    public void removeSubTask(int id) {
        if (subTasks.containsKey(id)) {
            int parentId = subTasks.get(id).getParentId();
            //Получаем список детей, стираем нужного, возвращаем список
            ArrayList<Integer> childrenIds = epics.get(parentId).getSubTasksIds();
            childrenIds.remove(id);
            epics.get(parentId).setChildrenIds(childrenIds);
            //Обновляем статус эпика
            updateEpicStatus(parentId);
            subTasks.remove(id);
        }
    }


}