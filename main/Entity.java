package main;

import java.util.HashMap;
import java.util.Map;

public class Entity {
  private Map<String, String> variables;

  public Entity(String root, String uppercaseName) {
    variables = new HashMap<>();
    variables.put("Name", uppercaseName);
    variables.put("name", uppercaseName.toLowerCase());
    variables.put("entityFull", root+"."+Template.Entity.subPack+"."+uppercaseName);
    variables.put("daoFull", root+"."+Template.DAO.subPack+"."+uppercaseName+"Repository");
    variables.put("resourceFull", root+"."+Template.Resource.subPack+"."+uppercaseName+"ResourceAssembler");
    variables.put("ctrlFull", root+"."+Template.CTRL.subPack+"."+uppercaseName+"Ctrl");
    variables.put("root", root);
  }

  public Map<String, String> getVariables() {
    return variables;
  }
}