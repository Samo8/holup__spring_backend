package sk.zvjs.holup.calendar_event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import sk.zvjs.holup.user.User;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(nullable = false, length = 32)
    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", timezone = "UTC")
    private LocalDateTime endTime;
    @Column(columnDefinition = "boolean default false")
    private boolean imported;

    public CalendarEvent(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CalendarEvent(CalendarEventDTO calendarEventDTO, LocalDateTime start, LocalDateTime end) {
        this(
                calendarEventDTO.getTitle(),
                calendarEventDTO.getDescription(),
                start,
                end
        );
    }
}
