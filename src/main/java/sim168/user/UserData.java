package sim168.user;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sim168.util.DBConnect;

public class UserData {
	

	private static final String REGISTER_BRANCH = null;
	private static final String DATE_INSERT = null;
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null,Stmt2=null;
	ResultSet rs = null;
	public List<UserModal> getUserTables(){
		List<UserModal> userList = new ArrayList<UserModal>();
		
		String  sql = "SELECT * FROM `stock_sim` LIMIT 100";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				UserModal userModel = new UserModal();
				userModel.setMSISDN(rs.getString("MSISDN"));
				userModel.setBRANCH(rs.getString("BRANCE"));
				userModel.setSIM(rs.getString("SIM"));
				userModel.setSTATUS(rs.getString("STATUS"));
				userModel.setREGISTER_BRANCH(rs.getString("REGISTER_BRANCH"));
				userModel.setDATE_INSERT(rs.getString("DATE_INSERT"));
				userModel.setREGISTER_DATE(rs.getString("REGISTER_DATE"));
				
				
				
				
				
				

				userList.add(userModel);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		return userList;
	}
}
 