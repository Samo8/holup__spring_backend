package sk.zvjs.holup.calendar_event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import sk.zvjs.holup.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CalendarEvent {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;
    private String title;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime endTime;

    public CalendarEvent(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CalendarEvent(CalendarEventDTO calendarEventDTO) {
        this(
                calendarEventDTO.getTitle(),
                calendarEventDTO.getDescription(),
                calendarEventDTO.getStart(),
                calendarEventDTO.getEnd()
        );
    }
}
