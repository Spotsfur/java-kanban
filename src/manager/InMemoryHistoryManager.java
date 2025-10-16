package manager;

import tasks.Task;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class InMemoryHistoryManager implements HistoryManager {
    //private List<Task> history = new ArrayList<>();
    private Map<Integer, HistoryNode<tasks.Task>> nodesList = new HashMap<>();
    private HistoryNode<Task> head = null;
    private HistoryNode<Task> tail = null;

    //Методы управления нодами
    //Линкуем ноду с конца
    private void linkLast(Task element) {
        HistoryNode<Task> node = new HistoryNode<>(element);
        //Если такой элемент есть, удаляем его
        if (nodesList.containsKey(element.getId())) {
            removeNode(nodesList.get(element.getId()));
        }
        //Если хешмапа пуста, подвязываем голову и хвост
        if (nodesList.isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        nodesList.put(element.getId(), node);
    }

    //Собираем таски из хешмапы в список
    private List<Task> getTasks() {
        List<Task> history = new ArrayList<>();
        HistoryNode<Task> reader = head;
        if (reader != null) {
            for (int i = 0; i < nodesList.size(); i++) {
                history.add(reader.data);
                reader = reader.next;
            }
        }
        return history;
    }

    //Удаляем ноду
    private void removeNode(HistoryNode<Task> node) {
        if (node == null) {
            return;
        } else if (node.prev != null && node.next == null) { //Если это хвост, то предыдущий новый хвост
            tail = node.prev;
            node.prev.next = null;
        } else if (node.prev == null && node.next != null) { //Если это голова, то следующий новая голова
            head = node.next;
            node.next.prev = null;
        } else if (node.prev != null && node.next != null) { //Если это ни конец, ни начало, то связываем соседей
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else { //Если элемент единственный
            tail = null;
            head = null;
        }
        //Удаляем элемент из хешмапы
        nodesList.remove(node.data.getId());
    }

    //Методы для Истории
    @Override
    public List<Task> getHistory() {
        List<Task> newHistory;
        newHistory = getTasks();
        return newHistory;
    }

    @Override
    public void removeTask(int id) {
        removeNode(nodesList.get(id));
    }

    @Override
    public void addTask(Task task) {
        linkLast(task);
    }
}
