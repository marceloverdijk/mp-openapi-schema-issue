package com.example.rest.resource;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Represents a country page.
 *
 * @author Marcel Overdijk
 */
@Schema(name = "Country page", description = "Represents a country page.")
public class CountryPageResource extends BasePageResource<CountryResource> {

    public CountryPageResource(List<CountryResource> elements, long totalElements, long totalPages, boolean hasNextPage, boolean hasPreviousPage) {
        super(elements, totalElements, totalPages, hasNextPage, hasPreviousPage);
    }
}
