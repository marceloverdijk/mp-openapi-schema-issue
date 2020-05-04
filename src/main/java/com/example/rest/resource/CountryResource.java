package com.example.rest.resource;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Represents a country.
 *
 * @author Marcel Overdijk
 */
@Schema(name = "Country",
        description = "Represents a country.")
@JsonbPropertyOrder({
        "id",
        "alpha2Code",
        "alpha3Code",
        "name",
        "demonym",
        "continentId"
})
public class CountryResource {

    @Schema(name = "id",
            description = "The id of the country.",
            required = true)
    @JsonbProperty("id")
    private String id;

    @Schema(name = "alpha2-code",
            description = "The alpha2 code of the country.",
            required = true)
    @JsonbProperty("alpha2-code")
    private String alpha2Code;

    @Schema(name = "alpha3-code",
            description = "The alpha3 code of the country.",
            required = true)
    @JsonbProperty("alpha3-code")
    private String alpha3Code;

    @Schema(name = "name",
            description = "The name of the country.",
            required = true)
    @JsonbProperty("name")
    private String name;

    @Schema(name = "demonym",
            description = "The demonym of the country.",
            required = true)
    @JsonbProperty("demonym")
    private String demonym;

    @Schema(name = "continent-id",
            description = "The continent id of the country.",
            required = true)
    @JsonbProperty("continent-id")
    private String continentId;

    public CountryResource(String id, String alpha2Code, String alpha3Code, String name, String demonym, String continentId) {
        this.id = id;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.name = name;
        this.demonym = demonym;
        this.continentId = continentId;
    }

    public String getId() {
        return id;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getName() {
        return name;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getContinentId() {
        return continentId;
    }
}
