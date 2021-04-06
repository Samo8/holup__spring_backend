package sk.zvjs.holup.calendar_event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
    public List<CalendarEvent> findCalendarEventsByUserIdAndImported(UUID id, boolean imported);
}
