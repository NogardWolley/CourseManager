package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.Course;
import xmu.crms.entity.FixGroup;
import xmu.crms.vo.*;
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
	public ResponseEntity<Course> getInfo(){
		System.out.println("****");
		BigInteger a,b;
		a=BigInteger.valueOf(3);b=BigInteger.valueOf(1);
			return new ResponseEntity<Course>(courseMapper.getCourseByCourseId(b),HttpStatus.OK);
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
//	public ResponseEntity<CourseDetailOrignalVO>getCourseOfId(){
//		return new ResponseEntity<CourseDetailOrignalVO>(new CourseDetailOrignalVO(23,"OOAD","面向对象分析与设计","邱明","mingqiu@xmu.edu.cn"),HttpStatus.OK);
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
//		ArrayList<ClassOrignalVO>list=new ArrayList<ClassOrignalVO>();
//		list.add(new ClassOrignalVO(1,"周三12节"));
//		list.add(new ClassOrignalVO(2,"周三34节"));
//		return new ResponseEntity<SeminarVO>(new SeminarVO(29,"界面原型设计","OOAD","fixed","2017-09-25","2017-10-09",list), HttpStatus.OK);
//	}
//	@GetMapping("/course/{courseId}/grade")
//	public ResponseEntity<ArrayList<SeminarGradeDetailOrignalVO>> getCourseGradeSeminarGradeDetail(){
//		ArrayList<SeminarGradeDetailOrignalVO>list=new ArrayList<SeminarGradeDetailOrignalVO>();
//		list.add(new SeminarGradeDetailOrignalVO("XXXX","XX","XX",3,4,4));
//		list.add(new SeminarGradeDetailOrignalVO("XXXT","TX","TX",3,4,4));
//		return new ResponseEntity<ArrayList<SeminarGradeDetailOrignalVO>>(list, HttpStatus.OK);
//	}
//
//	@GetMapping("/class")
//	public ResponseEntity<ArrayList<ClassOrignalVO>> getClasses(){
//		ArrayList<ClassOrignalVO>list=new ArrayList<ClassOrignalVO>();
//		list.add(new ClassOrignalVO(23,"XXXX",60,"XX","XX","XX","XX"));
//		list.add(new ClassOrignalVO(42,"XXXX",60,"XX","XX","XX","XX"));
//		return new ResponseEntity<ArrayList<ClassOrignalVO>>(list, HttpStatus.OK);
//	}
//	@GetMapping("/class/{classId}")
//	public ResponseEntity<ClassOrignalVO> getClassOfId(){
//		return new ResponseEntity<ClassOrignalVO>(new ClassOrignalVO(23,"XX",123,"XX","XX",-1,"/XX/XX.XX",new ProportionVO(50,50,20,60,20)),HttpStatus.OK);
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
//	public ResponseEntity<ArrayList<StudentOrignalVO>> getStudentFormClassId(){
//		ArrayList<StudentOrignalVO>list=new ArrayList<StudentOrignalVO>();
//		list.add(new StudentOrignalVO(
//                3486,
//                "StudentOrignalVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolOrignalVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		list.add(new StudentOrignalVO(
//                3487,
//                "StudentOrignalVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolOrignalVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		return new ResponseEntity<ArrayList<StudentOrignalVO>>(list, HttpStatus.OK);
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
//		ArrayList<StudentOrignalVO>list=new ArrayList<StudentOrignalVO>();
//		list.add(new StudentOrignalVO(
//                3486,
//                "StudentOrignalVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolOrignalVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		list.add(new StudentOrignalVO(
//                3487,
//                "StudentOrignalVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolOrignalVO(
//                        32,
//                        "厦门大学"
//                ),
//                "null",
//                "XXX",
//                "XXX",
//                "/avator/3486.png"
//        ));
//		ClassGroupVO g=new ClassGroupVO(new StudentOrignalVO(
//                34878,
//                "StudentOrignalVO",
//                "234546",
//                "qiuming",
//                "123456789",
//                "xxxx@xx.com",
//                "male",
//                new SchoolOrignalVO(
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
//	public ResponseEntity<AttendanceListOrignalVO> memerAssign(){
//		return new ResponseEntity<AttendanceListOrignalVO>(new AttendanceListOrignalVO(77,"ss") ,HttpStatus.NO_CONTENT);
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
//		ArrayList<ClassOrignalVO>list=new ArrayList<ClassOrignalVO>();
//		list.add(new ClassOrignalVO(1,"周三12节"));
//		list.add(new ClassOrignalVO(2,"周三34节"));
//		ArrayList<TopicOrignalVO>list1=new ArrayList<TopicOrignalVO>();
//		list1.add(new TopicOrignalVO(257,"XXXX"));
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
//	public ResponseEntity<StudentSeminarOrignalVO> getMySeminar(){
//		StudentSeminarOrignalVO sem=new StudentSeminarOrignalVO(32,"SS","random","OOAD","2017-10-11","2017-11-11",23,true,true);
//		return new ResponseEntity<StudentSeminarOrignalVO>(sem,HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/detail")
//	public ResponseEntity<SeminarDetailOrignalVO> getSeminarDetail(){
//		SeminarDetailOrignalVO sem=new SeminarDetailOrignalVO(32,"SS","2017-10-11","2017-11-11","201","qm","xxx2@XXX");
//		return new ResponseEntity<SeminarDetailOrignalVO>(sem,HttpStatus.OK);
//	}
//	@GetMapping("/seminar/{seminarId}/topic")
//	public ResponseEntity<TopicOrignalVO> getSeminarTopic(){
//		TopicOrignalVO sem=new TopicOrignalVO(32,"SS","XXXXX","XXXXXXXXXXXXXXXX",5,6,2);
//		return new ResponseEntity<TopicOrignalVO>(sem,HttpStatus.OK);
//	}
//	@PostMapping("/seminar/{seminarId}/topic")
//	public ResponseEntity PostSeminarTopic(){
//
//		return new ResponseEntity(HttpStatus.CREATED);
//	}
//	@GetMapping("/seminar/{seminarId}/group")
//	public ResponseEntity<ArrayList<GroupVO>> getSeminarGroup(){
//		ArrayList<GroupVO>list=new ArrayList<GroupVO>();
//		list.add(new GroupVO(28,"1A1",new TopicOrignalVO(234,"XXXX")));
//		list.add(new GroupVO(38,"1A2",new TopicOrignalVO(234,"XXXX")));
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
//	public ResponseEntity<ArrayList<AttendanceListOrignalVO>>getAttendanceListVOlate(){
//		ArrayList<AttendanceListOrignalVO>list=new ArrayList<AttendanceListOrignalVO>();
//		list.add(new AttendanceListOrignalVO(1234,"WW"));
//		list.add(new AttendanceListOrignalVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListOrignalVO>>(list,HttpStatus.OK);
//
//	}
//	@GetMapping("/seminar/{seminarId}/class/{classId}/attendance/absent")
//	public ResponseEntity<ArrayList<AttendanceListOrignalVO>>getAttendanceListVOabsent(){
//		ArrayList<AttendanceListOrignalVO>list=new ArrayList<AttendanceListOrignalVO>();
//		list.add(new AttendanceListOrignalVO(1234,"WW"));
//		list.add(new AttendanceListOrignalVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListOrignalVO>>(list,HttpStatus.OK);
//
//	}
//	@GetMapping("/seminar/{seminarId}/class/{classId}/attendance/present")
//	public ResponseEntity<ArrayList<AttendanceListOrignalVO>>getAttendanceListVOpresent(){
//		ArrayList<AttendanceListOrignalVO>list=new ArrayList<AttendanceListOrignalVO>();
//		list.add(new AttendanceListOrignalVO(1234,"WW"));
//		list.add(new AttendanceListOrignalVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListOrignalVO>>(list,HttpStatus.OK);
//
//	}
//	@PutMapping("/seminar/{seminarId}/class/{classId}/attendance/{studentId}")
//	public ResponseEntity upGPS(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	@GetMapping("/topic/{topicId}")
//	public ResponseEntity<TopicOrignalVO> gettopic(){
//		return new ResponseEntity<TopicOrignalVO>(new TopicOrignalVO(12,"A","Ssaasa","asasasss",3,4,5),HttpStatus.OK);
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
//	public ResponseEntity<ArrayList<AttendanceListOrignalVO>>gettopicgroup(){
//		ArrayList<AttendanceListOrignalVO>list=new ArrayList<AttendanceListOrignalVO>();
//		list.add(new AttendanceListOrignalVO(1234,"WW"));
//		list.add(new AttendanceListOrignalVO(1235,"WWe"));
//		return new ResponseEntity<ArrayList<AttendanceListOrignalVO>>(list,HttpStatus.OK);
//
//	}
//
//	@GetMapping("/group/{groupId}")//未完成
//	public ResponseEntity<GroupVO>getgroup(){
//		ArrayList<UserOrignalVO>list=new ArrayList<UserOrignalVO>();
//		list.add(new UserOrignalVO(7,"ss"));
//		list.add(new UserOrignalVO(9,"sss"));
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
//	public ResponseEntity<SeminarGradeOrignalVO> lseaderAssignss(){
//		ArrayList<presentationGrade> list=new ArrayList<presentationGrade>();
//		list.add(new presentationGrade(2,2));
//		return new ResponseEntity<SeminarGradeOrignalVO>(new SeminarGradeOrignalVO(list,3,3),HttpStatus.OK);
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
//	public ResponseEntity<ArrayList<SchoolOrignalVO>> searchShoolSomeplace(){
//		ArrayList<SchoolOrignalVO>list=new ArrayList<SchoolOrignalVO>();
//		list.add(new SchoolOrignalVO(32,"厦门大学","福建","厦门"));
//		list.add(new SchoolOrignalVO(32,"厦门理工学院","福建","厦门"));
//		list.add(new SchoolOrignalVO(32,"厦门城市职业学院","福建","厦门"));
//		return new ResponseEntity<ArrayList<SchoolOrignalVO>>(list,HttpStatus.OK);
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


























