package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.dto;

public class DevDTO {
    private Long id;
    private String name;
    private String surname;
    private String specialization;
    private Long studioId;

    public DevDTO() {}

    public DevDTO(Long id, String name, String surname, String specialization, Long studioId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.studioId = studioId;
    }

    public Long getId() {return id;}

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }
}
