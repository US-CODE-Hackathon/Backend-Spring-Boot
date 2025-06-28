package com.garliccastle.demo.domain.user.service;

import com.garliccastle.demo.domain.user.entity.Users;
import com.garliccastle.demo.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<Users>  getUsers(){
        List<Users> all = userRepository.findAll();
        return all;
    }
    public Users getUser(Long id){
        return userRepository.findById(id).orElseThrow();
    }

}
