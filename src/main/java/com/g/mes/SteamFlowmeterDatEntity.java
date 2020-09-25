package com.g.mes;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "steam_flowmeter_dat", schema = "dbo", catalog = "MES")
@DynamicInsert
@DynamicUpdate
@ToString
public class SteamFlowmeterDatEntity {
    private Integer id;
    private LocalDateTime ctime;
    private Integer deviceId;
    private Float flowRate1;
    private Float flowRate2;
    private Float flowTotal1;
    private Float flowTotal2;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "flow_rate1")
    public Float getFlowRate1() {
        return flowRate1;
    }

    public void setFlowRate1(Float flowRate1) {
        this.flowRate1 = flowRate1;
    }

    @Basic
    @Column(name = "flow_rate2")
    public Float getFlowRate2() {
        return flowRate2;
    }

    public void setFlowRate2(Float flowRate2) {
        this.flowRate2 = flowRate2;
    }

    @Basic
    @Column(name = "flow_total1")
    public Float getFlowTotal1() {
        return flowTotal1;
    }

    public void setFlowTotal1(Float flowTotal1) {
        this.flowTotal1 = flowTotal1;
    }

    @Basic
    @Column(name = "flow_total2")
    public Float getFlowTotal2() {
        return flowTotal2;
    }

    public void setFlowTotal2(Float flowTotal2) {
        this.flowTotal2 = flowTotal2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SteamFlowmeterDatEntity that = (SteamFlowmeterDatEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ctime, that.ctime) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(flowRate1, that.flowRate1) &&
                Objects.equals(flowRate2, that.flowRate2) &&
                Objects.equals(flowTotal1, that.flowTotal1) &&
                Objects.equals(flowTotal2, that.flowTotal2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ctime, deviceId, flowRate1, flowRate2, flowTotal1, flowTotal2);
    }
}
