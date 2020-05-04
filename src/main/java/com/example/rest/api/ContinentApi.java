package com.example.rest.api;

import com.example.domain.Continent;
import com.example.rest.resource.ContinentResource;
import com.example.rest.resource.PageResource;
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
 * The continent REST API.
 *
 * @author Marcel Overdijk
 */
@Path("/continents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "continent")
public class ContinentApi {

    @GET
    @Operation(
            summary = "Retrieve a page of continents",
            description = "Retrieve a page of continents using the provided filtering, pagination and sorting parameters.")
    public PageResource<ContinentResource> getContinents(
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
        PanacheQuery<Continent> query = Continent
                .findAll()
                .page(Page.of(pageIndex, pageSize));
        var continents = query.list();
        var continentResources = continents
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());
        var totalElements = query.count();
        var totalPages = query.pageCount();
        var hasNextPage = query.hasNextPage();
        var hasPreviousPage = query.hasPreviousPage();
        return new PageResource<>(continentResources, totalElements, totalPages, hasNextPage, hasPreviousPage);
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Retrieve a continent",
            description = "Retrieve a continent using the continent's id.")
    public ContinentResource getContinent(
            @Parameter(
                    description = "The id of the continent to retrieve.",
                    required = true,
                    example = "europe",
                    schema = @Schema(type = SchemaType.STRING))
            @PathParam("id") String id) {
        Optional<Continent> optional = Continent.findByIdOptional(id);
        var continent = optional.orElseThrow(() -> new NotFoundException());
        var continentResource = toResource(continent);
        return continentResource;
    }

    private ContinentResource toResource(Continent continent) {
        return new ContinentResource(
                continent.id,
                continent.code,
                continent.name,
                continent.demonym
        );
    }
}
