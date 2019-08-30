package main;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Entity {
  private Map<String, String> variables;

  public Entity(String root, String name) {
    String camel = new StringBuilder(name.substring(0, 1).toLowerCase())
      .append(name.substring(1)).toString();
    variables = new HashMap<>();
    variables.put("Name", name);
    variables.put("name", camel.toString());
    variables.put("url", withSeparator(camel, '_'));
    variables.put("description", withSeparator(camel, ' '));
    variables.put("entityFull", root+"."+Template.Entity.subPack+"."+name);
    variables.put("daoFull", root+"."+Template.DAO.subPack+"."+name+"Repository");
    variables.put("resourceFull", root+"."+Template.Resource.subPack+"."+name+"ResourceAssembler");
    variables.put("ctrlFull", root+"."+Template.CTRL.subPack+"."+name+"Ctrl");
    variables.put("root", root);
  }

  private static String withSeparator(String camel, int separator) {
    return camel.codePoints()
    .flatMap(c -> Character.isUpperCase(c) ?
      IntStream.of(separator, Character.toLowerCase(c)) :
      IntStream.of(c))
    .collect(StringBuilder::new,
      StringBuilder::appendCodePoint,
      StringBuilder::append)
    .toString();
  }

  public Map<String, String> getVariables() {
    return variables;
  }
}