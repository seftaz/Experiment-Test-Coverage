package com.unittest.codecoverage;

import com.unittest.codecoverage.exceptions.PersonException;
import com.unittest.codecoverage.models.Gender;
import com.unittest.codecoverage.models.Person;
import com.unittest.codecoverage.repositories.PersonRepository;
import com.unittest.codecoverage.services.impl.PersonServiceImpl;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
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

        Assert.isNull(service.get("Ali"));

    }

    @Test
    void checkRepository() {
        PersonRepository repository = new PersonRepository();

        Person person1 = new Person();
        person1.setName("Ali");
        person1.setGender(Gender.M);
        person1.setAge(23);

        Person person2 = new Person();
        person2.setName("Mamad");
        person2.setGender(Gender.M);
        person2.setAge(33);

        repository.insert(person1);
        repository.insert(person2);

        try {
            repository.insert(null);
        } catch (NullPointerException e) {
            Assert.isTrue(e.getMessage().equals("person can't be null"));
        }

        try {
            repository.update(null);
        } catch (NullPointerException e) {
            Assert.isTrue(e.getMessage().equals("person can't be null"));
        }

        try {
            repository.delete(null);
        } catch (NullPointerException e) {
            Assert.isTrue(e.getMessage().equals("name can't be null"));
        }

        Assert.isTrue(repository.get("Ali").getAge() == 23);
    }

}
