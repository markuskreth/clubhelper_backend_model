package de.kreth.clubhelper.model.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the adress database table.
 * 
 */
@Entity
@Table(name = "adress")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "Adress.findAll", query = "SELECT a FROM Adress a")
public class Adress extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8216273166570667412L;

    private String adress1;

    private String adress2;

    private String city;

    private String plz;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public String getAdress1() {
	return adress1;
    }

    public void setAdress1(String adress1) {
	this.adress1 = adress1;
    }

    public String getAdress2() {
	return adress2;
    }

    public void setAdress2(String adress2) {
	this.adress2 = adress2;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getPlz() {
	return plz;
    }

    public void setPlz(String plz) {
	this.plz = plz;
    }

    @Override
    public int hashCode() {
	final int prime = 109;
	int result = super.hashCode();
	result = prime * result;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	return super.equals(obj);
    }

    @Override
    public String toString() {
	return "Adress [adress1=" + adress1 + ", adress2=" + adress2 + ", plz=" + plz + ", city=" + city + "]";
    }

}
