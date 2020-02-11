package book.stdscore.data;

import java.io.Serializable;

public class User implements Serializable {
	private String name ;
	private String password ;
	private int job ;
	public static final int STUDENT=0;
	public static final int TEACHER=1;
	public static final int ADMIN=2;
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public int getJob() {
		return job;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + job;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (job != other.job)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	public User(String name, String password, int job) {
		super();
		this.name = name;
		this.password = password;
		this.job = job ;
	}
	@Override
	public String toString() {
		return "User [job=" + job + ", name=" + name + ", password=" + password
				+ "]";
	}
	
}
