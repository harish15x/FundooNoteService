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
    private String color;
    private String emailid;
    private long userId;
    private boolean pin;
    private boolean trash;
    private boolean isArchieve;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private LocalDateTime remindertime;

    public NoteModel(NoteDTO noteDTO){

        this.title = noteDTO.getTitle();
        this.description = noteDTO.getDescription();
        this.emailid = noteDTO.getEmailid();
        this.color = noteDTO.getColor();

    }

    public NoteModel() {

    }

}
