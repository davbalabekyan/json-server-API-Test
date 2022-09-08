package pojo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("status")
    private String status;
    @JsonProperty("subjectId")
    private int subjectId;
    @JsonProperty("id")
    private int id;

    public User() {
    }

    public User(String firstName, String lastName, String email, String gender, String active, int subjectId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.status = active;
        this.subjectId = subjectId;
    }

    public User(String firstName, String lastName, String email, String gender, String status, int subjectId, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.status = status;
        this.subjectId = subjectId;
        this.id = id;
    }

    @JsonGetter
    public String getFirstName() {
        return firstName;
    }

    @JsonSetter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonGetter
    public String getLastName() {
        return lastName;
    }

    @JsonSetter
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonGetter
    public String getEmail() {
        return email;
    }

    @JsonSetter
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter
    public String getGender() {
        return gender;
    }

    @JsonSetter
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonGetter
    public String getStatus() {
        return status;
    }

    @JsonSetter
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonGetter
    public int getSubjectId() {
        return subjectId;
    }

    @JsonSetter
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return subjectId == user.subjectId && id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, gender, status, subjectId, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", subjectId=" + subjectId +
                ", id=" + id +
                '}';
    }
}
