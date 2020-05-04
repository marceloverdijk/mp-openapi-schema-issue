package com.example.rest.resource;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Represents a continent.
 *
 * @author Marcel Overdijk
 */
@Schema(name = "Continent",
        description = "Represents a continent.")
@JsonbPropertyOrder({
        "id",
        "code",
        "name",
        "demonym"
})
public class ContinentResource{

    @Schema(name = "id",
            description = "The id of the continent.",
            required = true)
    @JsonbProperty("id")
    private String id;

    @Schema(name = "code",
            description = "The code of the continent.",
            required = true)
    @JsonbProperty("code")
    private String code;

    @Schema(name = "name",
            description = "The name of the continent.",
            required = true)
    @JsonbProperty("name")
    private String name;

    @Schema(name = "demonym",
            description = "The demonym of the continent.",
            required = true)
    @JsonbProperty("demonym")
    private String demonym;

    public ContinentResource(String id, String code, String name, String demonym) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.demonym = demonym;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDemonym() {
        return demonym;
    }
}
