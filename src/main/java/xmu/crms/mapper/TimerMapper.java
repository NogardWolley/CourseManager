package xmu.crms.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Event;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Component

/**
 * @author zzj
 * */
public interface TimerMapper {

    Integer insertEvent(Event event);

    void deleteEvent(BigInteger eventId);

    void  updateEvent(Event event);

    List<Event> listExecutableEvents();

    Event getEventByEventId(BigInteger eventId);
}
