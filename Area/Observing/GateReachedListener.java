package Area.Observing;

public class GateReachedListener implements IGateReachedListener {
    public boolean IsGateReached;

    public GateReachedListener() {
        IsGateReached = false;
    }

    @Override
    public void OnGateReached(GateReachedEvent event) {
        System.out.println("Juh " + event.getJuh().getName() + " has reached the gate!");

        IsGateReached = true;
    }
}
