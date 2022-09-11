package com.bridgelabz.note.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteDTO {

    private String title;
    private String description;
    private long userId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private boolean trash;
    private boolean isArchieve;
    private boolean pin;
    private String emailid;
    private String color;
    private LocalDateTime remindertime;

}
