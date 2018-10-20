package virtualdispatcher.api;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class DefaultPilot implements Pilot {

    // properties
    private final int id;
    private final String firstName;
    private final String lastName;

    // constructor
    @Inject
    public DefaultPilot(
        @Assisted("id") final int id,
        @Assisted("firstName") final String firstName,
        @Assisted("lastName") final String lastName) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // methods
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }
    

    @Override
    public String getLastName() {
        return lastName;
    }
}
