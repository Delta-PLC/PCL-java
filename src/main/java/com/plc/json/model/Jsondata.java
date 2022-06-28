package com.plc.json.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="json_data")

@NoArgsConstructor
@AllArgsConstructor
public class Jsondata {
    @Id
  //  @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String ipAddress;
    private Long status;
    private Long actualTimer;
    private Long setTimer;
    private Date datetime;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @PrePersist
    private void Createdate() {
        datetime = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String ip()
    {
        return (""+ipAddress);
    }
    public String st()
    {
        return (""+status);
    }
    public String att()
    {
        return (" "+actualTimer);
    }
    public String stt()
    {
        return (""+setTimer);
    }
}
