package de.rieckpil.blog;

import io.keploy.servlet.KeployMiddleware;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@SpringBootApplication

@Import(KeployMiddleware.class)

public class Application implements CommandLineRunner {

  private final UsersClient usersClient;
  private final ObjectMapper objectMapper;

  public Application(UsersClient usersClient, ObjectMapper objectMapper) {
    this.usersClient = usersClient;
    this.objectMapper = objectMapper;
  }


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(usersClient.getUserById(1L));

    ObjectNode objectNode = objectMapper.createObjectNode();
    objectNode.put("name", "dke");
    objectNode.put("email", "dke@java.io");
    objectNode.set("address",
      objectMapper.createObjectNode()
        .put("street", "main")
        .put("postalCode", "91074"));
    objectNode.set("hobbies", objectMapper.createArrayNode().add("sports").add("bowling"));

    System.out.println(usersClient.createNewUser(objectNode));
  }
}
