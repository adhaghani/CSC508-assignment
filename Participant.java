public class Participant {
    private String name;
    private String id;
    private String event;

    public Participant(String name, String id, String event) {
        this.name = name;
        this.id = id;
        this.event = event;
    }
    public String getName() { return name; }
    public String getId()   { return id; }
    public String getEvent(){ return event; }
    public String toString() {
        return name + " (" + id + ") - " + event;
    }
}