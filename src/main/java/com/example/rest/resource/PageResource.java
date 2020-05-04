package com.example.rest.resource;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Represents a page.
 *
 * @author Marcel Overdijk
 */
@Schema(name = "Page",
        description = "Represents a page.")
@JsonbPropertyOrder({
        "elements",
        "totalElements",
        "totalPages",
        "hasNextPage",
        "hasPreviousPage"
})
public class PageResource<T> {

    @Schema(name = "elements",
            description = "The elements of the page.",
            required = true)
    @JsonbProperty("elements")
    private List<T> elements;

    @Schema(name = "total-elements",
            description = "The total number of elements.",
            required = true)
    @JsonbProperty("total-elements")
    private long totalElements;

    @Schema(name = "total-pages",
            description = "The total number of pages.",
            required = true)
    @JsonbProperty("total-pages")
    private long totalPages;

    @Schema(name = "has-next-page",
            description = "Whether there is a next page.",
            required = true)
    @JsonbProperty("has-next-page")
    private boolean hasNextPage;

    @Schema(name = "has-previous-page",
            description = "Whether there is a previous page.",
            required = true)
    @JsonbProperty("has-previous-page")
    private boolean hasPreviousPage;

    public PageResource(List<T> elements, long totalElements, long totalPages, boolean hasNextPage, boolean hasPreviousPage) {
        this.elements = elements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
    }

    public List<T> getElements() {
        return elements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
}
