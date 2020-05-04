package com.example.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Represents a country.
 *
 * @author Marcel Overdijk
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "country")
public class Country extends PanacheEntityBase {

    public static Sort DEFAULT_SORT = Sort.ascending("name");

    @Id
    @Column(name = "id")
    public String id;

    @Column(name = "alpha2_code")
    public String alpha2Code;

    @Column(name = "alpha3_code")
    public String alpha3Code;

    @Column(name = "name")
    public String name;

    @Column(name = "demonym")
    public String demonym;

    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    public Continent continent;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "<" + id + ">";
    }
}
