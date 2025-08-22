public class SubTask extends Task {
    private int parentId;

    SubTask(String name, String description, int parentId) {
        super(name, description);
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id='" + super.getId() + "'" +
                ", name='" + super.getName() + "'" +
                ", description='" + super.getDescription() + "'" +
                ", status='" + super.getStatus() + "'" +
                ", parentId='" + parentId + "'}";
    }

    public int getParentId() {
        return parentId;
    }

    public Status getStatus() {
        return super.getStatus();
    }
}