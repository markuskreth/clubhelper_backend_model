package de.kreth.clubhelper.model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import de.kreth.clubhelper.model.dao.PersonDao;
import de.kreth.clubhelper.model.data.Gender;
import de.kreth.clubhelper.model.data.Person;

@WebMvcTest(excludeAutoConfiguration = {
	DataSourceAutoConfiguration.class,
	JdbcRepositoriesAutoConfiguration.class,
	DataSourceTransactionManagerAutoConfiguration.class,
	JdbcTemplateAutoConfiguration.class,
	SecurityAutoConfiguration.class
})
@Disabled
class PersonControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    PersonDao personDao;

    private Person p1;
    private Person p2;

    @BeforeEach
    void initMocks() {
	p1 = new Person();
	p1.setId(1);
	p1.setPrename("prename");
	p1.setSurname("surname");
	p1.setBirth(LocalDate.of(2000, 1, 1));
	p1.setGender(Gender.MALE.getId());
	p2 = new Person();
	p2.setId(1);
	p2.setPrename("prename");
	p2.setSurname("surname");
	p2.setBirth(LocalDate.of(2000, 1, 1));
	p2.setGender(Gender.MALE.getId());
	when(personDao.findAll()).thenReturn(Arrays.asList(p1, p2));
	when(personDao.findById(1L)).thenReturn(Optional.of(p1));
	when(personDao.findById(2L)).thenReturn(Optional.of(p2));
    }

    @Test
    void callAllPersons() throws Exception {
	String jsonListOfPersons = "[{\"id\":1,\"changed\":null,\"created\":null,\"deleted\":null,\"birth\":\"2000-01-01\",\"prename\":\"prename\",\"surname\":\"surname\",\"username\":null,\"password\":null,\"gender\":\"MALE\"},{\"id\":1,\"changed\":null,\"created\":null,\"deleted\":null,\"birth\":\"2000-01-01\",\"prename\":\"prename\",\"surname\":\"surname\",\"username\":null,\"password\":null,\"gender\":\"MALE\"}]";
	mvc.perform(get("/person").accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
		.andExpect(status().isOk())
		.andExpect(content().string(jsonListOfPersons));
    }

    @Test
    void callPerson1() throws Exception {
	String jsonListOfPersons = "{\"id\":1,\"changed\":null,\"created\":null,\"deleted\":null,\"birth\":\"2000-01-01\",\"prename\":\"prename\",\"surname\":\"surname\",\"username\":null,\"password\":null,\"gender\":\"MALE\"}";
	mvc.perform(get("/person/1").accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
		.andExpect(status().isOk())
		.andExpect(content().string(jsonListOfPersons));
    }
}
