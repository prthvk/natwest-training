package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently
 * provide any additional behavior over the @Component annotation, but it's a good idea
 * to use @Service over @Component in service-layer classes because it specifies intent
 * better. Additionally, tool support and additional behavior might rely on it in the
 * future.
 * */
@Service
public class NoteServiceImpl implements NoteService{

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a new note.
	 */
	private NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) {
		boolean value = false;
		NoteUser noteUser = new NoteUser();
		List<Note> noteList = new ArrayList<>();
		noteList.add(note);
		noteUser.setNotes(noteList);
		noteUser.setUserId(note.getNoteCreatedBy());
		if (noteRepository.insert(noteUser) != null) {
			value = true;
		}
		return value;
	}

	/* This method should be used to delete an existing note. */


	public boolean deleteNote(String userId, int noteId) {
		NoteUser noteUser = noteRepository.findById(userId).get();
		return noteUser.getNotes().removeIf(note -> note.getNoteId() == noteId);
	}

	/* This method should be used to delete all notes with specific userId. */


	public boolean deleteAllNotes(String userId) {
		NoteUser noteUser = noteRepository.findById(userId).get();
		List<Note> empty = new ArrayList<>();
		noteUser.setNotes(empty);
		noteRepository.save(noteUser);
		return true;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note, int id, String userId) throws NoteNotFoundExeption {
		int index;
		NoteUser noteUser;
		try {
			Optional noteUserOptional = noteRepository.findById(userId);
			noteUser = (NoteUser) noteUserOptional.get();
			Optional noteOptional = noteUser.getNotes().stream().filter(noteobj -> noteobj.getNoteId() == id).findAny();
			index = noteUser.getNotes().indexOf(noteOptional.get());
		} catch (NoSuchElementException e) {
			throw new NoteNotFoundExeption("Note not found");
		}
		return noteUser.getNotes().set(index, note);
	}

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
	public Note getNoteByNoteId(String userId, int noteId) throws NoteNotFoundExeption {
		Optional noteOptional;
		try {
			Optional noteUserOptional = noteRepository.findById(userId);
			NoteUser noteUser = (NoteUser) noteUserOptional.get();
			noteOptional = noteUser.getNotes().stream().filter(noteobj -> noteobj.getNoteId() == noteId).findAny();
		} catch (NoSuchElementException e) {
			throw new NoteNotFoundExeption("Note not found");
		}
		return (Note) noteOptional.get();
	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Note> getAllNoteByUserId(String userId) {
		return noteRepository.findById(userId).get().getNotes();
	}

}
