package sk.zvjs.holup.calendar_event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalendarEventResponseDTO {
    private String title;
    private String description;
    private UUID userId;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime endTime;
    private boolean imported;

    public CalendarEventResponseDTO(CalendarEvent calendarEvent) {
        this(
                calendarEvent.getTitle(),
                calendarEvent.getDescription(),
                calendarEvent.getUser().getId(),
                calendarEvent.getStartTime(),
                calendarEvent.getEndTime(),
                calendarEvent.isImported()
        );
    }

}
