package tasks;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Integer> subTasksIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Epic epic = (Epic) obj;
        return Objects.equals(getId(), epic.getId());
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "id='" + super.getId() + "'" +
                ", name='" + super.getName() + "'" +
                ", description='" + super.getDescription() + "'" +
                ", status='" + super.getStatus() + "'" +
                ", subTasksIds='" + subTasksIds + "'}";
    }

    public ArrayList<Integer> getSubTasksIds() {
        return subTasksIds;
    }

    public void setSubtasksIds(ArrayList<Integer> subTasksIds) {
        if (!subTasksIds.contains(this.getId())) {
            this.subTasksIds = subTasksIds;
        }
    }
}