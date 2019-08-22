public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        addNewTask(this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
