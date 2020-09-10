package de.kreth.clubhelperbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.Contact;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/contact")
public class ContactController extends AbstractController<Contact> {

	@Autowired
	public ContactController() {
		super(Contact.class);
	}

}
