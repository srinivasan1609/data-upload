package com.leading.company.dataupload.service;

import com.leading.company.dataupload.entity.CompanyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataUploadService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String Sample(){
        return "hello";
    }
    public void csvUpload(MultipartFile file){
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            insertInDb(convFile.getName());
            convFile.delete();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void insertInDb(String fileName){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lead_cmpy_database","root","root");

            PreparedStatement stmt = con.prepareStatement("LOAD DATA LOCAL INFILE 'C:/Users/Srinivasan S/IdeaProjects/data-upload/"+ fileName +" '\n" +
                    "INTO TABLE loan_details_table \n" +
                    "FIELDS TERMINATED BY ',' \n" +
                    "ENCLOSED BY '\"'\n" +
                    "LINES TERMINATED BY '\\n'\n" +
                    "IGNORE 1 ROWS;");
            stmt.execute();
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public List<CompanyDataModel> fetchDataFromDb(){


        List<CompanyDataModel> fetchDataList = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lead_cmpy_database","root","root");
            Statement statement =con.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from loan_details_table");
            while (resultSet.next()){
                    CompanyDataModel companyDataModel = new CompanyDataModel();
                    companyDataModel.setMemberId(resultSet.getInt(1));
                    companyDataModel.setLoanAmount(resultSet.getInt(2));
                    companyDataModel.setFundedAmntInv(resultSet.getInt(3));
                    companyDataModel.setTerm(resultSet.getString(4));
                    companyDataModel.setIntRate(resultSet.getDouble(5));
                    companyDataModel.setInstallment(resultSet.getDouble(6));
                    companyDataModel.setGrade(resultSet.getString(7));
                    companyDataModel.setEmpTitle(resultSet.getString(8));
                    companyDataModel.setEmpLength(resultSet.getString(9));
                    companyDataModel.setHomeOwnership(resultSet.getString(10));
                    companyDataModel.setAnnualInc(resultSet.getInt(11));
                    companyDataModel.setVerificationStatus(resultSet.getString(12));
                    companyDataModel.setIssueDate(resultSet.getString(13));
                    companyDataModel.setLoanStatus(resultSet.getString(14));
                    companyDataModel.setDescn(resultSet.getString(15));
                    companyDataModel.setPurpose(resultSet.getString(16));
                    companyDataModel.setTitle(resultSet.getString(17));
                    companyDataModel.setAddrState(resultSet.getString(18));
                    companyDataModel.setLastPymtDate(resultSet.getString(19));
                    fetchDataList.add(companyDataModel);
                    System.out.println(companyDataModel);
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
        return fetchDataList.stream().limit(10).collect(Collectors.toList());
    }


}
