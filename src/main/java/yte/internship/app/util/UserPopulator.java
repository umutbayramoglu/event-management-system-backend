package yte.internship.app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yte.internship.app.entity.Event;
import yte.internship.app.entity.User_;
import yte.internship.app.entity.UserEvent;
import yte.internship.app.repository.EventRepository;
import yte.internship.app.repository.UserEventRepo;
import yte.internship.app.security.CustomUserDetailsManager;
import yte.internship.app.entity.Authority;
import yte.internship.app.repository.AuthorityRepository;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserPopulator {
    private final CustomUserDetailsManager customUserDetailsManager;
    private final AuthorityRepository authorityRepository;
    private final EventRepository eventRepository;
    private final UserEventRepo userEventRepo;

    @Transactional
    public void createUser() throws ParseException {
        List<Authority> savedAuthorities = authorityRepository.saveAll(Set.of(new Authority(null, "READ"), new Authority(null, "WRITE")));
        User_ adminUser = new User_(null, "admin", "admin", "Umut Emre","Bayramoglu","uebayramoglu@gmail.com",new Date(),"1.jpg",Set.copyOf(savedAuthorities),new HashSet<>(),new HashSet<>(),null,null);
        User_ normalUser = new User_(null, "user", "user", "Normal","User","uebayramoglu@gmail.com",new Date(),"1.jpg",Set.of(new Authority(null, "READ")),new HashSet<>(),new HashSet<>(),null,null);
        User_ user1 = new User_(null, "u", "u", "Umut Emre","Bayramoglu","uebayramoglu@gmail.com",new Date(),"1.jpg",Set.copyOf(savedAuthorities),new HashSet<>(),new HashSet<>(),null,null);
        User_ user2 = new User_(null, "e", "e", "Emre","User","uebayramoglu@gmail.com",new Date(),"1.jpg",Set.of(new Authority(null, "READ")),new HashSet<>(),new HashSet<>(),null,null);


        Date startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-08-20 00:00:00");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-08-20 02:00:00");
        Event event1 = new Event(null,"Windows Forensics","Explanation","Umut emre",adminUser,null,null,null,List.of(),2,0,true,new Date(),new Date(),"Ankara","Turkish","event2.jpg","active",new Date(),false);
        Event event2 = new Event(null,"Blockchain Eğitimi","Explanation","Umut emre",adminUser,null,null,null,List.of(),5,0,true, startDate,endDate,"Ankara","Turkish","event3.jpg","active",new Date(),false);
        Event event3 = new Event(null,"3ds Max Eğitimi","Explanation","Umut emre",adminUser,null,null,null,List.of(),5,0,true, startDate,endDate,"Ankara","Turkish","event4.jpg","active",new Date(),false);

        event1.setParticipantCount(1);
        event2.setParticipantCount(1);
        event3.setParticipantCount(1);
        adminUser.getCreatedEvents().add(event1);
        adminUser.getCreatedEvents().add(event2);
        adminUser.getCreatedEvents().add(event3);
        UserEvent userEvent = new UserEvent();
        userEvent.setUser(user1);
        userEvent.setEvent(event1);
        userEvent.setEvent(event2);
        userEvent.setEvent(event3);

        customUserDetailsManager.createUser(adminUser);
        customUserDetailsManager.createUser(normalUser);
        customUserDetailsManager.createUser(user1);
        customUserDetailsManager.createUser(user2);
        eventRepository.save(event2);
        eventRepository.save(event1);
        eventRepository.save(event3);
        userEventRepo.save(userEvent);


    }

}
