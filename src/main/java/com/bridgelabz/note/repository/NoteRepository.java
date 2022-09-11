package com.bridgelabz.note.repository;

import com.bridgelabz.note.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteModel, Long> {
}
