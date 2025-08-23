package Tasks;

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
                ", parentId='" + epicId + "'}";
    }

    public int getParentId() {
        return epicId;
    }

    public Status getStatus() {
        return super.getStatus();
    }
}