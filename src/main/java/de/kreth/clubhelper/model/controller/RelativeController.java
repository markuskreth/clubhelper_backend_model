package de.kreth.clubhelper.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kreth.clubhelper.model.dao.RelativeDao;
import de.kreth.clubhelper.model.data.Relative;

@RestController
@RequestMapping("/relative")
public class RelativeController extends AbstractController<Relative, RelativeDao> {

    @Autowired
    private RelativeDao relativeDao;

    public RelativeController() {
	super(Relative.class);
    }

    @Override
    public List<Relative> getByParentId(long id) {
	return relativeDao.findByPerson1ORPerson2(id);
    }
}
