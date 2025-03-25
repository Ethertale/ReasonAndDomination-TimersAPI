package io.ethertale.reasonanddomination_timers.timer;

import io.ethertale.reasonanddomination_timers.timer.model.Timer;
import io.ethertale.reasonanddomination_timers.timer.model.TimerType;
import io.ethertale.reasonanddomination_timers.timer.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimerScheduler {

    private final TimerService timerService;

    @Autowired
    public TimerScheduler(TimerService timerService) {
        this.timerService = timerService;
    }

    @Scheduled(fixedRate = 60000)
    public void updateTimers(){
        for (TimerType type : TimerType.values()) {
            Timer timer = timerService.getTimer(type);
            if (timer.getEndTime().isBefore(LocalDateTime.now())) {
                timerService.resetTimer(type, getDuration(type));
            }
        }
    }

    private long getDuration(TimerType type) {
        return switch (type) {
            case RAID_20 -> 72;
            case RAID_40 -> 168;
            case WORLD_BOSS -> 120;
        };
    }
}
