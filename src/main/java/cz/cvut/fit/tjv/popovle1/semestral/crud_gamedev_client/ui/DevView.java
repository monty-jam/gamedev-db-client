package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.ui;

import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.dto.DevDTO;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.ExitRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collection;

@Component
public class DevView {

    public void printAllDevs(Collection<DevDTO> devs) {
        devs.forEach(d -> System.out.println("`" + d.getId() + " " + d.getName() + " " + d.getSurname() + "'"));
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

    public void printErrorNotFound(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Developer does not exist.",
                    AnsiColor.DEFAULT
            ));
        } else
            printError(e);
    }

    public void printErrorStudioNotFound(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Studio does not exist.",
                    AnsiColor.DEFAULT
            ));
        } else
            printError(e);
    }

    public void printErrorAlreadyExists(WebClientException e) {
        if (e instanceof WebClientResponseException.Conflict) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED,
                    "Developer already exists.",
                    AnsiColor.DEFAULT
            ));
        } else
            printError(e);
    }

    public void printErrorTest(Throwable e) {
        if (e instanceof WebClientResponseException) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED,
                    e.getMessage(),
                    AnsiColor.DEFAULT
            ));
        } else if (e instanceof WebClientRequestException) {
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
}
