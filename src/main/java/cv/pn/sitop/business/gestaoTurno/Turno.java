package cv.pn.sitop.business.gestaoTurno;

import cv.pn.sitop.commons.CommonsAttributes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;


@Entity
@Table(name = "gestao_turno")
public class Turno extends CommonsAttributes {

    @Column(name = "local_time") //nullable = false)
    private LocalTime localTime;

    @Column(name = "description_code", length = 100)
    private String descriptionCode;

    @Column(name = "organic", length = 100)
    private String organic;

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public String getDescriptionCode() {
        return descriptionCode;
    }

    public void setDescriptionCode(String descriptionCode) {
        this.descriptionCode = descriptionCode;
    }

    public String getOrganic() {
        return organic;
    }

    public void setOrganic(String organic) {
        this.organic = organic;
    }
}
