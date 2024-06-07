package com.unittest.codecoverage;

import com.unittest.codecoverage.exceptions.BehaviorException;
import com.unittest.codecoverage.exceptions.PersonException;
import com.unittest.codecoverage.models.*;
import com.unittest.codecoverage.repositories.PersonRepository;
import com.unittest.codecoverage.services.impl.PersonServiceImpl;
import com.unittest.codecoverage.services.impl.TrafficBehaviorServiceImpl;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.Assert.*;

//@SpringBootTest
class CodecoverageApplicationTests {

    @Test
    void checkingTrafficService() {
        Footpassenger footpassenger = new Footpassenger();
        footpassenger.setCrossedTrafficLigth(TrafficLigth.RED);
        footpassenger.setLookedToTheLeft(false);
        footpassenger.setLookedToTheRight(false);
        footpassenger.setCrossedTheRoad(false);

        Traffic traffic = new Traffic();
        traffic.setCurrentTrafficLight(TrafficLigth.GREEN);
        traffic.setIntenseCarTraffic(true);
        traffic.setStreetDirectionFlow(StreetDirectionFlow.TWO_WAY);

        TrafficBehaviorServiceImpl trafficBehaviorService = new TrafficBehaviorServiceImpl();
        trafficBehaviorService.footpassengerCrossTheStreet(traffic, footpassenger);
        footpassenger.setCrossedTheRoad(true);
        try {
            trafficBehaviorService.footpassengerCrossTheStreet(traffic, footpassenger);
        } catch (BehaviorException e){
            Assert.isTrue(e.getMessage().equals("You should'nt do that, reckless person"));
        }

        footpassenger.setCrossedTrafficLigth(TrafficLigth.GREEN);
        traffic.setCurrentTrafficLight(TrafficLigth.RED);

        try {
            trafficBehaviorService.footpassengerCrossTheStreet(traffic, footpassenger);
        } catch (BehaviorException e){
            Assert.isTrue(e.getMessage().equals("You should be more careful"));
        }
    }







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
