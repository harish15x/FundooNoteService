package com.bridgelabz.note.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteDTO {

    private String title;
    private String description;
    private String emailid;
    private String color;

}
