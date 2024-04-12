package com.blood.rescue.service;

import com.blood.rescue.dto.Event;
import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.User;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;

    private final UserService userService;

    public NotificationService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public void notifyAllUsers(Event event) {
        List<User> userList = userService.findDonors(event);
        String value = event.getBloodGroup().getValue();
        String address = event.getAddress();
        String mobileNo = event.getMobileNo();
        for (User user :userList){
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmailId());
            mailMessage.setSubject("Its time to save a life");
            String text = String.format("There is a urgent need of %s type required at %s. ",value,address);
            String contact = String.format("Please contact %s",mobileNo);
            mailMessage.setText(text+"\n"+contact);
            javaMailSender.send(mailMessage);
        }
    }

    public void notifyRecipient(Event event) {

    }
}
