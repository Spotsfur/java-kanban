package tasks;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description, int parentId) {
        super(name, description);
        this.epicId = parentId;
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