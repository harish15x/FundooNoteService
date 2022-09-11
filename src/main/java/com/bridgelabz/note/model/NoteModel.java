package com.bridgelabz.note.model;

import com.bridgelabz.note.dto.NoteDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notetable")
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private long userId;
    private boolean trash;
    private boolean isArchieve;
    private boolean pin;
    private String emailid;
    private String color;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private LocalDateTime remindertime;

    public NoteModel(NoteDTO noteDTO){

        this.title = noteDTO.getTitle();
        this.description = noteDTO.getDescription();
        this.userId = noteDTO.getUserId();
        this.trash = noteDTO.isTrash();
        this.isArchieve = noteDTO.isArchieve();
        this.pin = noteDTO.isPin();
        this.emailid = noteDTO.getEmailid();
        this.color = noteDTO.getColor();
        this.registerDate = noteDTO.getRegisterDate();
        this.registerDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.registerDate = LocalDateTime.now();

    }

    public NoteModel() {

    }

}
