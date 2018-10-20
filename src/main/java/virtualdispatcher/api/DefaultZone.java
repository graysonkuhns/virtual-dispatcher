package virtualdispatcher.api;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class DefaultZone implements Zone {

    //property
    private final int id;

    //constructor
    @Inject
    public DefaultZone(@Assisted("id") final int id){
        this.id = id;
    }

    //method
    @Override
    public int getId() {
        return id;
    }
}
