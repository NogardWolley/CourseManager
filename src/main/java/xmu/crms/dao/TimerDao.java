package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xmu.crms.entity.Event;
import xmu.crms.mapper.TimerMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyx
 * */
@Component
public class TimerDao {

    @Autowired
    TimerMapper timerMapper;
    public void insertEvent(Event event)
    {
        try {
            timerMapper.insertEvent(event);
        }
        catch (Exception e){}
    }

    public void deleteEvent(BigInteger eventId)
    {
        try {
            timerMapper.deleteEvent(eventId);
        }
        catch (Exception e){}
    }

    public void  updateEvent(Event event)
    {
        try {
            timerMapper.updateEvent(event);
        }
        catch (Exception e){}
    }

    public Event  getEventByEventId(BigInteger eventId)
    {
        try {
            return timerMapper.getEventByEventId(eventId);
        }
        catch (Exception e){}
        return null;
    }

    public List<Event> listExecutableEvents()
    {
        List<Event> list =new ArrayList<Event>(16);
        try
        {
            list=timerMapper.listExecutableEvents();
        }
        catch (Exception e){}
        return list;
    }
}
