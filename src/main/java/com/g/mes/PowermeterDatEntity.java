package com.g.mes;

import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "powermeter_dat", schema = "dbo", catalog = "MES")
@DynamicInsert
@DynamicUpdate
@ToString
public class PowermeterDatEntity {
    private Integer id;
    private LocalDateTime ctime;
    private Integer deviceId;
    private Float vlnA;
    private Float vlnB;
    private Float vlnC;
    private Float vlnAvg;
    private Float vllAb;
    private Float vllBc;
    private Float vllCa;
    private Float vllAvg;
    private Float iA;
    private Float iB;
    private Float iC;
    private Float iAvg;
    private Float frequency;
    private Float kwTot;
    private Float kvarTot;
    private Float kvaTot;
    private Float pf;
    private Float kwh;
    private Float kvarh;
    private Float kvah;

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
    @Column(name = "vln_a")
    public Float getVlnA() {
        return vlnA;
    }

    public void setVlnA(Float vlnA) {
        this.vlnA = vlnA;
    }

    @Basic
    @Column(name = "vln_b")
    public Float getVlnB() {
        return vlnB;
    }

    public void setVlnB(Float vlnB) {
        this.vlnB = vlnB;
    }

    @Basic
    @Column(name = "vln_c")
    public Float getVlnC() {
        return vlnC;
    }

    public void setVlnC(Float vlnC) {
        this.vlnC = vlnC;
    }

    @Basic
    @Column(name = "vln_avg")
    public Float getVlnAvg() {
        return vlnAvg;
    }

    public void setVlnAvg(Float vlnAvg) {
        this.vlnAvg = vlnAvg;
    }

    @Basic
    @Column(name = "vll_ab")
    public Float getVllAb() {
        return vllAb;
    }

    public void setVllAb(Float vllAb) {
        this.vllAb = vllAb;
    }

    @Basic
    @Column(name = "vll_bc")
    public Float getVllBc() {
        return vllBc;
    }

    public void setVllBc(Float vllBc) {
        this.vllBc = vllBc;
    }

    @Basic
    @Column(name = "vll_ca")
    public Float getVllCa() {
        return vllCa;
    }

    public void setVllCa(Float vllCa) {
        this.vllCa = vllCa;
    }

    @Basic
    @Column(name = "vll_avg")
    public Float getVllAvg() {
        return vllAvg;
    }

    public void setVllAvg(Float vllAvg) {
        this.vllAvg = vllAvg;
    }

    @Basic
    @Column(name = "i_a")
    public Float getiA() {
        return iA;
    }

    public void setiA(Float iA) {
        this.iA = iA;
    }

    @Basic
    @Column(name = "i_b")
    public Float getiB() {
        return iB;
    }

    public void setiB(Float iB) {
        this.iB = iB;
    }

    @Basic
    @Column(name = "i_c")
    public Float getiC() {
        return iC;
    }

    public void setiC(Float iC) {
        this.iC = iC;
    }

    @Basic
    @Column(name = "i_avg")
    public Float getiAvg() {
        return iAvg;
    }

    public void setiAvg(Float iAvg) {
        this.iAvg = iAvg;
    }

    @Basic
    @Column(name = "frequency")
    public Float getFrequency() {
        return frequency;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    @Basic
    @Column(name = "kw_tot")
    public Float getKwTot() {
        return kwTot;
    }

    public void setKwTot(Float kwTot) {
        this.kwTot = kwTot;
    }

    @Basic
    @Column(name = "kvar_tot")
    public Float getKvarTot() {
        return kvarTot;
    }

    public void setKvarTot(Float kvarTot) {
        this.kvarTot = kvarTot;
    }

    @Basic
    @Column(name = "kva_tot")
    public Float getKvaTot() {
        return kvaTot;
    }

    public void setKvaTot(Float kvaTot) {
        this.kvaTot = kvaTot;
    }

    @Basic
    @Column(name = "pf")
    public Float getPf() {
        return pf;
    }

    public void setPf(Float pf) {
        this.pf = pf;
    }

    @Basic
    @Column(name = "kwh")
    public Float getKwh() {
        return kwh;
    }

    public void setKwh(Float kwh) {
        this.kwh = kwh;
    }

    @Basic
    @Column(name = "kvarh")
    public Float getKvarh() {
        return kvarh;
    }

    public void setKvarh(Float kvarh) {
        this.kvarh = kvarh;
    }

    @Basic
    @Column(name = "kvah")
    public Float getKvah() {
        return kvah;
    }

    public void setKvah(Float kvah) {
        this.kvah = kvah;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowermeterDatEntity that = (PowermeterDatEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ctime, that.ctime) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(vlnA, that.vlnA) &&
                Objects.equals(vlnB, that.vlnB) &&
                Objects.equals(vlnC, that.vlnC) &&
                Objects.equals(vlnAvg, that.vlnAvg) &&
                Objects.equals(vllAb, that.vllAb) &&
                Objects.equals(vllBc, that.vllBc) &&
                Objects.equals(vllCa, that.vllCa) &&
                Objects.equals(vllAvg, that.vllAvg) &&
                Objects.equals(iA, that.iA) &&
                Objects.equals(iB, that.iB) &&
                Objects.equals(iC, that.iC) &&
                Objects.equals(iAvg, that.iAvg) &&
                Objects.equals(frequency, that.frequency) &&
                Objects.equals(kwTot, that.kwTot) &&
                Objects.equals(kvarTot, that.kvarTot) &&
                Objects.equals(kvaTot, that.kvaTot) &&
                Objects.equals(pf, that.pf) &&
                Objects.equals(kwh, that.kwh) &&
                Objects.equals(kvarh, that.kvarh) &&
                Objects.equals(kvah, that.kvah);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ctime, deviceId, vlnA, vlnB, vlnC, vlnAvg, vllAb, vllBc, vllCa, vllAvg, iA, iB, iC, iAvg, frequency, kwTot, kvarTot, kvaTot, pf, kwh, kvarh, kvah);
    }
}
