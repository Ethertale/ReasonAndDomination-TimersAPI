package io.ethertale.reasonanddomination_timers.timer;

import io.ethertale.reasonanddomination_timers.timer.model.Timer;
import io.ethertale.reasonanddomination_timers.timer.model.TimerType;
import io.ethertale.reasonanddomination_timers.timer.service.TimerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimerSchedulerUTest {

    @Mock
    private TimerService timerService;

    @InjectMocks
    private TimerScheduler timerScheduler;

    @Test
    void givenThreeRaids_Raid20Expired_whenSchedulerRuns_thenTimersAreUpdatedIfNeeded() {

        TimerType raid20Type = TimerType.RAID_20;
        Timer raid20Timer = new Timer();
        raid20Timer.setType(raid20Type);
        raid20Timer.setEndTime(LocalDateTime.now().minusMinutes(1));

        TimerType raid40Type = TimerType.RAID_40;
        Timer raid40Timer = new Timer();
        raid40Timer.setType(raid40Type);
        raid40Timer.setEndTime(LocalDateTime.now().plusMinutes(1));

        TimerType raidWorldBossType = TimerType.WORLD_BOSS;
        Timer raidWorldBossTimer = new Timer();
        raidWorldBossTimer.setType(raidWorldBossType);
        raidWorldBossTimer.setEndTime(LocalDateTime.now().plusMinutes(1));

        when(timerService.getTimer(raid20Type)).thenReturn(raid20Timer);
        when(timerService.getTimer(raid40Type)).thenReturn(raid40Timer);
        when(timerService.getTimer(raidWorldBossType)).thenReturn(raidWorldBossTimer);

        timerScheduler.updateTimers();

        verify(timerService, times(1)).resetTimer(raid20Type, 72);
        verify(timerService, never()).resetTimer(raid40Type, 168);
        verify(timerService, never()).resetTimer(raidWorldBossType, 120);
    }
}
