package de.kreth.clubhelperbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.Relative;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/relative")
public class RelativeController extends AbstractController<Relative> {

	@Autowired
	public RelativeController() {
		super(Relative.class);
	}
//
//	@Override
//	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET)
//	public List<Relative> getByParentId(@PathVariable("id") long id) {
//		StringBuilder whereClause = new StringBuilder("person1=");
//		whereClause.append(id).append(" OR person2=").append(id);
//		return dao.getByWhere(whereClause.toString());
//	}

}
