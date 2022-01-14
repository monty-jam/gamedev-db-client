package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.dto;

public class DevDTO {
    private final String name;
    private final String surname;
    private final String specialization;
    private final Long studioId;

    public DevDTO(String name, String surname, String specialization, Long studioId) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Long getStudioId() {
        return studioId;
    }
}
