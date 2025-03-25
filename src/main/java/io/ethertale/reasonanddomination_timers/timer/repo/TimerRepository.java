package io.ethertale.reasonanddomination_timers.timer.repo;

import io.ethertale.reasonanddomination_timers.timer.model.Timer;
import io.ethertale.reasonanddomination_timers.timer.model.TimerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TimerRepository extends JpaRepository<Timer, UUID> {
    Optional<Timer> findByType(TimerType type);
}
