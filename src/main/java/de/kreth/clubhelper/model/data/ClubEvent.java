package de.kreth.clubhelper.model.data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.kreth.clubhelper.model.data.CompetitionType.Type;

/**
 * Calendar Event item corresponding to google calendar events.
 * 
 * @author markus
 *
 */
@Entity
@Table(name = "clubevent")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "ClubEvent.findAll", query = "SELECT c FROM ClubEvent c")
public class ClubEvent extends BaseEntity implements EntityAccessor {

	private String caption;
	private String description;
	private ZonedDateTime end;
	private ZonedDateTime start;

	private boolean isAllDay;

	private String location;

	private String iCalUID;

	private String organizerDisplayName;

	private boolean deleted;

	@ManyToMany
	private Set<Person> persons;

	@OneToMany
	private Set<Altersgruppe> altersgruppen;

	@OneToOne
	@JoinColumn(name = "id", nullable = true)
	private CompetitionType competitionType;

	public String getCaption() {
		return caption;
	}

	public String getDescription() {

		return description;
	}

	public ZonedDateTime getEnd() {
		return end;
	}

	public ZonedDateTime getStart() {
		return start;
	}

	public boolean isAllDay() {
		return isAllDay;
	}

	public Type getType() {
		if (competitionType != null) {
			return competitionType.getType();
		} else {
			return null;
		}
	}

	public void setType(Type competitionType) {
		if (this.competitionType == null) {
			this.competitionType = new CompetitionType();
			this.competitionType.setClubEvent(this);
		}
		this.competitionType.setType(competitionType);
	}
//
//	public void add(Person p) {
//		if (this.persons == null) {
//			this.persons = new HashSet<>();
//		}
//		if (this.persons.contains(p)) {
//			return;
//		}
//		this.persons.add(p);
//		p.add(this);
//	}
//
//	public void remove(Person person) {
//		persons.remove(person);
//		person.remove(this);
//	}

	@Transient
	public String toDisplayString() {
		return "ClubEvent [Caption=" + getCaption() + ", Start=" + getStart() + ", location=" + location + "]";
	}

	@Override
	public String toString() {
		return "ClubEvent [id=" + getId() + ", getCaption()=" + getCaption() + ", iCalUID=" + iCalUID + ", location="
				+ location + ", organizerDisplayName=" + organizerDisplayName + ", getDescription()=" + getDescription()
				+ ", getEnd()=" + getEnd() + ", getStart()=" + getStart() + ", isAllDay()=" + isAllDay() + "]";
	}

	public static ZonedDateTime toZoned(Date parse) {
		if (parse != null) {
			Instant instant = parse.toInstant();
			return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
		} else {
			return null;
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getiCalUID() {
		return iCalUID;
	}

	public void setiCalUID(String iCalUID) {
		this.iCalUID = iCalUID;
	}

	public String getOrganizerDisplayName() {
		return organizerDisplayName;
	}

	public void setOrganizerDisplayName(String organizerDisplayName) {
		this.organizerDisplayName = organizerDisplayName;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Set<Altersgruppe> getAltersgruppen() {
		return altersgruppen;
	}

	public void setAltersgruppen(Set<Altersgruppe> altersgruppen) {
		this.altersgruppen = altersgruppen;
	}

	public CompetitionType getCompetitionType() {
		return competitionType;
	}

	public void setCompetitionType(CompetitionType competitionType) {
		this.competitionType = competitionType;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((competitionType == null) ? 0 : competitionType.hashCode());
		result = prime * result + ((iCalUID == null) ? 0 : iCalUID.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ClubEvent other = (ClubEvent) obj;
		if (competitionType == null) {
			if (other.competitionType != null) {
				return false;
			}
		} else if (!competitionType.equals(other.competitionType)) {
			return false;
		}
		if (iCalUID == null) {
			if (other.iCalUID != null) {
				return false;
			}
		} else if (!iCalUID.equals(other.iCalUID)) {
			return false;
		}
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		return true;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

}
