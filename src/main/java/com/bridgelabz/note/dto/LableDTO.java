package com.bridgelabz.note.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LableDTO {

    private String labelName;
    private Long userId;
    private Long noteId;
    private String emailId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

}
