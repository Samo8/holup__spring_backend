package sk.zvjs.holup.calendar_event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalendarEventDTO {
    private String title;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime end;
}
