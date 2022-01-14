package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudGamedevClientApplication {

    public static void main(String[] args) {
        var app = new SpringApplication(CrudGamedevClientApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
