package com.solomon.user.service;

import com.solomon.user.VO.Department;
import com.solomon.user.VO.ResponseTemplateVO;
import com.solomon.user.entity.User;
import com.solomon.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Object addUser(User user) {
        String firstname = user.getFirstName().toLowerCase(Locale.ROOT);
        String lastname = user.getLastName().toLowerCase(Locale.ROOT);

        if ((firstname != null && firstname.length() != 0) && (lastname != null && lastname.length() != 0)) {
            user.setEmail(firstname + "." + lastname + "@email.com");
            return userRepository.save(user);
        }
        else if (firstname == null || firstname.length() == 0){
            return "First name can't be empty";
        }
        else{
            return "Last name can't be empty";
        }

    }


    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of userRepository!");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        // Rest call to Department microservice to get department
        //Before configuring the API GATEWAY SERVICE, used this url "http://localhost:9001/departments/"
        //After API GATEWAY SERVICE is configured, used the microservice name url
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }

    public List<User> saveAllUsers(List<User> userList) {

        userList.forEach((user) -> {
            String firstname = user.getFirstName().toLowerCase(Locale.ROOT);
            String lastname = user.getLastName().toLowerCase(Locale.ROOT);


            if ((firstname != null && firstname.length() != 0) && (lastname != null && lastname.length() != 0)) {
                user.setEmail(firstname + "." + lastname + "@email.com");
            }

        });
        return userRepository.saveAll(userList);
    }
}
