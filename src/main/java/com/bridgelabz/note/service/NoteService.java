package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.exception.NoteNotFoundException;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.repository.NoteRepository;
import com.bridgelabz.note.util.ResponseClass;
import com.bridgelabz.note.util.TokenUtil;
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
        String body = "Note has been added sucessfully " + noteModel.getId();
        String subject = "Note registration completed";
        mailService.send(noteModel.getEmailid(), body, subject);
        return new ResponseClass(200, "Sucessfull", noteModel);
    }
        throw new NoteNotFoundException(400, "token is wrong");
    }

    @Override
    public ResponseClass updateNote(String token, NoteDTO noteDTO, Long id) {
        boolean isNote = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isNote) {
            Long noteId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isNotePresent = noteRepository.findById(noteId);
            if (isNotePresent.isPresent()) {
                Optional<NoteModel> isNoteAvailable = noteRepository.findById(id);
                if (isNoteAvailable.isPresent()) {
                    isNoteAvailable.get().setTitle(noteDTO.getTitle());
                    isNoteAvailable.get().setDescription(noteDTO.getDescription());
                    isNoteAvailable.get().setUserId(noteDTO.getUserId());
                    isNoteAvailable.get().setEmailid(noteDTO.getEmailid());
                    isNoteAvailable.get().setColor(noteDTO.getColor());
                    noteRepository.save(isNoteAvailable.get());
                    String body = "candidate is added successfully with adminId " + isNoteAvailable.get().getId();
                    String subject = "Candidate registration successful";
                    mailService.send(isNoteAvailable.get().getEmailid(), subject, body);
                    return new ResponseClass(200, "Sucessfull", isNoteAvailable.get());
                } else {
                    throw new NoteNotFoundException(400, "Note is not available");
                }
            }}
                throw new NoteNotFoundException(400, "token is not available");
    }

    @Override
    public List<NoteModel> getNoteData(String token) {
        boolean isNote = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if (isNote){
            Long noteId = tokenUtil.decodeToken(token);
            Optional<NoteModel> isNotePresent = noteRepository.findById(noteId);
            if (isNotePresent.isPresent()){
                List<NoteModel> isNoteAvailable = noteRepository.findAll();
                if (isNoteAvailable.size() > 0){
                    return isNoteAvailable;
                }else{
                    throw  new NoteNotFoundException(400, "no Note is present");
                }
            }}
        throw new NoteNotFoundException(400, "token is wrong ");
    }

    @Override
    public ResponseClass deleteNote(long id, String token) {
        boolean isNote = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if(isNote){
        Long noteId = tokenUtil.decodeToken(token);
        Optional<NoteModel> isNotePresent = noteRepository.findById(noteId);
        if(isNotePresent.isPresent()){
            Optional<NoteModel> isNoteAvailable = noteRepository.findById(id);
            if (isNoteAvailable.isPresent()){
                noteRepository.delete(isNoteAvailable.get());
                return new ResponseClass(200, "Sucessfull",isNoteAvailable.get());
            }else{
                throw new NoteNotFoundException(400, "No note is available");
            }
        }}
        throw new NoteNotFoundException(400, "token is wrong");
    }




}
