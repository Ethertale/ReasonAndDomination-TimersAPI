package io.ethertale.reasonanddomination_timers.timer.service;

import io.ethertale.reasonanddomination_timers.exceptions.NoTimerOfTypeFoundException;
import io.ethertale.reasonanddomination_timers.timer.model.Timer;
import io.ethertale.reasonanddomination_timers.timer.model.TimerType;
import io.ethertale.reasonanddomination_timers.timer.repo.TimerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimerServiceUTest {

    @Mock
    private TimerRepository timerRepository;
    @InjectMocks
    private TimerService timerService;

    @Test
    void givenTimerExists_whenGetTimer_thenReturnTimer() {
        TimerType timerType = TimerType.RAID_20;

        Timer expectedTimer = new Timer();
        expectedTimer.setType(timerType);
        expectedTimer.setEndTime(LocalDateTime.now().plusHours(72));

        when(timerRepository.findByType(timerType)).thenReturn(Optional.of(expectedTimer));

        Timer result = timerService.getTimer(timerType);

        assertThat(result).isEqualTo(expectedTimer);
    }

    @Test
    void givenTimerDoesNotExist_whenGetTimer_thenThrowException() {
        TimerType timerType = TimerType.RAID_20;

        when(timerRepository.findByType(timerType)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> timerService.getTimer(timerType))
                .isInstanceOf(NoTimerOfTypeFoundException.class);
    }

    @Test
    void whenInitTimers_thenCallResetTimer() {
        timerService.initTimers();

        verify(timerRepository, times(3)).save(any(Timer.class));
    }
}