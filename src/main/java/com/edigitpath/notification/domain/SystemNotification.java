package com.edigitpath.notification.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.edigitpath.notification.domain.enumeration.StatusType;

/**
 * A SystemNotification.
 */
@Entity
@Table(name = "system_notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemNotification implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;

    @Column(name = "send_date")
    private ZonedDateTime sendDate;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "source_type")
    private String sourceType;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "application_key")
    private String applicationKey;

    @Column(name = "error")
    private String error;

    @Column(name = "error_short_name")
    private String errorShortName;

    @Column(name = "error_date")
    private ZonedDateTime errorDate;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "deleted_date")
    private ZonedDateTime deletedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public SystemNotification content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusType getStatus() {
        return status;
    }

    public SystemNotification status(StatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public ZonedDateTime getSendDate() {
        return sendDate;
    }

    public SystemNotification sendDate(ZonedDateTime sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public void setSendDate(ZonedDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public String getSourceId() {
        return sourceId;
    }

    public SystemNotification sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public SystemNotification sourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public SystemNotification sourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    public SystemNotification applicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
        return this;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    public String getError() {
        return error;
    }

    public SystemNotification error(String error) {
        this.error = error;
        return this;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorShortName() {
        return errorShortName;
    }

    public SystemNotification errorShortName(String errorShortName) {
        this.errorShortName = errorShortName;
        return this;
    }

    public void setErrorShortName(String errorShortName) {
        this.errorShortName = errorShortName;
    }

    public ZonedDateTime getErrorDate() {
        return errorDate;
    }

    public SystemNotification errorDate(ZonedDateTime errorDate) {
        this.errorDate = errorDate;
        return this;
    }

    public void setErrorDate(ZonedDateTime errorDate) {
        this.errorDate = errorDate;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public SystemNotification deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ZonedDateTime getDeletedDate() {
        return deletedDate;
    }

    public SystemNotification deletedDate(ZonedDateTime deletedDate) {
        this.deletedDate = deletedDate;
        return this;
    }

    public void setDeletedDate(ZonedDateTime deletedDate) {
        this.deletedDate = deletedDate;
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
        SystemNotification systemNotification = (SystemNotification) o;
        if (systemNotification.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemNotification.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemNotification{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", status='" + getStatus() + "'" +
            ", sendDate='" + getSendDate() + "'" +
            ", sourceId='" + getSourceId() + "'" +
            ", sourceType='" + getSourceType() + "'" +
            ", sourceName='" + getSourceName() + "'" +
            ", applicationKey='" + getApplicationKey() + "'" +
            ", error='" + getError() + "'" +
            ", errorShortName='" + getErrorShortName() + "'" +
            ", errorDate='" + getErrorDate() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", deletedDate='" + getDeletedDate() + "'" +
            "}";
    }
}
