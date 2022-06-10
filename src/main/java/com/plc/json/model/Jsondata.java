package com.plc.json.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="json_data")

@NoArgsConstructor
@AllArgsConstructor
public class Jsondata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ipAddress;
    private long status;
    private long actualTimer;
    private long setTimer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getActualTimer() {
        return actualTimer;
    }

    public void setActualTimer(long actualTimer) {
        this.actualTimer = actualTimer;
    }

    public long getSetTimer() {
        return setTimer;
    }

    public void setSetTimer(long setTimer) {
        this.setTimer = setTimer;
    }

    @Override
    public String toString() {
        return "Jsondata{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", status=" + status +
                ", actualTimer=" + actualTimer +
                ", setTimer=" + setTimer +
                '}';
    }
}
