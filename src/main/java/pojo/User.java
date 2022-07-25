package pojo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Setter
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

    public User(String firstName, String lastName, String email, String gender, String active, int subjectId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.status = active;
        this.subjectId = subjectId;
    }

    @JsonGetter
    public String getFirstName() {
        return firstName;
    }

    @JsonGetter
    public String getLastName() {
        return lastName;
    }

    @JsonGetter
    public String getEmail() {
        return email;
    }

    @JsonGetter
    public String getGender() {
        return gender;
    }

    @JsonGetter
    public String getStatus() {
        return status;
    }

    @JsonGetter
    public int getSubjectId() {
        return subjectId;
    }

    @JsonGetter
    public int getId() {
        return id;
    }
}
