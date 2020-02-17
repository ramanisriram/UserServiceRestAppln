package service;


public class User {
	private String id;
	private String name;
	public User() {
		
	}
	public User(String userid, String name) {
		this.id = userid;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof User) {
			if(this.getId().equals(((User) o).getId()) && this.getName().equals(((User) o).getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}