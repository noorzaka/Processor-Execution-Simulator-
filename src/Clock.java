public class Clock {
    private  int currentCycle;

    public Clock() {
        this.currentCycle = 0;
    }

    public void nextCycle() {
        currentCycle++;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }
}
