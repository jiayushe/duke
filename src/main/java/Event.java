import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = DukeDateTimeParser.parse(at);
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + DukeDateTimeFormatter.standardFormat(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DukeDateTimeFormatter.prettyFormat(at) + ")";
    }
}
