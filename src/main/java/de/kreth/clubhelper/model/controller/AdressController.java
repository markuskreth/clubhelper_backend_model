package de.kreth.clubhelper.model.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelper.model.dao.AdressDao;

@RestController
@RequestMapping("/adress")
@PreAuthorize("isAuthenticated()")
public class AdressController extends AbstractController<Adress, AdressDao> {

    public AdressController() {
	super(Adress.class);
    }

}
