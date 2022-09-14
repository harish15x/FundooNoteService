package com.bridgelabz.note.controller;

import com.bridgelabz.note.dto.LableDTO;
import com.bridgelabz.note.model.LableModel;
import com.bridgelabz.note.service.ILableService;
import com.bridgelabz.note.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lable")
public class LabelController {

    @Autowired
    ILableService  lableService;

    @PostMapping("/addlable")
    public ResponseEntity<ResponseClass> addLable(@RequestBody LableDTO lableDTO){
        ResponseClass responseClass = lableService.addLable(lableDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseClass> updateLable(@RequestHeader String token, @PathVariable long lableId, @PathVariable LableDTO lableDTO){
        ResponseClass responseClass = lableService.updateLable(token, lableId, lableDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/getlabledata")
    public ResponseEntity<List<?>> getLableData(@RequestHeader String token){
        List<LableModel >responseClass = lableService.getLableData(token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @DeleteMapping("/deletelable")
    public ResponseEntity<ResponseClass> deleteLable(@RequestHeader String token, @PathVariable long lableId){
        ResponseClass responseClass = lableService.deleteLable(token, lableId);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

}
