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
public interface PageResource<T> {
    List<T> getElements();
    long getTotalElements();
    long getTotalPages();
    boolean isHasNextPage();
    boolean isHasPreviousPage();
}
