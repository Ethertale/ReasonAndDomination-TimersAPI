package io.ethertale.reasonanddomination_timers.timer.service;

import io.ethertale.reasonanddomination_timers.exceptions.NoTimerOfTypeFoundException;
import io.ethertale.reasonanddomination_timers.timer.model.Timer;
import io.ethertale.reasonanddomination_timers.timer.model.TimerType;
import io.ethertale.reasonanddomination_timers.timer.repo.TimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimerService {

    private final TimerRepository timerRepository;

    @Autowired
    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public Timer getTimer(TimerType type) {
        if  (timerRepository.count() == 0) {
            initTimers();
        }

        return timerRepository.findByType(type).orElseThrow(() -> new NoTimerOfTypeFoundException(type.name()));
    }

    public void resetTimer(TimerType type, long hours) {
        Timer timer = timerRepository.findByType(type).orElse(new Timer());
        timer.setType(type);
        timer.setEndTime(LocalDateTime.now().plusHours(hours));
        timerRepository.save(timer);
    }

    public void initTimers(){
        resetTimer(TimerType.RAID_20, 72);
        resetTimer(TimerType.RAID_40, 168);
        resetTimer(TimerType.WORLD_BOSS, 120);
    }
}
