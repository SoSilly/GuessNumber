package Information;

public class Member {
	private String account;  //�˻�
	private String password; //����
	private String icon;	 //ͷ��
	private String name;	 //����
	private int score;		 //����
	private String sex;		 //�Ա�
	private String myNative; //����
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMyNative() {
		return myNative;
	}
	public void setMyNative(String myNative) {
		this.myNative = myNative;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
