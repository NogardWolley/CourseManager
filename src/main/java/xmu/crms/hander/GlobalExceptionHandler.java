package xmu.crms.hander;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.Seminar;
import xmu.crms.exception.*;

@ControllerAdvice
@RestController
/**
 * 
 * @author Administrator
 *
 */

public class GlobalExceptionHandler {

    @ExceptionHandler(value = ClassesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse classesNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("班级没有找到！");
        return response;
    }

    @ExceptionHandler(value = CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse courseNotFoundException()
    {
        ExceptionResponse response=new  ExceptionResponse("课程没有找到！");
        return response;
    }

    @ExceptionHandler(value = FixGroupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse fixGroupNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("固定分组没有找到！");
        return response;
    }

    @ExceptionHandler(value = GroupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse groupNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("讨论课分组没有找到！");
        return response;
    }
    @ExceptionHandler(value = SeminarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse seminarNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("讨论课没有找到！");
        return response;
    }
    @ExceptionHandler(value = TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse topicNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("话题没有找到！");
        return response;
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse userNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("用户没有找到！");
        return response;
    }

    @ExceptionHandler(value = InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse invalidOperationExceptionHandler(InvalidOperationException es)
    {
        String mes=es.getMessage();
        ExceptionResponse response=new  ExceptionResponse("非法的操作！",mes);
        return response;
    }

}

