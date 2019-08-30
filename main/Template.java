package main;

import java.util.Map;

public enum Template {
  /**
   * Has the variables "ctrlFull", "entityFull", "Name", "name"
   */
  Resource("resource", "ResourceAssembler",
    "\nimport static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;\nimport static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;\n\nimport org.springframework.hateoas.Resource;\nimport org.springframework.hateoas.ResourceAssembler;\nimport org.springframework.stereotype.Component;\n\nimport ",
    "ctrlFull",
    ";\nimport ",
    "entityFull",
    ";\n\n@Component\npublic class ",
    "Name",
    "ResourceAssembler implements ResourceAssembler<",
    "Name",
    ", Resource<",
    "Name",
    ">> {\n\n\t@Override\n\tpublic Resource<",
    "Name",
    "> toResource(",
    "Name",
    " entity) {\n\t\treturn new Resource<>(entity,\n\t\t\t\tlinkTo(methodOn(",
    "Name",
    "Ctrl.class).get(entity.getId())).withSelfRel(),\n\t\t\t\tlinkTo(methodOn(",
    "Name",
    "Ctrl.class).all()).withRel(\"/",
    "name",
    "\"));\n\t}\n\n}"),
  /**
   * Has the variables "entityFull", "Name"
   */
  DAO("dao", "Repository",
    "\nimport org.springframework.data.jpa.repository.JpaRepository;\n\nimport ",
    "entityFull",
    ";\n\npublic interface ",
    "Name",
    "Repository extends JpaRepository<",
    "Name",
    ", Long> {\n\n}"),
  /**
   * Has the variables "description", "url", "daoFull", "entityFull", "resourceFull", "Name", "name"
   */
  CTRL("controllers", "Ctrl",
    "\nimport static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;\nimport static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;\n\nimport java.util.List;\nimport java.util.stream.Collectors;\n\nimport org.springframework.hateoas.Resource;\nimport org.springframework.hateoas.Resources;\nimport org.springframework.web.bind.annotation.GetMapping;\nimport org.springframework.web.bind.annotation.PathVariable;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\nimport ",
    "daoFull",
    ";\nimport ",
    "entityFull",
    ";\nimport assabi.exceptions.EntityNotFoundException;\nimport ",
    "resourceFull",
    ";\n\n@RestController\n@RequestMapping(\"/",
    "url",
    "\")\npublic class ",
    "Name",
    "Ctrl {\n\tprivate final ",
    "Name",
    "Repository repository;\n\tprivate final ",
    "Name",
    "ResourceAssembler assembler;\n\n\tpublic ",
    "Name",
    "Ctrl(",
    "Name",
    "Repository repository, ",
    "Name",
    "ResourceAssembler assembler) {\n\t\tsuper();\n\t\tthis.repository = repository;\n\t\tthis.assembler = assembler;\n\t}\n\n\t@GetMapping(\"/\")\n\tpublic Resources<Resource<",
    "Name",
    ">> all() {\n\t\tList<Resource<",
    "Name",
    ">> list = repository.findAll().stream()\n\t\t\t\t.map(assembler::toResource)\n\t\t\t\t.collect(Collectors.toList());\n\t\treturn new Resources<>(list,\n\t\t\t\tlinkTo(methodOn(",
    "Name",
    "Ctrl.class).all()).withSelfRel());\n\t}\n\n\t@GetMapping(\"/{id}\")\n\tpublic Resource<",
    "Name",
    "> get(@PathVariable Long id) {\n\t\t",
    "Name",
    " ",
    "name",
    " = repository.findById(id).orElseThrow(EntityNotFoundException.supplier(id, \"",
    "description",
    "\"));\n\t\treturn assembler.toResource(",
    "name",
    ");\n\t}\n}"),
  Entity("entity", "",
    "\nimport javax.persistence.Entity;\nimport javax.persistence.GeneratedValue;\nimport javax.persistence.Id;\n\nimport lombok.Data;\n\n@Data\n@Entity\npublic class "
    ,"Name",
    " {\n\t@Id\n\t@GeneratedValue\n\tprivate Long id;\n\tprivate String name;\n\n\tpublic ",
    "Name",
    "() {\n\t\tsuper();\n\t}\n}");

  private final String[] texts;
  public final String subPack;
  public final String nameComplement;

  private Template(String subPack, String nameComplement, String ... texts) {
    this.subPack = subPack;
    this.nameComplement = nameComplement;
    this.texts = texts;
  }

  public String toString(Map<String, String> variables) {
    StringBuilder str = new StringBuilder("package ")
      .append(variables.get("root"))
      .append('.')
      .append(subPack)
      .append(";\n")
      .append(texts[0]);
    for (int i = 1; i < texts.length; i+=2) {
      str.append(variables.get(texts[i]));
      str.append(texts[i+1]);
    }
    return str.toString();
  }
}