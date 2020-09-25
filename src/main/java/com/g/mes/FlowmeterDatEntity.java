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
    private Integer highAlarm;
    private Integer lowAlarm;
    private Integer atcAlarm;
    private Integer sysAlarm;

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

    @Basic
    @Column(name = "high_alarm")
    public Integer getHighAlarm() {
        return highAlarm;
    }

    public void setHighAlarm(Integer highAlarm) {
        this.highAlarm = highAlarm;
    }

    @Basic
    @Column(name = "low_alarm")
    public Integer getLowAlarm() {
        return lowAlarm;
    }

    public void setLowAlarm(Integer lowAlarm) {
        this.lowAlarm = lowAlarm;
    }

    @Basic
    @Column(name = "atc_alarm")
    public Integer getAtcAlarm() {
        return atcAlarm;
    }

    public void setAtcAlarm(Integer atcAlarm) {
        this.atcAlarm = atcAlarm;
    }

    @Basic
    @Column(name = "sys_alarm")
    public Integer getSysAlarm() {
        return sysAlarm;
    }

    public void setSysAlarm(Integer sysAlarm) {
        this.sysAlarm = sysAlarm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowmeterDatEntity that = (FlowmeterDatEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
