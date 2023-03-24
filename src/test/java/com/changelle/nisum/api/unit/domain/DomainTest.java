package com.changelle.nisum.api.unit.domain;

import com.changelle.nisum.api.domain.model.Phone;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.domain.repository.PhoneRepository;
import com.changelle.nisum.api.domain.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class DomainTest {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private UserRepository userRepository;

    private Phone phone;
    private User user;

    @BeforeEach
    void setup() {
        phone = Phone.builder().id(1).countryCode("22").cityCode("22").number("121").build();
        user = User.builder()
                .token("aa")
                .id(1)
                .modified(LocalDateTime.now())
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .name("zyx")
                .email("aaaa@gmail.com")
                .isActive(true)
                .password("aaaaa")
                .phones(List.of(phone))
                .build();
        phone.setUser(user);
    }

    /**
     * test Phone
     */
    @Test
    void checkValueDomainUserAndPhone() {
        userRepository.save(user);
        phoneRepository.save(phone);

        Optional<User> optionalUser = userRepository.findById(1);
        Optional<Phone> optionalPhone = phoneRepository.findById(1);

        User user = optionalUser.get();
        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getLastLogin());

        Phone phone = optionalPhone.get();
        Assert.assertNotNull(phone.getId());
        Assert.assertNotNull(phone.getCityCode());
        Assert.assertNotNull(phone.getCountryCode());
        Assert.assertNotNull(phone.getNumber());
        Assert.assertNotNull(phone.getUser());

    }
}
