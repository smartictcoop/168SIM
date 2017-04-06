package sim168.user;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
	
	private UserModal userModel;
	private List<UserModal> userList = new ArrayList<UserModal>();
	private UserData userData = new UserData();
	
	public String execute(){
		
		userList = userData.getUserTables();
		return SUCCESS;
	}

	public UserModal getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModal userModel) {
		this.userModel = userModel;
	}

	public List<UserModal> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModal> userList) {
		this.userList = userList;
	}
	
}
