package xmu.crms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.dao.TimerDao;
import xmu.crms.entity.Event;
import xmu.crms.service.TimerService;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TimerServiceImpl implements TimerService {

   @Autowired
   private TimerDao timerDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void insertEvent(Date time, String beanName, String methodName, HashMap<BigInteger, String> paramMap) {
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void insertEvent(Date time, String beanName, String methodName, List<Object> paramList)
    {
        ObjectMapper m = new ObjectMapper();
        m.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");

        try {
            String param = m.writeValueAsString(paramList);
            Event event = new Event();
            event.setTime(time);
            event.setBeanName(beanName);
            event.setMethodName(methodName);
            event.setParameter(param);
            timerDao.insertEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void updateEvent(BigInteger eventId, Date newTime) {
        try {
            Event event = new Event();
            event.setId(eventId);
            event.setTime(newTime);
            timerDao.updateEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Scheduled(fixedRate = 1000 * 60 * 10)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void scheduled() {
        try {
        List<Event> events = timerDao.listExecutableEvents();
        if(events == null) {
            return;
        }
            for (Event event : events) {
                ObjectMapper om = new ObjectMapper();
                om.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");

                Object bean = applicationContext.getBean(event.getBeanName());
                Method callback = BeanUtils.findDeclaredMethodWithMinimalParameters(bean.getClass(), event.getMethodName());
                Object[] args = om.readValue(event.getParameter(), Object[].class);
                callback.invoke(bean, args);
                timerDao.deleteEvent(event.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    }
