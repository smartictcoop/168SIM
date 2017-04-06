package com.smartict.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.codehaus.jettison.json.JSONObject;

import com.smartict.Model.CSVDataModel;

import sim168.util.DBConnect;


public class CSVFile {
	static char DEFAULT_SEPARATOR = ',';
	static char DEFAULT_QUOTE = '"';
	
	DBConnect agent = new DBConnect();
	
	public boolean isPatternFileImport(File fileUpload, int column, List<String> columnName) throws FileNotFoundException{
		
		if(fileUpload == null)	return false;
		
		if(!fileUpload.exists())	return false;
		
		Scanner scanner = new Scanner(fileUpload);
		if (scanner.hasNextLine()) {
			List<String> curline = parseLine(scanner.nextLine());
			if(curline.size() != column){
				scanner.close();
				return false;
			}else{
				for(int i = 0; i < columnName.size(); i++){
					if(!curline.get(i).equals(columnName.get(i))){
						scanner.close();
						return false;
					}
				}
			}
			
		}else{
			scanner.close();
			return false;
		}
		
		scanner.close();
		return true;
	}
	
	public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	 }

	 public static List<String> parseLine(String cvsLine, char separators) {
	    return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	 }
	 @SuppressWarnings("null")
	public static List<String> parseLine(String cvsLine, char separators, char customQuote) {
	
	     List<String> result = new ArrayList<String>();
	
	     //if empty, return!
	     if (cvsLine == null && cvsLine.isEmpty()) {
	         return result;
	     }
	
	     if (customQuote == ' ') {
	         customQuote = DEFAULT_QUOTE;
	     }
	
	     if (separators == ' ') {
	         separators = DEFAULT_SEPARATOR;
	     }
	
	     StringBuffer curVal = new StringBuffer();
	     boolean inQuotes = false;
	     boolean startCollectChar = false;
	     boolean doubleQuotesInColumn = false;
	
	     char[] chars = cvsLine.toCharArray();
	
	     for (char ch : chars) {
	
	         if (inQuotes) {
	             startCollectChar = true;
	             if (ch == customQuote) {
	                 inQuotes = false;
	                 doubleQuotesInColumn = false;
	             } else {
	
	                 //Fixed : allow "" in custom quote enclosed
	                 if (ch == ' ') {
	                     if (!doubleQuotesInColumn) {
	                         curVal.append(ch);
	                         doubleQuotesInColumn = true;
	                     }
	                 } else {
	                     curVal.append(ch);
	                 }
	
	             }
	         } else {
	             if (ch == customQuote) {
	
	                 inQuotes = true;
	
	                 //Fixed : allow "" in empty quote enclosed
	                 if (chars[0] != '"' && customQuote == '\"') {
	                    
	                 }
	
	                 //double quotes in column will hit this!
	                 if (startCollectChar) {
	                     curVal.append('"');
	                 }
	
	             } else if (ch == separators) {
	
	                 result.add(curVal.toString());
	
	                 curVal = new StringBuffer();
	                 startCollectChar = false;
	
	             } else if (ch == '\r') {
	                 //ignore LF characters
	                 continue;
	             } else if (ch == '\n') {
	                 //the end, break!
	                 break;
	             } else {
	                 curVal.append(ch);
	             }
	         }
	
	     }
	
	     result.add(curVal.toString());
	
	     return result;
	 }
	 
	 public List<CSVDataModel> buildListDataFromCsvFile(File fileUpload, int rowsData){
			List<CSVDataModel> listData = new ArrayList<CSVDataModel>();
			Scanner scanner = null;
			try {
				scanner = new Scanner(fileUpload);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			int i = 0;
			while (scanner.hasNext()) {
				List<String> curline = parseLine(scanner.nextLine());
				if (i >= rowsData){
					CSVDataModel csvModel = new CSVDataModel();
					//csvModel.setSubgroupName(curline.get(5));
					csvModel.setSim(curline.get(1));
					csvModel.setMsisdn(curline.get(2));
					csvModel.setBranch(curline.get(3));
					listData.add(csvModel);
				}
				i++;
			}
			return listData;
		}
	 
	 
	 public List<String> addDetailPartnerService(List<CSVDataModel> listData){
			List<String> listAddDetail = new ArrayList<String>();
			
			Statement stmt = null;
			int rowsupdate = 0 ;
			
			try {
				String sql = "INSERT INTO Temp_SIM (SIM, MSISDN, BRANCE, DATE_INSERT) values ";
				
				int countData = 0;
				
				for (CSVDataModel csvModel : listData) {
					if(countData >= 1){
						sql += ",";
					}
					//make Multiple Query
					sql += "('"+csvModel.getSim()+"', "
							+ "'"+csvModel.getMsisdn()+"', "
							+ "'"+csvModel.getBranch()+"', "
							+"now())";
					
					
					if(countData == 999){
						Connection conn = agent.getConnectMYSql();
						stmt = conn.createStatement();
						rowsupdate += stmt.executeUpdate(sql);
						
						if(!stmt.isClosed()) stmt.close();
						if(!conn.isClosed()) conn.close();
						
						sql = "INSERT INTO Temp_SIM (SIM, MSISDN, BRANCE, DATE_INSERT) values ";
						
						countData = 0;
					}else{
						countData++;
					}
					
				}
				 
				Connection conn = agent.getConnectMYSql();
				stmt = conn.createStatement();
				rowsupdate += stmt.executeUpdate(sql);
				if( rowsupdate > 0){
					listAddDetail.add( String.valueOf(rowsupdate));
					listAddDetail.add("success");
					listAddDetail.add("à¾ÔèÁ¢éÍÁÙÅÊÓàÃç¨¨Ó¹Ç¹ "+rowsupdate+" àÅ¢ËÁÒÂ");
				}
				
				
				if(!stmt.isClosed()) stmt.close();
				if(!conn.isClosed()) conn.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return  listAddDetail;
		}
	 
	 
	 
	 public void CaddDetailPartnerService() throws IOException, Exception{
			List<String> listAddDetail = new ArrayList<String>();
			
			Statement stmt = null;
			int rowsupdate = 0 ;
			
			
				String sql = "INSERT INTO stock_sim (SIM, MSISDN, BRANCE) SELECT SIM, MSISDN, BRANCE FROM Temp_SIM "
						+ "WHERE MSISDN not in ( "
						+ "SELECT MSISDN FROM stock_sim) ";
				
				
				 
				Connection conn = agent.getConnectMYSql();
				stmt = conn.createStatement();
				rowsupdate += stmt.executeUpdate(sql);
				
			
		}
	 
	 
	 public void updateDetailPartnerService() throws IOException, Exception{
			List<String> listAddDetail = new ArrayList<String>();
			
			Statement stmt = null;
			int rowsupdate = 0 ;
			
			
				String sql = "UPDATE Test_SIM INNER JOIN Temp_SIM "
						+ "ON Test_SIM.MSISDN = Temp_SIM.MSISDN "
						+ "SET Test_SIM.SIM = Temp_SIM.SIM, Test_SIM.MSISDN = Temp_SIM.MSISDN, "
						+ "Test_SIM.BRANCE = Temp_SIM.BRANCE ";
				
				
				 
				Connection conn = agent.getConnectMYSql();
				stmt = conn.createStatement();
				rowsupdate += stmt.executeUpdate(sql);
				
			
		}
	 
	 public void ClearDetailPartnerService() throws IOException, Exception{
			List<String> listAddDetail = new ArrayList<String>();
			
			Statement stmt = null;
			int rowsupdate = 0 ;
			
			
				String sql = "DELETE FROM Temp_SIM  ";
				
				
				 
				Connection conn = agent.getConnectMYSql();
				stmt = conn.createStatement();
				rowsupdate += stmt.executeUpdate(sql);
				
			
		}
	 
	 
}
