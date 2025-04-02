package io.ethertale.reasonanddomination_timers.web.controller;

import io.ethertale.reasonanddomination_timers.exceptions.NoTimerOfTypeFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NoTimerOfTypeFoundException.class)
    public String handleNoTimerOfTypeFoundException(RedirectAttributes redirectAttributes, String type){
        redirectAttributes.addFlashAttribute("NoTimerOfTypeFound", String.format("No timer found for type %s", type));
        return "redirect:/timers";
    }
}
