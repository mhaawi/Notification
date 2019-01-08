package com.edigitpath.notification.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.edigitpath.notification.domain.enumeration.Channel;

/**
 * A NotificationDefination.
 */
@Entity
@Table(name = "notification_defination")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationDefination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "notification_key", nullable = false)
    private String notificationKey;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "channel_name", nullable = false)
    private Channel channelName;

    @NotNull
    @Column(name = "template_path", nullable = false)
    private String templatePath;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @Column(name = "source_type", nullable = false)
    private String sourceType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "notification_defination_",
               joinColumns = @JoinColumn(name = "notification_definations_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "_id", referencedColumnName = "id"))
    private Set<Application>  = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "notification_defination_",
               joinColumns = @JoinColumn(name = "notification_definations_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "_id", referencedColumnName = "id"))
    private Set<ApiDefination>  = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public NotificationDefination notificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
        return this;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public Channel getChannelName() {
        return channelName;
    }

    public NotificationDefination channelName(Channel channelName) {
        this.channelName = channelName;
        return this;
    }

    public void setChannelName(Channel channelName) {
        this.channelName = channelName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public NotificationDefination templatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public NotificationDefination isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getSourceType() {
        return sourceType;
    }

    public NotificationDefination sourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Set<Application> get() {
        return ;
    }

    public NotificationDefination (Set<Application> applications) {
        this. = applications;
        return this;
    }

    public NotificationDefination add(Application application) {
        this..add(application);
        application.getNotificationDefinations().add(this);
        return this;
    }

    public NotificationDefination remove(Application application) {
        this..remove(application);
        application.getNotificationDefinations().remove(this);
        return this;
    }

    public void set(Set<Application> applications) {
        this. = applications;
    }

    public Set<ApiDefination> get() {
        return ;
    }

    public NotificationDefination (Set<ApiDefination> apiDefinations) {
        this. = apiDefinations;
        return this;
    }

    public NotificationDefination add(ApiDefination apiDefination) {
        this..add(apiDefination);
        apiDefination.getNotificationDefinations().add(this);
        return this;
    }

    public NotificationDefination remove(ApiDefination apiDefination) {
        this..remove(apiDefination);
        apiDefination.getNotificationDefinations().remove(this);
        return this;
    }

    public void set(Set<ApiDefination> apiDefinations) {
        this. = apiDefinations;
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
        NotificationDefination notificationDefination = (NotificationDefination) o;
        if (notificationDefination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationDefination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationDefination{" +
            "id=" + getId() +
            ", notificationKey='" + getNotificationKey() + "'" +
            ", channelName='" + getChannelName() + "'" +
            ", templatePath='" + getTemplatePath() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", sourceType='" + getSourceType() + "'" +
            "}";
    }
}
