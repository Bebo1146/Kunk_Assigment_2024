package Area.Observing;

public class GateReachedListener implements IGateReachedListener {
    
    public boolean getIsGateReached() {
        return isGateReached;
    }

    public GateReachedListener() {
        isGateReached = false;
        isFirst = true;
    }

    @Override
    public void onGateReached(GateReachedEvent event) {
        if (isFirst) {
            System.out.println("Juh " + event.getJuh().getName() + " has reached the gate!");
            isFirst = false;   
        }

        isGateReached = true;
    }

    private boolean isGateReached;
    private boolean isFirst;
}
