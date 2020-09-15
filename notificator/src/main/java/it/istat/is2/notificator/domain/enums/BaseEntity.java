package it.istat.is2.notificator.domain.enums;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedBy
    @Column(name = "creatorUser")
    private String creatorUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    private Date creationDate;

    @LastModifiedBy
    @Column(name = "modifierUser")
    private String modifierUser;

    @LastModifiedDate
    @Column(name = "modifyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(String modifierUser) {
        this.modifierUser = modifierUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
