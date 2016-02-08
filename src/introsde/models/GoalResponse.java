package introsde.models;



import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import javax.xml.bind.annotation.XmlTransient;

import static java.lang.Math.toIntExact;


public class GoalResponse {

		private String reached;
		private String author;
		private String content;

  public GoalResponse(){}

	public String getReached() {
		return reached;
	}

	public void setReached(String reached) {
		this.reached = reached;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
