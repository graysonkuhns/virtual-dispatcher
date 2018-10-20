package virtualdispatcher.api;

public class DefaultPilot implements Pilot {

    // properties
    private final int id;
    private final String firstName;
    private final String lastName;

    // constructor
    public DefaultPilot(final int id, final String firstName, final String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // methods
    @Override
    public int getId() { return id;}

    @Override
    public String getFirstName() { return firstName;}
    

    @Override
    public String getLastName() {return lastName; }
}
