package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>(10);

    //Методы для Истории
    @Override
    public List<Task> getHistory() {
        List<Task> newHistory;
        newHistory = history;
        return newHistory;
    }

    @Override
    public void addTask(Task task) {
        if (history.size() >= 10 && task != null) {
            history.removeFirst();
        }
        history.add(task);
    }
}
