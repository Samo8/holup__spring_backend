package sk.zvjs.holup.calendar_event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalendarEventDTO {
    private String title;
    private String description;
    private String start;
    private String end;
}
