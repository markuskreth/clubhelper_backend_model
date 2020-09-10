package de.kreth.clubhelperbackend.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.DeletedEntry;
import de.kreth.clubhelperbackend.controller.abstr.AbstractController;

@RestController
@RequestMapping("/deletedentries")
public class DeletedEntriesController extends AbstractController<DeletedEntry> {

	@Autowired
	public DeletedEntriesController() {
		super(DeletedEntry.class);
	}

	@Override
	public List<DeletedEntry> getByParentId(long id) {
		return Collections.emptyList();
	}
}
