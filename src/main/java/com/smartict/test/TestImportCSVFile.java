package com.smartict.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.smartict.Model.CSVDataModel;
import com.smartict.utilities.CSVFile;

public class TestImportCSVFile {
	int columnNumber = 6;
	List<String> columnName = new ArrayList<String>();
	
	CSVFile csv = new CSVFile();
	
	//@Test
	public void patternFileIsTrue() throws FileNotFoundException{
		File fileUpload = new File("D:/IMPORT.csv");
		initialInformation();
		Assert.assertTrue(csv.isPatternFileImport(fileUpload, 4, columnName));
		List<CSVDataModel> listCSV = csv.buildListDataFromCsvFile(fileUpload, 1);
		Assert.assertTrue(listCSV.size() > 0);
		
		csv.addDetailPartnerService(listCSV);		
		
	}
	@Test
	public void patternFileIsTruee() throws IOException, Exception{
		
		
		csv.CaddDetailPartnerService();
		
	}
	
public void patternFileIsTrueee() throws IOException, Exception{
		
		
		csv.updateDetailPartnerService();
		
	}

public void patternFileIsTrueeee() throws IOException, Exception{
	
	
	csv.ClearDetailPartnerService();
	
}
	
	
	public void initialInformation(){
		columnName.add("Sr.No.");
		columnName.add("SIM");
		columnName.add("MSISDN");
		columnName.add("สาขาออมสิน");
	}
}