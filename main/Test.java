package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import main.Entity;

public class Test {
  private String root = "assabi";
  private List<Template> templates = new ArrayList<>();

  public void write(String ready, File file) {
    if (file == null)
      System.out.println(ready);
    else
      System.out.println("Falta implementar para escrever em "+file.getAbsolutePath()+":\n"+ready);
  }

  public File createFile(Template temp, String uppercaseName) {
    File file = new File(root);
    if (!file.exists())
      file.mkdir();
    file = new File(file, temp.subPack);
    if (!file.exists())
      file.mkdir();
    file = new File(file, uppercaseName+temp.nameComplement+".java");
    return file;
  }
  
  public void process(Template temp, String uppercaseName) {
    Entity entity = new Entity(root, uppercaseName);
    Map<String, String> map = entity.getVariables();
    String text = temp.toString(map);
    File file = createFile(temp, uppercaseName);
    write(text, file);
  }

  public static void main(String[] args) {
    Test self = new Test();
    Stream.of(Template.values())
      .forEach(temp -> self.process(temp, "Stakeholder"));
  }
}