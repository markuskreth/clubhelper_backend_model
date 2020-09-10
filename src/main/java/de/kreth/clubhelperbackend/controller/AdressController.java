package de.kreth.clubhelperbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.Adress;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/adress")
public class AdressController extends AbstractController<Adress> {

	@Autowired
	public AdressController() {
		super(Adress.class);
	}

	@Override
	public List<Adress> getByParentId(long id) {
		return new ArrayList<Adress>();
	}

}
