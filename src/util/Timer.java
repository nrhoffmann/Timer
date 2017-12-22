package util;

import static util.Timer.TimerState.*;

public class Timer {

    enum TimerState {STARTED, STOPPED}

    private long startTime, endTime;
    private TimerState currentState = TimerState.STOPPED;

    public void start() {
        ensureTimerIsStopped();
        setStartTimeToNow();
        setCurrentState(STARTED);
    }

    public void stop() {
        if (! isStopped())
            stopTimer();
    }

    public long getElapsedNanoseconds() {
        if (! isStopped())
            return nanosecondsSoFar();
        else if (isStopped())
            return nanosecondsUntilStopped();
        else
            throw new IllegalStateException("The timer has entered an unknown state");
    }


    private void stopTimer() {
        setEndTimeToNow();
        setCurrentState(STOPPED);
    }

    private long nanosecondsSoFar() {
        return System.nanoTime() - startTime;
    }

    private long nanosecondsUntilStopped() {
        return endTime - startTime;
    }

    private boolean isStopped() {
        return currentState == STOPPED;
    }

    private void setCurrentState(TimerState newState){
        currentState = newState;
    }

    private void setStartTimeToNow() {
        startTime = System.nanoTime();
    }

    private void setEndTimeToNow() {
        endTime = System.nanoTime();
    }

    private void ensureTimerIsStopped() {
        if (! isStopped())
            throw new IllegalStateException("The timer has already been started");
    }

}
