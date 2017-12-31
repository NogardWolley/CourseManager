package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.Course;
import xmu.crms.entity.FixGroup;
import xmu.crms.vo.*;
import xmu.crms.dto.*;
import xmu.crms.dto.ClassVO;
import xmu.crms.mapper.*;

import java.math.BigInteger;
import java.util.ArrayList;

@RestController
public class Controller {
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private FixGroupMapper fixGroupMapper;

	@GetMapping("/me")
	public ResponseEntity<FixGroup> getInfo(){
		System.out.println("****");
		BigInteger a,b;
		a=BigInteger.valueOf(3);b=BigInteger.valueOf(1);
<<<<<<< HEAD
			return new ResponseEntity<FixGroup>(fixGroupMapper.getFixedGroupById(a,b),HttpStatus.OK);
=======
			return new ResponseEntity<Course>(courseMapper.getCourseByCourseId(b),HttpStatus.OK);
>>>>>>> 7ddd08e71853b136f5f9d389181569dde30470a3
    }
//	@PutMapping("/me")
//	public ResponseEntity updateUser(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/signin")//未完成
//	public ResponseEntity getSign(){
//		return new ResponseEntity(HttpStatus.OK);
//	}
//	@PostMapping("/signin")
//	public ResponseEntity postSign(){
//		return new ResponseEntity(HttpStatus.OK);
//	}
//	@PostMapping("/register")
//	public ResponseEntity postRegister(){
//		return new ResponseEntity(HttpStatus.OK);
//	}
//
//	@GetMapping("/course")
//	public ResponseEntity<ArrayList<CourseVO>>getCourse(){
//		ArrayList<CourseVO>list=new ArrayList<CourseVO>();
//		list.add(new CourseVO(1,"J2EE",3,60,"2017-9-1","2018-1-1"));
//		list.add(new CourseVO(2,"OOAD",3,60,"2017-9-1","2018-1-1"));
//		list.add(new CourseVO(3,"JAVA",3,60,"2017-9-1","2018-1-1"));
//		list.add(new CourseVO(4,"OS",3,60,"2017-9-1","2018-1-1"));
//		return new ResponseEntity<ArrayList<CourseVO>>(list,HttpStatus.OK);
//	}
//	@PostMapping("/course")
//	public ResponseEntity<CourseVO> postCourse(){
//		return new ResponseEntity<CourseVO>(new CourseVO(23,"dssd"),HttpStatus.CREATED);
//	}
//	@GetMapping("/course/{courseId}")
//	public ResponseEntity<CourseDetailVO>getCourseOfId(){
//		return new ResponseEntity<CourseDetailVO>(new CourseDetailVO(23,"OOAD","面向对象分析与设计","邱明","mingqiu@xmu.edu.cn"),HttpStatus.OK);
//	}
//	@PostMapping("/course/{courseId}")
//	public ResponseEntity postCourseOfId(){
//		return new ResponseEntity(HttpStatus.CREATED);
//	}
//	@DeleteMapping("/course/{courseId}")
//	public ResponseEntity deleteCourseOfId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/course/{courseId}")
//	public ResponseEntity deleteCourseOfIddd(){
//
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/course/{courseId}/class")
//	public ResponseEntity<ArrayList<CourseClassVO>>getClassOfCourse(){
//		ArrayList<CourseClassVO>list=new ArrayList<CourseClassVO>();
//		list.add(new CourseClassVO(1,"周三12节"));
//		list.add(new CourseClassVO(2,"周三34节"));
//		return new ResponseEntity<ArrayList<CourseClassVO>>(list,HttpStatus.OK);
//	}
//	@PostMapping("/course/{courseId}/class")
//	public ResponseEntity<CourseClassVO> putClassOfCourse(){
//
//		return new ResponseEntity<CourseClassVO>(new CourseClassVO(1,"周三12节"),HttpStatus.CREATED);
//	}
//	@GetMapping("/course/{courseId}/seminar")
//	public ResponseEntity<ArrayList<SeminarVO>> getseminatlist(){
//		ArrayList<SeminarVO>list=new ArrayList<SeminarVO>();
//		list.add(new SeminarVO(29,"界面原型设计","界面原型设计","fixed","2017-09-25","2017-10-25",4));
//		list.add(new SeminarVO(32,"概要设计","模型层与数据库设计","fixed","2017-10-25","2017-11-25",4));
//		return new ResponseEntity<ArrayList<SeminarVO>>(list, HttpStatus.OK);
//	}
//	@PostMapping("/course/{courseId}/seminar")
//	public ResponseEntity<SeminarVO> postseminatlist(){
//		return new ResponseEntity<SeminarVO>(new SeminarVO(12,"sdsd"),HttpStatus.CREATED);
//	}
//	@GetMapping("/course/{courseId}/seminar/current")
//	public ResponseEntity<SeminarVO> getCurrentSemina(){
//		ArrayList<ClassVO>list=new ArrayList<ClassVO>();
//		list.add(new ClassVO(1,"周三12节"));
//		list.add(new ClassVO(2,"周三34节"));
//		return new ResponseEntity<SeminarVO>(new SeminarVO(29,"界面原型设计","OOAD","fixed","2017-09-25","2017-10-09",list), HttpStatus.OK);
//	}
//	@GetMapping("/course/{courseId}/grade")
//	public ResponseEntity<ArrayList<SeminarGradeDetailVO>> getCourseGradeSeminarGradeDetail(){
//		ArrayList<SeminarGradeDetailVO>list=new ArrayList<SeminarGradeDetailVO>();
//		list.add(new SeminarGradeDetailVO("XXXX","XX","XX",3,4,4));
//		list.add(new SeminarGradeDetailVO("XXXT","TX","TX",3,4,4));
//		return new ResponseEntity<ArrayList<SeminarGradeDetailVO>>(list, HttpStatus.OK);
//	}
//
//	@GetMapping("/class")
//	public ResponseEntity<ArrayList<ClassVO>> getClasses(){
//		ArrayList<ClassVO>list=new ArrayList<ClassVO>();
//		list.add(new ClassVO(23,"XXXX",60,"XX","XX","XX","XX"));
//		list.add(new ClassVO(42,"XXXX",60,"XX","XX","XX","XX"));
//		return new ResponseEntity<ArrayList<ClassVO>>(list, HttpStatus.OK);
//	}
//	@GetMapping("/class/{classId}")
//	public ResponseEntity<ClassVO> getClassOfId(){
//		return new ResponseEntity<ClassVO>(new ClassVO(23,"XX",123,"XX","XX",-1,"/XX/XX.XX",new ProportionVO(50,50,20,60,20)),HttpStatus.OK);
//	}
//	@PutMapping("/class/{classId}")
//	public ResponseEntity putClassOfId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@DeleteMapping("/class/{classId}")
//	public ResponseEntity deleteClassOfId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/class/{classId}/student")
//	public ResponseEntity<ArrayList<StudentVO>> getStudentFormClassId(){
//		ArrayList<StudentVO>list=new ArrayList<StudentVO>();
//		list.add(new StudentVO(
//                3486,
//                "StudentVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		list.add(new StudentVO(
//                3487,
//                "StudentVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		return new ResponseEntity<ArrayList<StudentVO>>(list, HttpStatus.OK);
//	}
//	@PutMapping("/class/{classId}/student")
//	public ResponseEntity putStudentFormClassId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PostMapping("/class/{classId}/student")
//	public ResponseEntity putStudentFormdClassId(){
//		return new ResponseEntity(HttpStatus.CREATED);
//	}
//	@DeleteMapping("/class/{classId}/student/{studentId}")
//	public ResponseEntity DeleteStudentFormClassId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/class/{classId}/classGroup")
//	public ResponseEntity<ClassGroupVO> getClassGroup(){
//		ArrayList<StudentVO>list=new ArrayList<StudentVO>();
//		list.add(new StudentVO(
//                3486,
//                "StudentVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		list.add(new StudentVO(
//                3487,
//                "StudentVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		ClassGroupVO g=new ClassGroupVO(new StudentVO(
//                34878,
//                "StudentVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ),list);
//		return new ResponseEntity<ClassGroupVO>(g,HttpStatus.OK);
//	}
//	@PutMapping("/class/{classId}/classgroup/resign")
//	public ResponseEntity leaderResign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/class/{classId}/classgroup/assign")
//	public ResponseEntity<AttendanceListVO> memerAssign(){
//		return new ResponseEntity<AttendanceListVO>(new AttendanceListVO(77,"ss") ,HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/class/{classId}/classgroup/add")
//	public ResponseEntity AddMemer(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/class/{classId}/classgroup/remove")
//	public ResponseEntity RemoveMemer(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/seminar/{seminarId}")
//	public ResponseEntity<SeminarVO> getSeminarOfId(){
//		Integer i=29;
//		ArrayList<ClassVO>list=new ArrayList<ClassVO>();
//		list.add(new ClassVO(1,"周三12节"));
//		list.add(new ClassVO(2,"周三34节"));
//		ArrayList<TopicVO>list1=new ArrayList<TopicVO>();
//		list1.add(new TopicVO(257,"XXXX"));
//		return new ResponseEntity<SeminarVO>(new SeminarVO(i,"界面x原型设计","界面原型设计","fixed","2017-09-25","2017-10-25",list,list1),HttpStatus.OK);
//	}
//	@PutMapping("/seminar/{seminarId}")
//	public ResponseEntity putSeminarOfId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@DeleteMapping("/seminar/{seminarId}")
//	public ResponseEntity DeleteSeminarOfId(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/seminar/{seminarId}/my")
//	public ResponseEntity<StudentSeminarVO> getMySeminar(){
//		StudentSeminarVO sem=new StudentSeminarVO(32,"SS","random","OOAD","2017-10-11","2017-11-11",23,true,true);
//		return new ResponseEntity<StudentSeminarVO>(sem,HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/detail")
//	public ResponseEntity<SeminarDetailVO> getSeminarDetail(){
//		SeminarDetailVO sem=new SeminarDetailVO(32,"SS","2017-10-11","2017-11-11","201","qm","xxx2@XXX");
//		return new ResponseEntity<SeminarDetailVO>(sem,HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/topic")
//	public ResponseEntity<TopicVO> getSeminarTopic(){
//		TopicVO sem=new TopicVO(32,"SS","XXXXX","XXXXXXXXXXXXXXXX",5,6,2);
//		return new ResponseEntity<TopicVO>(sem,HttpStatus.OK);
//	}
//	@PostMapping("/seminar/{seminarId}/topic")
//	public ResponseEntity PostSeminarTopic(){
//
//		return new ResponseEntity(HttpStatus.CREATED);
//	}
//	@GetMapping("/seminar/{seminarId}/group")
//	public ResponseEntity<ArrayList<GroupVO>> getSeminarGroup(){
//		ArrayList<GroupVO>list=new ArrayList<GroupVO>();
//		list.add(new GroupVO(28,"1A1",new TopicVO(234,"XXXX")));
//		list.add(new GroupVO(38,"1A2",new TopicVO(234,"XXXX")));
//		return new ResponseEntity<ArrayList<GroupVO>>(list,HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/group/my")
//	public ResponseEntity<GroupVO> getSeminarGroupMy(){
//
//		GroupVO list=new GroupVO(28,"1A1s");
//
//		return new ResponseEntity<GroupVO> (list,HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/class/{classId}/attendance")
//	public ResponseEntity<SeminarVO> getAttandenceStatus(){
//		return new ResponseEntity<SeminarVO>(new SeminarVO(40,"grouping"),HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/class/{classId}/attendance/late")
//	public ResponseEntity<ArrayList<AttendanceListVO>>getAttendanceListVOlate(){
//		ArrayList<AttendanceListVO>list=new ArrayList<AttendanceListVO>();
//		list.add(new AttendanceListVO(1234,"WW"));
//		list.add(new AttendanceListVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListVO>>(list,HttpStatus.OK);
//
//	}
//	@GetMapping("/seminar/{seminarId}/class/{classId}/attendance/absent")
//	public ResponseEntity<ArrayList<AttendanceListVO>>getAttendanceListVOabsent(){
//		ArrayList<AttendanceListVO>list=new ArrayList<AttendanceListVO>();
//		list.add(new AttendanceListVO(1234,"WW"));
//		list.add(new AttendanceListVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListVO>>(list,HttpStatus.OK);
//
//	}
//	@GetMapping("/seminar/{seminarId}/class/{classId}/attendance/present")
//	public ResponseEntity<ArrayList<AttendanceListVO>>getAttendanceListVOpresent(){
//		ArrayList<AttendanceListVO>list=new ArrayList<AttendanceListVO>();
//		list.add(new AttendanceListVO(1234,"WW"));
//		list.add(new AttendanceListVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListVO>>(list,HttpStatus.OK);
//
//	}
//	@PutMapping("/seminar/{seminarId}/class/{classId}/attendance/{studentId}")
//	public ResponseEntity upGPS(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/topic/{topicId}")
//	public ResponseEntity<TopicVO> gettopic(){
//		return new ResponseEntity<TopicVO>(new TopicVO(12,"A","Ssaasa","asasasss",3,4,5),HttpStatus.OK);
//
//	}
//	@PutMapping("/topic/{topicId}")
//	public ResponseEntity Puttopic(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//
//	}
//	@DeleteMapping("/topic/{topicId}")
//	public ResponseEntity Deletetopic(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//
//	}
//	@GetMapping("/topic/{topicId}/group")
//	public ResponseEntity<ArrayList<AttendanceListVO>>gettopicgroup(){
//		ArrayList<AttendanceListVO>list=new ArrayList<AttendanceListVO>();
//		list.add(new AttendanceListVO(1234,"WW"));
//		list.add(new AttendanceListVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListVO>>(list,HttpStatus.OK);
//
//	}
//
//	@GetMapping("/group/{groupId}")//未完成
//	public ResponseEntity<GroupVO>getgroup(){
//		ArrayList<UserVO>list=new ArrayList<UserVO>();
//		list.add(new UserVO(7,"ss"));
//		list.add(new UserVO(9,"sss"));
//		return new ResponseEntity<GroupVO>(new GroupVO(7),HttpStatus.OK);
//
//	}
//	@PutMapping("/group/{groupID}/resign")
//	public ResponseEntity gleaderResign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/group/{groupId}/add")
//	public ResponseEntity gleaderResigns(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/group/{groupID}/assign")
//	public ResponseEntity leaderAssign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/group/{groupID}/remove")
//	public ResponseEntity dleaderAssign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PostMapping("/group/{groupID}/topic")
//	public ResponseEntity leaderAdssign(){
//		return new ResponseEntity(HttpStatus.CREATED);
//	}
//	@PutMapping("/group/{groupID}/topic/{topicId}")
//	public ResponseEntity lseaderAssign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@DeleteMapping("/group/{groupID}/topic/{topicId}")
//	public ResponseEntity lseaderAssigns(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/group/{groupId}/grade")
//	public ResponseEntity<SeminarGradeVO> lseaderAssignss(){
//		ArrayList<presentationGrade> list=new ArrayList<presentationGrade>();
//		list.add(new presentationGrade(2,2));
//		return new ResponseEntity<SeminarGradeVO>(new SeminarGradeVO(list,3,3),HttpStatus.OK);
//	}
//	@PutMapping("/group/{groupID}/grade/report")
//	public ResponseEntity lseadercAssign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@PutMapping("/group/{groupId}/grade/presentation/{studentId}")
//	public ResponseEntity lsedadercAssign(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//
//	@GetMapping("/school")
//	public ResponseEntity<ArrayList<SchoolVO>> searchShoolSomeplace(){
//		ArrayList<SchoolVO>list=new ArrayList<SchoolVO>();
//		list.add(new SchoolVO(32,"厦门大学","福建","厦门"));
//		list.add(new SchoolVO(32,"厦门理工学院","福建","厦门"));
//		list.add(new SchoolVO(32,"厦门城市职业学院","福建","厦门"));
//		return new ResponseEntity<ArrayList<SchoolVO>>(list,HttpStatus.OK);
//
//	}
//	@PostMapping("/school")
//	public ResponseEntity creatShoolSomeplace(){
//
//		return new ResponseEntity(HttpStatus.CREATED);
//
//	}
//	@PostMapping("/upload/avatar")
//	public ResponseEntity upHead(){
//
//		return new ResponseEntity(HttpStatus.OK);
//
//	}
}


























