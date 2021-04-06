package sk.zvjs.holup.calendar_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.zvjs.holup.user.User;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public Optional<CalendarEvent> fetchCalendarEventById(Long id) {
        return calendarEventRepository.findById(id);
    }

    public List<CalendarEvent> fetchCalendarEventsByUser(User user) {
        return calendarEventRepository.findCalendarEventsByUserIdAndImported(user.getId(), false);
    }

    public CalendarEvent addCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }
}
