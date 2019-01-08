package com.edigitpath.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.edigitpath.notification.domain.enumeration.ApiType;

/**
 * A ApiDefination.
 */
@Entity
@Table(name = "api")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApiDefination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "api_type", nullable = false)
    private ApiType apiType;

    @NotNull
    @Column(name = "api_url", nullable = false)
    private String apiUrl;

    @OneToMany(mappedBy = "apiDefination")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApiParam> apiParams = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public ApiDefination apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public ApiDefination apiType(ApiType apiType) {
        this.apiType = apiType;
        return this;
    }

    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public ApiDefination apiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Set<ApiParam> getApiParams() {
        return apiParams;
    }

    public ApiDefination apiParams(Set<ApiParam> apiParams) {
        this.apiParams = apiParams;
        return this;
    }

    public ApiDefination addApiParam(ApiParam apiParam) {
        this.apiParams.add(apiParam);
        apiParam.setApiDefination(this);
        return this;
    }

    public ApiDefination removeApiParam(ApiParam apiParam) {
        this.apiParams.remove(apiParam);
        apiParam.setApiDefination(null);
        return this;
    }

    public void setApiParams(Set<ApiParam> apiParams) {
        this.apiParams = apiParams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiDefination apiDefination = (ApiDefination) o;
        if (apiDefination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apiDefination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApiDefination{" +
            "id=" + getId() +
            ", apiKey='" + getApiKey() + "'" +
            ", apiType='" + getApiType() + "'" +
            ", apiUrl='" + getApiUrl() + "'" +
            "}";
    }
}