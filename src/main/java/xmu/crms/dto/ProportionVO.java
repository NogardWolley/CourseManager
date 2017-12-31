package xmu.crms.dto;
import java.util.ArrayList;
public class ProportionVO {
	Integer report;
	Integer presentation;
	Integer C;
	Integer B;
	Integer A;
	public ProportionVO(Integer report, Integer presentation, Integer c, Integer b, Integer a) {
		super();
		this.report = report;
		this.presentation = presentation;
		C = c;
		B = b;
		A = a;
	}
	public Integer getC() {
		return C;
	}
	public void setC(Integer c) {
		C = c;
	}
	public Integer getB() {
		return B;
	}
	public void setB(Integer b) {
		B = b;
	}
	public Integer getA() {
		return A;
	}
	public void setA(Integer a) {
		A = a;
	}
	public Integer getReport() {
		return report;
	}
	public void setReport(Integer report) {
		this.report = report;
	}
	public Integer getPresentation() {
		return presentation;
	}
	public void setPresentation(Integer presentation) {
		this.presentation = presentation;
	}

}
