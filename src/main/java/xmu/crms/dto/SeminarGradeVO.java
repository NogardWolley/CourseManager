package xmu.crms.dto;
import java.util.ArrayList;
public class SeminarGradeVO {
	ArrayList<presentationGrade> presentationGrade;
	Integer reportGrade;
	Integer grade;

	public SeminarGradeVO(ArrayList<xmu.crms.dto.presentationGrade> presentationGrade, Integer reportGrade,
						  Integer grade) {
		super();
		this.presentationGrade = presentationGrade;
		this.reportGrade = reportGrade;
		this.grade = grade;
	}
	public void setPresentationGrade(ArrayList<presentationGrade> presentationGrade) {
		this.presentationGrade = presentationGrade;
	}
	
	public Integer getReportGrade() {
		return reportGrade;
	}
	public void setReportGrade(Integer reportGrade) {
		this.reportGrade = reportGrade;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
