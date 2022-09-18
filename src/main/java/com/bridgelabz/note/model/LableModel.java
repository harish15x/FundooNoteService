package com.bridgelabz.note.model;

import com.bridgelabz.note.dto.LableDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lableNote", joinColumns = {@JoinColumn(name = "labelId")}, inverseJoinColumns = {@JoinColumn(name = "noteId")})
    @JsonBackReference
    private List<NoteModel> list;

    public LableModel(LableDTO lableDTO){
        this.labelName = lableDTO.getLabelName();
    }

    public LableModel() {

    }

}
