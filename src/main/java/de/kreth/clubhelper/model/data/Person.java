package de.kreth.clubhelper.model.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The persistent class for the person database table.
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person extends BaseEntity implements Serializable
{
   public static final String SESSION_LOGIN = "SESSION_LOGIN_USER";
   public static final String QUERY_FINDALL = "Person.findAll";
   public static final String QUERY_FINDLOGIN = "Person.findLogin";
   private static final long serialVersionUID = -8361264400619997123L;
   @Basic
   private LocalDate birth;
   private String prename;
   private String surname;
   private String username;
   private String password;
   private Integer gender;
   @ManyToMany
   @JoinTable(name = "persongroup", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
   private List<GroupDef> groups;

   public Gender getGender()
   {
      if (gender == null) {
         return null;
      }
      return Gender.valueOf(gender);
   }

   public LocalDate getBirth()
   {
      return birth;
   }

   public void setBirth(LocalDate birth)
   {
      this.birth = birth;
   }

   public String getPrename()
   {
      return prename;
   }

   public void setPrename(String prename)
   {
      this.prename = prename;
   }

   public String getSurname()
   {
      return surname;
   }

   public void setSurname(String surname)
   {
      this.surname = surname;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public void setGender(Integer gender)
   {
      this.gender = gender;
   }

   public boolean isMember(GroupDef group)
   {
      return groups != null && groups.contains(group);
   }

   @Override
   public int hashCode()
   {
      final int prime = 59;
      int result = super.hashCode();
      result = prime * result;
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      Person other = (Person) obj;
      if (surname == null) {
         if (other.surname != null)
            return false;
      }
      else if (!surname.equals(other.surname))
         return false;
      return true;
   }

   @Override
   public String toString()
   {
      return "Person [id=" + getId() + ", prename=" + prename + ", surname=" + surname + "]";
   }
}
