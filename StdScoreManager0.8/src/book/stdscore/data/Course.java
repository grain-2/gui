package book.stdscore.data;

import java.io.Serializable;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4906331992862999016L;
	private String name;
	private String type;
	private float score;

	public Course(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
