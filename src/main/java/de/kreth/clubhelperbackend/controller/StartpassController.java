package de.kreth.clubhelperbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.Startpass;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/startpass")
public class StartpassController extends AbstractController<Startpass> {

	@Autowired
	public StartpassController() {
		super(Startpass.class);
	}

	@Override
//	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<Startpass> getByParentId(@PathVariable("id") long id) {
		return dao.findByPersonId(id);
	}
}
