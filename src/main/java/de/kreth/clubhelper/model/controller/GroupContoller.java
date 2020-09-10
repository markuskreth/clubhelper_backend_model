package de.kreth.clubhelper.model.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import de.kreth.clubhelper.model.dao.GroupDao;
import de.kreth.clubhelper.model.data.GroupDef;

@RestController
@RequestMapping("/group")
@PreAuthorize("isAuthenticated()")
public class GroupContoller
{
   @Autowired
   private GroupDao groupDao;
   
   @GetMapping
   public List<GroupDef> allGroups() {
      return StreamSupport.stream(groupDao.findAll().spliterator(), false)
         .collect(Collectors.toList());
   }

   @GetMapping("/{id}")
   public GroupDef getById(@PathVariable("id") long id) {
      Supplier<HttpClientErrorException> supplier = () -> createNotFoundForId(id);
      return groupDao.findById(id).orElseThrow(supplier);
   }

   private HttpClientErrorException createNotFoundForId(long id)
   {
      HttpHeaders headers = new HttpHeaders();
      byte[] body = new byte[0];
      Charset charset = Charset.forName("UTF-8");
      HttpClientErrorException ex = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Group not found by id=" + id, headers, body, charset);
      return ex;
   }
}
