package sk.zvjs.holup.calendar_event;

import org.springframework.stereotype.Service;
import sk.zvjs.holup.user.User;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;

    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public List<CalendarEvent> fetchCalendarEventsByUser(User user) {
        return calendarEventRepository.findCalendarEventsByUserIdAndImported(user.getId(), false);
    }

    public CalendarEvent addCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public CalendarEvent updateCalendarEventImport(Long id) {
        var calendarEvent = calendarEventRepository.findById(id);
        if (calendarEvent.isPresent()) {
            calendarEvent.get().setImported(true);
            calendarEventRepository.save(calendarEvent.get());
            return calendarEvent.get();
        }
        return null;
    }

    public void deleteCalendarEvent(Long id) {
        var calendarEvent = calendarEventRepository.findById(id);
        calendarEvent.ifPresent(calendarEventRepository::delete);
    }
}
