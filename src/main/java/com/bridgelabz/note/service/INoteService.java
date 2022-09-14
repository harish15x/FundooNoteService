package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.model.NoteModel;
import com.bridgelabz.note.util.ResponseClass;

import java.util.List;

public interface INoteService {

    ResponseClass addNote(String token, NoteDTO noteDTO);

    ResponseClass updateNote(String token, NoteDTO noteDTO, Long notId);

    List<NoteModel> getNoteData(String token);

    ResponseClass deleteNote(long noteId, String token);

    ResponseClass readNotesById(String token, long noteId);

    ResponseClass restoreNotes(long noteId, String token);

    ResponseClass addColour(long noteId, String colour, String token);

    ResponseClass pin(long noteId, String token);

    ResponseClass archiveNote(long noteId, String token);
}
