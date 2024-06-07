package com.unittest.codecoverage;

import com.unittest.codecoverage.exceptions.PersonException;
import com.unittest.codecoverage.models.Gender;
import com.unittest.codecoverage.models.Person;
import com.unittest.codecoverage.repositories.PersonRepository;
import com.unittest.codecoverage.services.impl.PersonServiceImpl;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.Assert.*;

//@SpringBootTest
class CodecoverageApplicationTests {

    @Test
    void createValidatePerson() {
        Person person1 = new Person();
        person1.setName("Ali");
        person1.setAge(22);

        Person person2 = new Person();
        person2.setGender(Gender.M);
        person2.setAge(33);


        PersonServiceImpl service = new PersonServiceImpl();

        try {
            service.insert(person1);
        } catch (PersonException e) {
            Assert.isTrue(e.getMessage().equals("Gender is required"));
        }

        try {
            service.insert(person2);
        } catch (PersonException e) {
            Assert.isTrue(e.getMessage().equals("Name is required"));
        }

    }

}
