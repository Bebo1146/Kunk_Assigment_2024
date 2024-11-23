package Area.Observing;

public class GateReachedListener implements IGateReachedListener {
    
    public String getWinnerMessage(){
        return winnerMessage;
    }

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
            winnerMessage = "Juh " + event.getJuh().getName() + " kiment egy kapun!";
            isFirst = false;   
        }

        isGateReached = true;
    }

    private String winnerMessage;
    private boolean isGateReached;
    private boolean isFirst;
}
