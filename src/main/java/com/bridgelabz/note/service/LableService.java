package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.LableDTO;
import com.bridgelabz.note.exception.LableNotFoundException;
import com.bridgelabz.note.model.LableModel;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.repository.LableRepository;
import com.bridgelabz.note.repository.NoteRepository;
import com.bridgelabz.note.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LableService implements ILableService {

    @Autowired
    LableRepository lableRepository;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NoteRepository noteRepository;

    @Override
    public ResponseClass addLable(LableDTO lableDTO) {
        LableModel lableModel = new LableModel(lableDTO);
        lableModel.setRegisterDate(LocalDateTime.now());
        lableRepository.save(lableModel);
        String body = "Note has been added successfully" + lableModel.getId();
        String subject = "Note registration completed";
        mailService.send(lableModel.getEmailId(), body, subject);
        return new ResponseClass(200, " Successfully ", lableModel);
    }

    @Override
    public ResponseClass updateLable(String token, long lableId, LableDTO lableDTO) {
        boolean isLablePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isLablePresent){
            Optional<LableModel> isLableAvailable = lableRepository.findById(lableId);
            if(isLableAvailable.isPresent()){
                isLableAvailable.get().setLabelName(lableDTO.getLabelName());
                isLableAvailable.get().setUpdateDate(LocalDateTime.now());
                lableRepository.save(isLableAvailable.get());
                return new ResponseClass(200, "Sucessfully", isLableAvailable.get());
            }
            throw new LableNotFoundException(400, "Lable not present");
        }
        throw new LableNotFoundException(400, "token is wrong");
    }

    @Override
    public List<LableModel> getLableData(String token) {
        boolean isLablePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isLablePresent){
            List<LableModel> isLableAvailable = lableRepository.findAll();
                return isLableAvailable;
            }
        throw new LableNotFoundException(400, "token is wrong");
        }

    @Override
    public ResponseClass deleteLable(String token, long lableId) {
        boolean isLablePresent = restTemplate.getForObject("http://localhost:8091/note/validate" + token, Boolean.class);
        if (isLablePresent){
            Optional<LableModel> isLableAvailable = lableRepository.findById(lableId);
            if (isLableAvailable.isPresent()){
                lableRepository.delete(isLableAvailable.get());
                return new ResponseClass(200, "Sucessfully", isLableAvailable.get());
            }
            throw new LableNotFoundException(400, "Lable not found");
        }
        throw new LableNotFoundException(400, "token is wrong");
    }

    @Override
    public ResponseClass addLabel(long labelId, String token, List<Long> noteId) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-SERVICE:8091/user/validate/" + token, Boolean.class);
        if (isUserPresent){
            List<NoteModel> notesModels = new ArrayList<>();
            noteId.stream().forEach(note ->{
                Optional<NoteModel> isNotePresent = noteRepository.findById(note);
                if (isNotePresent.isPresent()){
                    notesModels.add(isNotePresent.get());
                }
            });
            Optional<LableModel> isLabelPresent = lableRepository.findById(labelId);
            if (isLabelPresent.isPresent()){
                isLabelPresent.get().setList(notesModels);
                lableRepository.save(isLabelPresent.get());
                return new ResponseClass(200, "success", isLabelPresent.get());
            }
            throw new LableNotFoundException(400, "not found");
        }
        throw new LableNotFoundException(400, "Token is wrong");
    }

}
