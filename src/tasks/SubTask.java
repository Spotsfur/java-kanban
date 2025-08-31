package tasks;

import java.util.Objects;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description, int parentId) {
        super(name, description);
        this.epicId = parentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        SubTask subTask = (SubTask) obj;
        return Objects.equals(getId(), subTask.getId());
    }

    @Override
    public String toString() {
        return "Tasks.SubTask{" +
                "id='" + super.getId() + "'" +
                ", name='" + super.getName() + "'" +
                ", description='" + super.getDescription() + "'" +
                ", status='" + super.getStatus() + "'" +
                ", epicId='" + epicId + "'}";
    }

    public int getEpicId() {
        return epicId;
    }

    public Status getStatus() {
        return super.getStatus();
    }
}