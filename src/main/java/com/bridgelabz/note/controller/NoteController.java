package com.bridgelabz.note.controller;

import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.service.INoteService;
import com.bridgelabz.note.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("note")
public class NoteController {

    @Autowired
    INoteService noteService;

    @PostMapping("/addnote")
    public ResponseEntity<ResponseClass> addNote(@RequestBody NoteDTO noteDTO) {
        ResponseClass responseClass = noteService.addNote(noteDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<ResponseClass> updateNote(@RequestHeader String token, @PathVariable NoteDTO noteDTO, @PathVariable Long noteId) {
        ResponseClass responseClass = noteService.updateNote(token, noteDTO, noteId);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/getnotedata")
    public ResponseEntity<List<?>> getNoteData(@RequestHeader String token) {
        List<NoteModel> responseClass = noteService.getNoteData(token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @DeleteMapping("deletenote")
    public ResponseEntity<ResponseClass> deleteNote(@PathVariable long noteId, @RequestHeader String token) {
        ResponseClass responseClass = noteService.deleteNote(noteId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/readnotesbyid/{noteId}")
    public ResponseEntity<ResponseClass> readNotesById(@RequestHeader String token, @PathVariable long noteId) {
        ResponseClass responseClass = noteService.readNotesById(token, noteId);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/restore")
    public ResponseEntity<ResponseClass> restoreNotes(@PathVariable long noteId, @RequestHeader String token){
        ResponseClass responseClass = noteService.restoreNotes(noteId, token);
        return  new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PostMapping("/addcolour")
    public ResponseEntity<ResponseClass> addColour(@RequestParam long noteId, @RequestParam String colour, @RequestHeader String token){
        ResponseClass responseClass = noteService.addColour(noteId, colour, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);

    }

    @PutMapping("pin/{noteId}")
    public ResponseEntity<ResponseClass> pinId(@PathVariable long noteId, @RequestHeader String token){
        ResponseClass responseClass = noteService.pin(noteId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/archive/{noteId}")
    public ResponseEntity<ResponseClass> archiveNote(@PathVariable long noteId, @RequestHeader String token){
        ResponseClass responseClass = noteService.archiveNote(noteId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PostMapping("/addcollabrators")
    public ResponseEntity<ResponseClass> addCollabrators(@RequestParam Long noteId, @RequestParam String emailId, @RequestHeader String collabrators, String token, Long collabratorUserId ) {
        ResponseClass responseClass = noteService.addCollabrator(noteId, emailId, collabrators, token, collabratorUserId);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PostMapping("/setremainder/{id}")
    ResponseEntity<ResponseClass> setRemainder(@RequestHeader String token, @PathVariable Long id, @RequestParam String remainderTime){
        NoteModel noteModel = noteService.setRemainder(remainderTime, token, id);
        ResponseClass responseClass = new ResponseClass(400, "Remainder set successfully", noteModel);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }











}






