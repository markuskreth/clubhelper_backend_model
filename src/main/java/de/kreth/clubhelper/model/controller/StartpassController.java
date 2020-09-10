package de.kreth.clubhelper.model.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.dao.StartpassDao;
import de.kreth.clubhelper.model.data.Startpass;

@RestController
@RequestMapping("/startpass")
public class StartpassController extends AbstractController<Startpass, StartpassDao> {

	public StartpassController() {
		super(Startpass.class);
	}

}
