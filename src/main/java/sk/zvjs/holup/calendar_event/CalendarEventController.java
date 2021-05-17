package sk.zvjs.holup.calendar_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.zvjs.holup.user.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CalendarEventController {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final UserService userService;
    private final CalendarEventService calendarEventService;

    @Autowired
    public CalendarEventController(UserService userService, CalendarEventService calendarEventService) {
        this.userService = userService;
        this.calendarEventService = calendarEventService;
    }

    @GetMapping("/api/v1/calendar_events/{id}")
    public List<CalendarEventResponseDTO> fetchCalendarEventsByUserId(@PathVariable UUID id) {
        var user = userService.fetchUserById(id);
        if (user.isPresent()) {
            List<CalendarEventResponseDTO> calendarEventResponseDTOS = new ArrayList<>();
            for (var calendarEvent : calendarEventService.fetchCalendarEventsByUser(user.get())) {
                calendarEventResponseDTOS.add(new CalendarEventResponseDTO(calendarEvent));
            }
            return calendarEventResponseDTOS;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " id not found");
    }

    @PostMapping("/api/v1/calendar_event/{id}")
    public CalendarEventResponseDTO addCalendarEvent(@PathVariable UUID id, @RequestBody CalendarEventDTO calendarEventDTO) {
        var user = userService.fetchUserById(id);
        if (user.isPresent()) {
            var start = LocalDateTime.parse(calendarEventDTO.getStart(), FORMATTER);
            var end = LocalDateTime.parse(calendarEventDTO.getEnd(), FORMATTER);

            var calendarEvent = new CalendarEvent(calendarEventDTO, start, end);
            calendarEvent.setUser(user.get());
            var event = calendarEventService.addCalendarEvent(calendarEvent);
            return new CalendarEventResponseDTO(event);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " id not found");
    }

    @PutMapping("/api/v1/calendar_event/{id}")
    public CalendarEventResponseDTO updateCalendarEvent(@PathVariable Long id) {
        var calendarEvent = calendarEventService.updateCalendarEventImport(id);
        if (calendarEvent != null) {
            return new CalendarEventResponseDTO(calendarEvent);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CalendarEvent with " + id + " id not found");
    }

    @DeleteMapping("/api/v1/calendar_event/{id}")
    public ResponseEntity<String> deleteCalendarEvent(@PathVariable Long id) {
        calendarEventService.deleteCalendarEvent(id);
        return new ResponseEntity<>(
                "Removed",
                HttpStatus.OK
        );
    }
}

