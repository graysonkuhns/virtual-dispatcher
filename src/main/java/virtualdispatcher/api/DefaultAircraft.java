package virtualdispatcher.api;

public class DefaultAircraft implements Aircraft {

    // properties
    private final int aircraftId;
    private boolean operational;

    //constructor
    public DefaultAircraft(final int aircraftId, boolean operational){
        this.aircraftId = aircraftId;
        this.operational = operational;
    }

    //methods
    @Override
    public int getId() { return aircraftId; }

    @Override
    public boolean isOperational() { return operational;}

    public void setOperational(boolean operational){
        this.operational = operational;
    }
}
