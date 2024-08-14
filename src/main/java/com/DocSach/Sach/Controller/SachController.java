package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.SachRequest;
import com.DocSach.Sach.DTO.SachResponse;
import com.DocSach.Sach.DTO.SachUpdate;
import com.DocSach.Sach.Entity.*;
import com.DocSach.Sach.Service.SachService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sach")
@RequiredArgsConstructor
@Slf4j
public class SachController {
    private final SachService sachService;
    private static final Logger logger = LoggerFactory.getLogger(SachController.class);


    @GetMapping("/{id}")
    public ResponseEntity<?> getSachResponsi(@PathVariable("id") Long id) {
        return sachService.getSachResponsi(id);
    }

    @GetMapping()
    public ResponseEntity<?> getAllSach() {
        return sachService.getAllSach();
    }

    @PostMapping()
    public ResponseEntity<?> addSach(@RequestBody SachRequest sachRequest) {
        return sachService.addSach(sachRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSach(@PathVariable("id") Long id) {
        return sachService.deleteSach(id);
    }
    @PutMapping
    public ResponseEntity<?> updateSach(@RequestBody SachUpdate sachUpdate) {
        return sachService.updateSach(sachUpdate);
    }
    @PostMapping("/add-sachs")
    public ResponseEntity<?> addSachs(@RequestBody List<SachRequest> sachRequests) {
        return sachService.addSachs(sachRequests);
    }
    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable("fileName") String fileName) {
        return sachService.getPdf(fileName);
    }

    @GetMapping("/img/{fileName}")
    public ResponseEntity<?> getImg(@PathVariable("fileName") String fileName) {
        return sachService.getImg(fileName);
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImg(@RequestParam("file") MultipartFile file){
        String uploadDir = "src/main/resources/statics/" + file.getOriginalFilename();
        try{
            File targetFile = new File(uploadDir);
            file.transferTo(targetFile.toPath());
            logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/luot-doc")
    public List<LuotDocSach> getLuotDoc(){
        return sachService.getLuotDoc();
    }
    @GetMapping("/all-favor")
    public List<CountAllFavor> getAllFavorCount(){
        return sachService.getCountAllFavorReponsi();
    }
    @GetMapping("/top-5-sach")
    public List<Top5Sach> getTop5(){
        return sachService.getTop5();
    }
    @GetMapping("/top-5-sach-mien-phi")
    public List<Top5MienPhi> getTop5MienPhi(){
        return sachService.getTop5MienPhi();
    }
    @GetMapping("/sach-lsd")
    public List<Sach_SLD> getSachFulCount(){
        return sachService.getSach_LSD();
    }
}
