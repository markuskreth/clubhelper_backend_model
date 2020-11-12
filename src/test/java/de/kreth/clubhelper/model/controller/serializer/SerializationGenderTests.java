package de.kreth.clubhelper.model.controller.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.kreth.clubhelper.model.data.Gender;
import de.kreth.clubhelper.model.data.Person;

@JsonTest
public class SerializationGenderTests {

    @Autowired
    private ObjectMapper objectMapper;
    private Person person;

    @BeforeEach
    void initTestPerson() {
	person = new Person();
	person.setPrename("Vorname");
	person.setSurname("Nachname");
	person.setBirth(LocalDate.of(2000, 1, 1));
	person.setGender(Gender.FEMALE.getId());
	person.setCreated(LocalDateTime.of(2020, 10, 10, 10, 10, 10));
	person.setChanged(LocalDateTime.of(2020, 10, 10, 10, 10, 10));
    }

    @Test
    void serializeFemale() throws Exception {
	String personJson = objectMapper.writeValueAsString(person);

	int indexOfGender = personJson.indexOf("gender");
	indexOfGender = personJson.indexOf(":", indexOfGender + 1);
	indexOfGender++;
	String genderValue = personJson.substring(indexOfGender, indexOfGender + 1);
	assertEquals("2", genderValue);
    }

    @Test
    void deserializeFemale() throws Exception {
	String json = "{\n" +
		"	\"id\": 0,\n" +
		"	\"changed\": \"2020-10-10T10:10:10\",\n" +
		"	\"created\": \"2020-10-10T10:10:10\",\n" +
		"	\"deleted\": null,\n" +
		"	\"birth\": \"2000-01-01\",\n" +
		"	\"prename\": \"Vorname\",\n" +
		"	\"surname\": \"Nachname\",\n" +
		"	\"gender\": 2,\n" +
		"	\"groups\": null\n" +
		"}";
	Person p = objectMapper.readValue(json, Person.class);

	assertEquals(Gender.FEMALE, p.getGender());
    }
}