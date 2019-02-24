package com.leading.company.dataupload.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class CsvImport {
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lead_cmpy_database","root","root");
            PreparedStatement stmt = con.prepareStatement("LOAD DATA LOCAL INFILE 'C:/Users/Srinivasan S/IdeaProjects/data-upload/loan_part_0009b606f.csv' \n" +
                    "INTO TABLE loan_details_table \n" +
                    "FIELDS TERMINATED BY ',' \n" +
                    "ENCLOSED BY '\"'\n" +
                    "LINES TERMINATED BY '\\n'\n" +
                    "IGNORE 1 ROWS;");
            stmt.execute();
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}

