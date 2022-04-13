package de.kreth.clubhelper.model.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.entity.Contact;
import de.kreth.clubhelper.model.dao.ContactDao;

@RestController
@RequestMapping("/contact")
@PreAuthorize("isAuthenticated()")
public class ContactController extends AbstractController<Contact, ContactDao> {

    public ContactController() {
	super(Contact.class);
    }

}
