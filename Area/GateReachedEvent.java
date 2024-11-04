package Area;

import FieldEntities.Juh;

public class GateReachedEvent {
    private final Juh juh;

    public GateReachedEvent(Juh juh) {
        this.juh = juh;
    }

    public Juh getJuh() {
        return juh;
    }
}
