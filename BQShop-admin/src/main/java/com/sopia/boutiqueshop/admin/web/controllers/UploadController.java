//package com.sopia.boutiqueshop.admin.web.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * @author Sopia Alfred on 6/10/2018 9:26 AM.
// * @project boutiqueshop.
// */
//
//@Controller
//public class UploadController {
//
//    //save the upload file to this folder
//    private static String UPLOAD_FOLDER = "D://productsimages//";
//
//    @RequestMapping("/")
//    public String index(){
//        return "upload";
//    }
//    @RequestMapping("/upload")
//    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
//
//        if(file.isEmpty()){
//            redirectAttributes.addFlashAttribute("message","please select a file to upload");
//            return "redirect: uploadStatus";
//        }
//        try{
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
//            Files.write(path,bytes);
//            redirectAttributes.addFlashAttribute("message","You have successfully uploaded" + file.getOriginalFilename() + "");
//
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return  "redirect:/uploadStatus";
//    }
//    @RequestMapping("/uploadStatus")
//    public String uploadStatus(){
//        return "uploadStatus";
//    }
//}
