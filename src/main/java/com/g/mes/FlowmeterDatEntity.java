package com.g.mes;

import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "flowmeter_dat", schema = "dbo", catalog = "MES")
@DynamicInsert
@DynamicUpdate
@ToString
public class FlowmeterDatEntity {
    private Integer id;
    private LocalDateTime ctime;
    private Integer deviceId;
    private Float q;
    private Float v;
    private Float qPct;
    private Float ratio;
    private Double accqPos;
    private Double accqNeg;
    private Integer qUnit;
    private Integer accqUnit;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ctime")
    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    @Basic
    @Column(name = "device_id")
    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "q")
    public Float getQ() {
        return q;
    }

    public void setQ(Float q) {
        this.q = q;
    }

    @Basic
    @Column(name = "v")
    public Float getV() {
        return v;
    }

    public void setV(Float v) {
        this.v = v;
    }

    @Basic
    @Column(name = "q_pct")
    public Float getqPct() {
        return qPct;
    }

    public void setqPct(Float qPct) {
        this.qPct = qPct;
    }

    @Basic
    @Column(name = "ratio")
    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    @Basic
    @Column(name = "accq_pos")
    public Double getAccqPos() {
        return accqPos;
    }

    public void setAccqPos(Double accqPos) {
        this.accqPos = accqPos;
    }

    @Basic
    @Column(name = "accq_neg")
    public Double getAccqNeg() {
        return accqNeg;
    }

    public void setAccqNeg(Double accqNeg) {
        this.accqNeg = accqNeg;
    }

    @Basic
    @Column(name = "q_unit")
    public Integer getqUnit() {
        return qUnit;
    }

    public void setqUnit(Integer qUnit) {
        this.qUnit = qUnit;
    }

    @Basic
    @Column(name = "accq_unit")
    public Integer getAccqUnit() {
        return accqUnit;
    }

    public void setAccqUnit(Integer accqUnit) {
        this.accqUnit = accqUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowmeterDatEntity that = (FlowmeterDatEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ctime, that.ctime) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(q, that.q) &&
                Objects.equals(v, that.v) &&
                Objects.equals(qPct, that.qPct) &&
                Objects.equals(ratio, that.ratio) &&
                Objects.equals(accqPos, that.accqPos) &&
                Objects.equals(accqNeg, that.accqNeg) &&
                Objects.equals(qUnit, that.qUnit) &&
                Objects.equals(accqUnit, that.accqUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ctime, deviceId, q, v, qPct, ratio, accqPos, accqNeg, qUnit, accqUnit);
    }
}
