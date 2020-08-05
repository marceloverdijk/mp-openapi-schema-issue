package com.example.rest.resource;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Represents a continent page.
 *
 * @author Marcel Overdijk
 */
@Schema(name = "Continent page", description = "Represents a continent page.")
public class ContinentPageResource extends BasePageResource<ContinentResource> {

    public ContinentPageResource(List<ContinentResource> elements, long totalElements, long totalPages, boolean hasNextPage, boolean hasPreviousPage) {
        super(elements, totalElements, totalPages, hasNextPage, hasPreviousPage);
    }
}
