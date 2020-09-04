package de.kreth.clubhelper.model.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the groupDef database table.
 */
@Entity
@Table(name = "groupdef")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = GroupDef.QUERY_FINDALL, query = "SELECT g FROM GroupDef g")
public class GroupDef extends BaseEntity implements Serializable
{
   private static final long serialVersionUID = 1L;
   public final static String QUERY_FINDALL = "GroupDef.findAll";
   
   private String name;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @Override
   public int hashCode()
   {
      final int prime = 53;
      return prime * super.hashCode();
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) {
         return true;
      }
      return super.equals(obj);
   }

   @Override
   public String toString()
   {
      return "GroupDef [id=" + getId() + ", name=" + name + "]";
   }
}
