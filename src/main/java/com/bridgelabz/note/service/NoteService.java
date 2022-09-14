package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.exception.NoteNotFoundException;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.repository.NoteRepository;
import com.bridgelabz.note.util.ResponseClass;
import com.bridgelabz.note.util.TokenUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    MailService mailService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseClass addNote(String token, NoteDTO noteDTO) {
        boolean isNote = restTemplate.getForObject("http://localhost:8091/note/validate/" + token, Boolean.class );
        if (isNote){
             NoteModel noteModel = new NoteModel(noteDTO);
             noteModel.setRegisterDate(LocalDateTime.now());
             noteRepository.save(noteModel);
             String body = "Note has been added successfully" + noteModel.getId();
             String subject = "Note registration completed";
             mailService.send(noteModel.getEmailid(), body, subject);
             return new ResponseClass(200, " Successfully ", noteModel);
        }
        throw new NoteNotFoundException(400, "token is wrong");
    }

    @Override
    public ResponseClass updateNote(String token, NoteDTO noteDTO, Long noteId) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNotePresent) {
            Optional<NoteModel> isNoteAvailable = noteRepository.findById(noteId);
                if (isNoteAvailable.isPresent()) {
                    isNoteAvailable.get().setTitle(noteDTO.getTitle());
                    isNoteAvailable.get().setDescription(noteDTO.getDescription());
                    isNoteAvailable.get().setEmailid(noteDTO.getEmailid());
                    isNoteAvailable.get().setColor(noteDTO.getColor());
                    noteRepository.save(isNoteAvailable.get());
                    String body = "Note is updated" + isNoteAvailable.get().getId();
                    String subject = "Note registration successful";
                    mailService.send(isNoteAvailable.get().getEmailid(), subject, body);
                    return new ResponseClass(200, " Successfully ", isNoteAvailable.get());
                }
                  throw new NoteNotFoundException(400, "Note is not available");
            }
               throw new NoteNotFoundException(400, "Token is wrong");
        }


    @Override
    public List<NoteModel> getNoteData(String token) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNotePresent){
            List<NoteModel> isNoteAvailable = noteRepository.findAll();
                    return isNoteAvailable;
                }
                throw new NoteNotFoundException(400, "no Note is present");
            }


    @Override
    public ResponseClass deleteNote(long noteId, String token) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if(isNotePresent){
            Optional<NoteModel> isNoteAvailable = noteRepository.findById(noteId);
            if (isNoteAvailable.isPresent()){
                noteRepository.delete(isNoteAvailable.get());
                return new ResponseClass(200, "Successfully",isNoteAvailable.get());
            }
            throw new NoteNotFoundException(400, "No note is available");
        }
        throw new NoteNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseClass readNotesById(String token, long noteId) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNotePresent) {
                Optional<NoteModel> isNoteAvailable = noteRepository.findById(noteId);
                if (isNoteAvailable.isPresent()) {
                    return new ResponseClass(200, "successfully", isNoteAvailable.get());
                }
                throw new NoteNotFoundException(400, "No note is available");
            }
            throw new NoteNotFoundException(400,"token is wrong");
        }

    @Override
    public ResponseClass restoreNotes(long noteId, String token) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNotePresent){
            Optional<NoteModel> isNoteAvailable = noteRepository.findById(noteId);
            if (isNoteAvailable.isPresent()){
                isNoteAvailable.get().setArchieve(true);
                isNoteAvailable.get().setTrash(true);
                noteRepository.save(isNoteAvailable.get());
                return new ResponseClass(200,"Sucessfully", isNoteAvailable.get());
            }
            throw new NoteNotFoundException(400, "note is not present");
        }
        throw new NoteNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseClass addColour(long noteId, String colour, String token) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNotePresent){
            Optional<NoteModel> isNoteAvailable = noteRepository.findById(noteId);
            if (isNoteAvailable.isPresent()){
                isNoteAvailable.get().setColor(colour);
                return new ResponseClass(200, "Sucessfully", isNoteAvailable.get());
            }
            throw new NoteNotFoundException(400, "note does not present");
        }
        throw new NoteNotFoundException(400, "token is wrong");
    }

    @Override
    public ResponseClass pin(long noteId, String token) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if(isNotePresent){
            Optional<NoteModel> isNoteAvialable = noteRepository.findById(noteId);
            if (isNoteAvialable.isPresent()){
                isNoteAvialable.get().setArchieve(false);
                isNoteAvialable.get().setPin(true);
                noteRepository.save(isNoteAvialable.get());
                return new ResponseClass(200, "Sucessfully", isNoteAvialable.get());
            }
            throw new NoteNotFoundException(400, "Note does not present");
        }
        throw new NoteNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseClass archiveNote(long noteId, String token) {
        boolean isNotePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNotePresent){
            Optional<NoteModel> isNoteAvailable = noteRepository.findById(noteId);
            if (isNoteAvailable.isPresent()){
                isNoteAvailable.get().setPin(true);
                isNoteAvailable.get().setArchieve(false);
                noteRepository.save(isNoteAvailable.get());
                return new ResponseClass(200, "Sucessfully", isNoteAvailable.get());
            }
            throw new NoteNotFoundException(400, "note does not found");
        }
        throw new NoteNotFoundException(400, "Token is wrong");
    }


}
