package com.bridgelabz.note.controller;

import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.service.INoteService;
import com.bridgelabz.note.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("note")
public class NoteController {

    @Autowired
    INoteService noteService;

    @PostMapping("/addnote")
    public ResponseEntity<ResponseClass> addNote(@RequestHeader String token, @Valid @PathVariable NoteDTO noteDTO){
        ResponseClass responseClass = noteService.addNote(token, noteDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseClass> updateNote(@RequestHeader String token, @Valid @PathVariable NoteDTO noteDTO, @PathVariable Long id){
        ResponseClass responseClass = noteService.updateNote(token, noteDTO, id);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/getnote")
    public ResponseEntity<List<?>> getNoteData(@RequestHeader String token){
        List<NoteModel> responseClass = noteService.getNoteData(token);
        return new ResponseEntity<>(responseClass ,HttpStatus.OK);
    }

    @DeleteMapping("deletenote")
    public ResponseEntity<ResponseClass> deleteNote(@PathVariable long id, @RequestHeader String token){
        ResponseClass responseClass = noteService.deleteNote(id, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    






}
