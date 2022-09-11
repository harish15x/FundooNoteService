package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.util.ResponseClass;

import java.util.List;

public interface INoteService {

    ResponseClass addNote(String token, NoteDTO noteDTO);

    ResponseClass updateNote(String token, NoteDTO noteDTO, Long id);

    List<NoteModel> getNoteData(String token);

    ResponseClass deleteNote(long id, String token);
}
