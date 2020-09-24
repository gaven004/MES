package com.g.mes;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "water_flowmeter_dat", schema = "dbo", catalog = "MES")
@DynamicInsert
@DynamicUpdate
@ToString
public class WaterFlowmeterDatEntity {
    private Integer id;
    private LocalDateTime ctime;
    private Integer deviceId;
    private Float flowRate;
    private Integer accqInt;
    private Float accqFloat;

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
    @Column(name = "flow_rate")
    public Float getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(Float flowRate) {
        this.flowRate = flowRate;
    }

    @Basic
    @Column(name = "accq_int")
    public Integer getAccqInt() {
        return accqInt;
    }

    public void setAccqInt(Integer accqInt) {
        this.accqInt = accqInt;
    }

    @Basic
    @Column(name = "accq_float")
    public Float getAccqFloat() {
        return accqFloat;
    }

    public void setAccqFloat(Float accqFloat) {
        this.accqFloat = accqFloat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaterFlowmeterDatEntity that = (WaterFlowmeterDatEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ctime, that.ctime) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(flowRate, that.flowRate) &&
                Objects.equals(accqInt, that.accqInt) &&
                Objects.equals(accqFloat, that.accqFloat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ctime, deviceId, flowRate, accqInt, accqFloat);
    }
}
