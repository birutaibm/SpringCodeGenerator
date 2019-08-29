package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
  private String root = "assabi";
  private List<Template> templates = new ArrayList<>();

  public Map<String, String> mapFor(String Name) {
    Map<String, String> map = new HashMap<>();
    map.put("Name", Name);
    map.put("name", Name.toLowerCase());
    map.put("entityFull", root+"."+Template.Entity.subPack+"."+Name);
    map.put("daoFull", root+"."+Template.DAO.subPack+"."+Name+"Repository");
    map.put("resourceFull", root+"."+Template.Resource.subPack+"."+Name+"ResourceAssembler");
    map.put("ctrlFull", root+"."+Template.CTRL.subPack+"."+Name+"Ctrl");
    map.put("root", root);
    return map;
  }
  public static void main(String[] args) {
    Test self = new Test();
    self.templates.add(Template.Resource);
    self.templates.add(Template.DAO);
    self.templates.add(Template.CTRL);
    self.templates.add(Template.Entity);
    self.templates.stream()
      .map(temp -> temp.toString(self.mapFor("Stakeholder")))
      .forEach(System.out::println);
  }
}