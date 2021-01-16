package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.UserAlreadyExistsException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.repository.UserAutheticationRepository;
import org.springframework.beans.factory.annotation.Autowired;

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


public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    /*
     * Autowiring should be implemented for the UserAuthenticationRepository. (Use
     * Constructor-based autowiring) Please note that we should not create any
     * object using the new keyword.
     */

    private UserAutheticationRepository userAutheticationRepository;

    @Autowired
    public UserAuthenticationServiceImpl(UserAutheticationRepository userAutheticationRepository) {
        this.userAutheticationRepository = userAutheticationRepository;
    }

    /*
     * This method should be used to validate a user using userId and password.
     *  Call the corresponding method of Respository interface.
     *
     */
    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
        User user = userAutheticationRepository.findByUserIdAndUserPassword(userId, password);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }


    /*
     * This method should be used to save a new user.Call the corresponding method
     * of Respository interface.
     */

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        Optional optional = userAutheticationRepository.findById(user.getUserId());
        if (optional.isPresent()) {
            throw new UserAlreadyExistsException("User Already exists");
        } else {
            userAutheticationRepository.save(user);
            return true;
        }
    }
}
