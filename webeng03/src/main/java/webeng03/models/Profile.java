package webeng03.models;

public class Profile {
	private String name = "";
	private int loginCounter = 0;	
	public Profile(String name, int loginCounter) {
		super();
		this.name = name;
		this.loginCounter = loginCounter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLoginCounter() {
		return loginCounter;
	}
	public void setLoginCounter(int loginCounter) {
		this.loginCounter = loginCounter;
	}
}
