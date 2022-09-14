package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.LableDTO;
import com.bridgelabz.note.exception.LableNotFoundException;
import com.bridgelabz.note.model.LableModel;
import com.bridgelabz.note.repository.LableRepository;
import com.bridgelabz.note.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
                isLableAvailable.get().setUserId(lableDTO.getUserId());
                isLableAvailable.get().setNoteId(lableDTO.getNoteId());
                isLableAvailable.get().setEmailId(lableDTO.getEmailId());
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
                lableRepository.delete(isLableAvailable);
                return new ResponseClass(200, "Sucessfully", isLableAvailable.get());
            }
            throw new LableNotFoundException(400, "Lable not found");
        }
        throw new LableNotFoundException(400, "token is wrong");
    }


}
