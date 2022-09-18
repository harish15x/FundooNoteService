package com.bridgelabz.note.model;

import com.bridgelabz.note.dto.NoteDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private String remindertime;

    @ElementCollection(targetClass = String.class)
    private List<String> collabrators;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "labelNote", joinColumns = {@JoinColumn(name = "noteId")},inverseJoinColumns = {@JoinColumn(name = "labelId")})
    @JsonBackReference
    @JsonIgnore
    private List<LableModel> list;




    public NoteModel(NoteDTO noteDTO){

        this.title = noteDTO.getTitle();
        this.description = noteDTO.getDescription();
        this.emailid = noteDTO.getEmailid();
        this.color = noteDTO.getColor();

    }

    public NoteModel() {

    }

}
