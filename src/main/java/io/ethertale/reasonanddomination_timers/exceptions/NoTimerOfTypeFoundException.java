package io.ethertale.reasonanddomination_timers.exceptions;

public class NoTimerOfTypeFoundException extends RuntimeException {
    public NoTimerOfTypeFoundException() {
    }

    public NoTimerOfTypeFoundException(String message) {
        super(message);
    }
}
