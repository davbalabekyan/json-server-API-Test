package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subject implements Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonGetter
    public String getName() {
        return name;
    }
}
