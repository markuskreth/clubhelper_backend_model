package de.kreth.clubhelperbackend.controller.abstr;

import static de.kreth.clubhelperbackend.utils.BoolUtils.not;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import de.kreth.clubhelper.model.dao.ClubhelperDao;
import de.kreth.clubhelper.model.data.BaseEntity;

/**
 * Default Controller implementing all functionality for all {@link Data} types.
 * 
 * @param <T>
 *            Data type
 */

//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'STAFF')")
public abstract class AbstractController<T extends BaseEntity>
		implements
			ClubController<T> {

   @Autowired
	protected ClubhelperDao<T> dao;
   private Class<T> elementClass;

	public AbstractController(Class<T> element) {
		super();
		this.elementClass = element;
	}

	@Override
	@GetMapping(value = "/{id}", produces = "application/json")
	public T getById(@PathVariable("id") long id) {
		return dao.findById(id).orElseThrow(() -> new IllegalArgumentException(elementClass.getName() + " with id=" + id + " not found"));
	}

	protected List<T> iterableToList(Iterable<T> in) {
	   List<T> result = new ArrayList<>();
	   in.forEach(result::add);
	   return result;
	}
	
	@Override
	@GetMapping(value = {"/",
			""}, produces = "application/json")
	public List<T> getAll() {
		Iterable<T> findAll = dao.findAll();
		List<T> result = new ArrayList<>();
		findAll.forEach(result::add);
      return result;
	}

	@Override
	@GetMapping(value = "/for/{id}", produces = "application/json")
	public List<T> getByParentId(@PathVariable("id") long id) {
	   return dao.findByPersonId(id);
	}

	@Override
	@GetMapping(value = "/changed/{changed}", produces = "application/json")
	public List<T> getChangedSince(@PathVariable("changed") long changed) {
	   return dao.findByChangedGreaterThan(new Date(changed));
	}

	@Override
	@PutMapping(value = "/{id}", produces = "application/json")
	public T put(@PathVariable("id") long id, @RequestBody T toUpdate) {

		Date now = new Date();
		Date created = toUpdate.getCreated();
		Date changed = null;

		if (toUpdate.getChanged() != null) {
			changed = toUpdate.getChanged();
			long minutes = MINUTES.between(created.toInstant(), changed.toInstant());
			if (minutes < 1) {
				toUpdate.setChanged(now);
			}
		} else {
			toUpdate.setChanged(now);
		}

		dao.save(toUpdate);
		return toUpdate;
	}

	@Override
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<T> delete(@PathVariable("id") long id) {
		T byId = getById(id);
		if (not(byId.isDeleted())) {
			dao.delete(byId);
		}
		return ResponseEntity.ok(getById(id));
	}

	@Override
	@PostMapping(value = "/", produces = "application/json")
	public T post(@RequestBody T toCreate) {
		return post(-1L, toCreate);
	}

	@Override
	@PostMapping(value = "/{id}", produces = "application/json")
	public T post(@PathVariable("id") Long id, @RequestBody T toCreate) {
		if (id == null) {
			id = -1L;
		}
		toCreate.setId(id);
		Date now = new Date();

		toCreate.setChanged(now);

		if (toCreate.getCreated() == null
				|| toCreate.getCreated().getTime() == 0) {
			toCreate.setCreated(now);
		}

		if (toCreate.getId() < 0) {
			return dao.save(toCreate);
		} else {
			T byId = getById(toCreate.getId());
			if (byId != null) {
			   toCreate.setDeleted(null);
            return dao.save(toCreate);
			} else {
				return dao.save(toCreate);
			}
		}
	}

}