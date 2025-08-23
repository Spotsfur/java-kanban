package Tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTasksIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "id='" + super.getId() + "'" +
                ", name='" + super.getName() + "'" +
                ", description='" + super.getDescription() + "'" +
                ", status='" + super.getStatus() + "'" +
                ", childIds='" + subTasksIds + "'}";
    }

    public ArrayList<Integer> getSubTasksIds() {
        return subTasksIds;
    }

    public void setChildrenIds(ArrayList<Integer> subTasksIds) {
        this.subTasksIds = subTasksIds;
    }
}