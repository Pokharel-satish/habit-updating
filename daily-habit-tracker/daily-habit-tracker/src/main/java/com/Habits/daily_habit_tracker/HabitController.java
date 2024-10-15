package com.Habits.daily_habit_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitRepository habitRepository;

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitRepository.save(habit);
    }

    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit updatedHabit) {
        return habitRepository.findById(id).map(habit -> {
            habit.setProgress(updatedHabit.getProgress());
            habit.setTarget(updatedHabit.getTarget());
            return habitRepository.save(habit);
        }).orElseGet(() -> {
            updatedHabit.setId(id);
            return habitRepository.save(updatedHabit);
        });
    }
}

