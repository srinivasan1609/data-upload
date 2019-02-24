package com.leading.company.dataupload.web;

import com.leading.company.dataupload.entity.CompanyDataModel;
import com.leading.company.dataupload.service.DataUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DataUploadIndexController {

    @Autowired
    DataUploadService dataUploadService;


    @RequestMapping("/index")
    public String sayHello() {
        System.out.println("called");
        return "Hello world";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(dataUploadService.Sample());
        System.out.println(file.getOriginalFilename());
        dataUploadService.csvUpload(file);
        return "Sucess";
    }
    @RequestMapping("/fetch")
    public List<CompanyDataModel> fetchData(){
        return dataUploadService.fetchDataFromDb();
    }

}
