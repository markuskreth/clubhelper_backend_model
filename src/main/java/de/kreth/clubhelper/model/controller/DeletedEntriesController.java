package de.kreth.clubhelper.model.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.data.DeletedEntry;
import de.kreth.clubhelper.model.dao.DeletedEntriesDao;

@RestController
@RequestMapping("/deletedentries")
public class DeletedEntriesController extends AbstractController<DeletedEntry, DeletedEntriesDao> {

	@Autowired
	public DeletedEntriesController() {
		super(DeletedEntry.class);
	}

	@Override
	public List<DeletedEntry> getByParentId(long id) {
		return Collections.emptyList();
	}
}
