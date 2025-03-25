package io.ethertale.reasonanddomination_timers.web.controller;

import io.ethertale.reasonanddomination_timers.timer.model.Timer;
import io.ethertale.reasonanddomination_timers.timer.model.TimerType;
import io.ethertale.reasonanddomination_timers.timer.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timers")
public class TimerController {

    private final TimerService timerService;

    @Autowired
    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<Timer> getTimer(@PathVariable TimerType type) {
        return ResponseEntity.ok(timerService.getTimer(type));
    }
}
