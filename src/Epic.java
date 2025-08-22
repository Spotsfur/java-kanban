import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> childrenIds = new ArrayList<>();

    Epic(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id='" + super.getId() + "'" +
                ", name='" + super.getName() + "'" +
                ", description='" + super.getDescription() + "'" +
                ", status='" + super.getStatus() + "'" +
                ", childIds='" + childrenIds + "'}";
    }

    public ArrayList<Integer> getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(ArrayList<Integer> childrenIds) {
        this.childrenIds = childrenIds;
    }
}