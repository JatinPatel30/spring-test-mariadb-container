package com.jatin.mysqltestcontainerdemo.functional;

import com.jatin.mysqltestcontainerdemo.configuration.TestMySqlContainer;
import com.jatin.mysqltestcontainerdemo.repository.PersonRepository;
import com.jatin.mysqltestcontainerdemo.repository.dto.Person;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jatin.mysqltestcontainerdemo.configuration.TestMySqlContainer.getInstance;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTestEmbedded {

    @ClassRule
    public static TestMySqlContainer testMySqlContainer = getInstance();

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldSaveUser() {
        Person person = new Person(null, "Jatin", "password");
        Person savedUser = personRepository.save(person);
        assertThat(person).usingRecursiveComparison().ignoringFields("id").isEqualTo(savedUser);
    }

}