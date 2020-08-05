package com.example.rest.api;

import com.example.domain.Country;
import com.example.rest.resource.CountryPageResource;
import com.example.rest.resource.CountryResource;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * The country REST API.
 *
 * @author Marcel Overdijk
 */
@Path("/countries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "country")
public class CountryApi {

    @GET
    @Operation(
            summary = "Retrieve a page of countries",
            description = "Retrieve a page of countries using the provided filtering, pagination and sorting parameters.")
    public CountryPageResource getCountries(
            @Parameter(
                    description = "The page index to retrieve.",
                    required = false,
                    example = "0",
                    schema = @Schema(type = SchemaType.INTEGER))
            @QueryParam("page-index")
            @DefaultValue("0") int pageIndex,
            @Parameter(
                    description = "The page size to retrieve.",
                    required = false,
                    example = "10",
                    schema = @Schema(type = SchemaType.INTEGER))
            @QueryParam("page-size")
            @DefaultValue("10") int pageSize) {
        PanacheQuery<Country> query = Country
                .findAll()
                .page(Page.of(pageIndex, pageSize));
        var countries = query.list();
        var countryResources = countries
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
        var totalElements = query.count();
        var totalPages = query.pageCount();
        var hasNextPage = query.hasNextPage();
        var hasPreviousPage = query.hasPreviousPage();
        return new CountryPageResource(countryResources, totalElements, totalPages, hasNextPage, hasPreviousPage);
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Retrieve a country",
            description = "Retrieve a country using the country's id.")
    public CountryResource getCountry(
            @Parameter(
                    description = "The id of the country to retrieve.",
                    required = true,
                    example = "netherlands",
                    schema = @Schema(type = SchemaType.STRING))
            @PathParam("id") String id) {
        Optional<Country> optional = Country.findByIdOptional(id);
        var country = optional.orElseThrow(() -> new NotFoundException());
        var countryResource = toResource(country);
        return countryResource;
    }

    private CountryResource toResource(Country country) {
        return new CountryResource(
                country.id,
                country.alpha2Code,
                country.alpha3Code,
                country.name,
                country.demonym,
                country.continent.id
        );
    }
}
