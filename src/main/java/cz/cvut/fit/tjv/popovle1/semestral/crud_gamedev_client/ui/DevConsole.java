package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.ui;

import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.data.DevClient;
import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.dto.DevDTO;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ShellComponent
public class DevConsole {
    private final DevClient devClient;
    private final DevView devView;

    public DevConsole(DevClient devClient, DevView devView) {
        this.devClient = devClient;
        this.devView = devView;
    }

    @ShellMethod("Display the list of all developers")
    public void listDevs() {
        try {
            var developers = devClient.readAll();
            devView.printAllDevs(developers);
        } catch (WebClientException e) {
            devView.printError(e);
        }
    }

    @ShellMethod("Create new developer")
    public void createDev(String name, String surname, String specialization, Long studioId) {
        try {
            var newDev = new DevDTO(null, name, surname, specialization, studioId);
            var ret = devClient.create(newDev);
            devView.printDev(ret);
        } catch (WebClientException e) {
            devView.printErrorStudioNotFound(e);
        }
    }

    @ShellMethod("Set current developer")
    public void setDev(Long id) {
        try {
            devClient.setCurrentId(id);
        } catch (WebClientException e) {
            devView.printErrorTest(e);
        }
    }

    @ShellMethod("Unset current developer, move to general scope")
    @ShellMethodAvailability("currentIdNeededAvailability")
    public void unsetDev() {
        devClient.setCurrentId(null);
    }

    public Availability currentIdNeededAvailability() {
        return devClient.getCurrentId() == null ?
                Availability.unavailable("Current id needs to be set in advance.")
                :
                Availability.available();
    }

    @ShellMethod("Print details of selected developer")
    @ShellMethodAvailability("currentIdNeededAvailability")
    public void readDev() {
        try {
            devView.printDev(devClient.read());
        } catch (WebClientException e) {
            devView.printErrorTest(e);
        }
    }

    @ShellMethod("Update a developer")
    @ShellMethodAvailability("currentIdNeededAvailability")
    public void updateDev(String name, String surname, String specialization, Long studioId) {
        try {
            devClient.update(new DevDTO(null, name, surname, specialization, studioId));
        } catch (WebClientException e) {
            devView.printErrorTest(e);
        }
    }

    @ShellMethod("Update a developer")
    @ShellMethodAvailability("currentIdNeededAvailability")
    public void deleteDev() {
        try {
            devClient.delete();
        } catch (WebClientException e) {
            devView.printErrorTest(e);
        }
    }
}

