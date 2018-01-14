package co.za.geartronix.providers;

public enum MessagesCategoryProvider {
    inbox("Inbox", 0),
    outbox("Outbox", 1);

    private String name;
    private int id;

    MessagesCategoryProvider(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
}
