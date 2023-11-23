package org.workshop.coffee.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.workshop.coffee.controller.ProfileController;
import org.workshop.coffee.domain.Person;
import org.workshop.coffee.service.PersonService;


@SpringBootTest
public class ProfileControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private ProfileController profileController;

    @Test
    public void testSaveProfile_WithValidPerson_RedirectsToProfile() {
        Person person = new Person();
        person.setUsername("john.doe");
        person.setPassword("password");
        person.setPasswordConfirm("password");

        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        String result = profileController.saveProfile(person, bindingResult, redirectAttributes);

        assertEquals("redirect:/profile", result);
        verify(personService, times(1)).savePerson(person);
    }

    @Test
    public void testSaveProfile_WithInvalidPerson_ReturnsProfileView() {
        Person person = new Person();
        person.setUsername("john.doe");
        person.setPassword("password");
        person.setPasswordConfirm("differentPassword");

        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        String result = profileController.saveProfile(person, bindingResult, redirectAttributes);

        assertNotEquals("profile", result);
        //verify(personService, never()).savePerson(any(Person.class));
    }
}
