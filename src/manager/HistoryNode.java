package manager;

import tasks.Task;

public class HistoryNode<T extends Task> {
    public HistoryNode<Task> prev;
    public Task data;
    public HistoryNode<Task> next;

    public HistoryNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
