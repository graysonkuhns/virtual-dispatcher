package virtualdispatcher.api;

public class DefaultZone implements Zone {

    //property
    private final int id;

    //constructor
    public DefaultZone (final int id){
        this.id = id;
    }

    //method
    @Override
    public int getId() { return id;}
}
