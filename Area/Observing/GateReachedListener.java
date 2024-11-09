package Area.Observing;

public class GateReachedListener implements IGateReachedListener {
    public boolean IsGateReached;

    public GateReachedListener() {
        IsGateReached = false;
        isFirst = true;
    }

    @Override
    public void OnGateReached(GateReachedEvent event) {
        if (isFirst) {
            System.out.println("Juh " + event.getJuh().getName() + " has reached the gate!");
            isFirst = false;   
        }

        IsGateReached = true;
    }

    private boolean isFirst;
}
