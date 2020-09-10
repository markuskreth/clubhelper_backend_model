package de.kreth.clubhelperbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.GroupDef;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/group")
public class GroupController extends AbstractController<GroupDef> {

	@Autowired()
	public GroupController() {
		super(GroupDef.class);
	}

}
