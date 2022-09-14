package com.bridgelabz.note.model;

import com.bridgelabz.note.dto.LableDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lable")
public class LableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String labelName;
    private Long userId;
    private Long noteId;
    private String emailId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    public LableModel(LableDTO lableDTO){
        this.labelName = lableDTO.getLabelName();
        this.userId = lableDTO.getUserId();
        this.noteId = lableDTO.getNoteId();
        this.emailId = lableDTO.getEmailId();
    }

    public LableModel() {

    }

}
