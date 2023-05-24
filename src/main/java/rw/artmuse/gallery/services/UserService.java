package rw.artmuse.gallery.services;

import rw.artmuse.gallery.model.User;

import java.util.List;


public interface UserService {

    void saveUser(User user);


    List<Object> isUserPresent(User user);

    User findUserByEmail(String email);

}