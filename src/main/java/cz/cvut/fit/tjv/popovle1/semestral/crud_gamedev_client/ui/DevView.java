package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.ui;

import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.dto.DevDTO;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.ExitRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collection;

@Component
public class DevView {

    public void printAllUsers(Collection<DevDTO> users) {
        users.forEach(d -> System.out.println("`" + d.getId() + " " + d.getName() + " " + d.getSurname() + "'"));
    }

    public void printDev(DevDTO ret) {
        System.out.println("Id " + ret.getId());
        System.out.println("   Name: " + ret.getName());
        System.out.println("   Surname: " + ret.getSurname());
        System.out.println("   Specialization: " + ret.getSpecialization());
        System.out.println("   Studio Id: " + ret.getStudioId());
    }

    public void printError(Throwable e) {
        if (e instanceof WebClientRequestException) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot connect to API:",
                    AnsiColor.DEFAULT
            ));
            System.err.println(e.getMessage());
            throw new ExitRequest();
        } else {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Unknown error:",
                    AnsiColor.DEFAULT
            ));
            System.err.println(e.getMessage());
        }
    }

    public void printErrorCreate(WebClientException e) {
        if (e instanceof WebClientResponseException.Conflict) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Developer already exists.",
                    AnsiColor.DEFAULT
            ));
        } else
            printError(e);
    }

    public void printErrorUpdate(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot update - user does not exist.",
                    AnsiColor.DEFAULT
            ));
        } else
            printError(e);
    }

    public void printErrorDelete(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "User does not exist.",
                    AnsiColor.DEFAULT
            ));
        } else
            printError(e);
    }
}
