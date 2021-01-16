package com.stackroute.keepnote.repository;

import com.stackroute.keepnote.model.NoteUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */
@Repository
public interface NoteRepository extends MongoRepository<NoteUser, String> {

}
