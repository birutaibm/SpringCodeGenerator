package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import main.Entity;

public class Test {
  private String root = "assabi";

  public void write(String ready, File file) {
    if (file == null)
      System.out.println(ready);
	else
		try {
			Files.write(file.toPath(), ready.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
    Stream.of(args).forEach(name ->
      Stream.of(Template.values())
        .forEach(temp -> self.process(temp, name))
    );
  }
}