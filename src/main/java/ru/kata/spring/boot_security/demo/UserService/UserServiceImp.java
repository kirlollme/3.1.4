package ru.kata.spring.boot_security.demo.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    private final UserDao userDao;
    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }
    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {

        return userDao.getUsers();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void changeDataUser(Long id, User userAfter) {
        userDao.changeDataUser(id,userAfter);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }
}
