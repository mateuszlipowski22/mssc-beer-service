package guru.springframework.msscbeerservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateMapper {
    public LocalDateTime asOffsetDateTime(Timestamp timestamp){
        if (timestamp != null){
//            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
//                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
//                    ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
            return timestamp.toLocalDateTime();
        } else {
            return null;
        }
    }

    public Timestamp asTimestamp(LocalDateTime localDateTime){
        if(localDateTime != null) {
//            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
            return Timestamp.valueOf(localDateTime);
        } else {
            return null;
        }
    }
}