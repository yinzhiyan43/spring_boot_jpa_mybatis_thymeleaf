package com.wonzopein.demo.controller;

import com.wonzopein.demo.domain.User;
import com.wonzopein.demo.mybatis.mapper.UserMapper;
import com.wonzopein.demo.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by seunghyeonoh on 15. 8. 4..
 */
@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    void init(){
        userRepository.save( new User("1","user1"));
        userRepository.save( new User("2","user2"));
        userRepository.save( new User("3","user3"));
        userRepository.save( new User("4","user4"));
        userRepository.save( new User("5","user5"));
    }

    @RequestMapping("/")
    String home(Model model){

        //  Mybatis Mapper Call
        log.debug(userMapper.getUser());


        //  JPA
        List<User> users = userRepository.findAll();

        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                log.debug(user.getName());
            }
        });


        //  set model
        model.addAttribute("users", users);


        return "index";
    }

}
