package de.kreth.clubhelper.model.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.dao.ContactDao;
import de.kreth.clubhelper.model.data.Contact;

@RestController
@RequestMapping("/contact")
@PreAuthorize("isAuthenticated()")
public class ContactController extends AbstractPersonRelatedController<Contact, ContactDao> {

    public ContactController() {
	super(Contact.class);
    }

}
