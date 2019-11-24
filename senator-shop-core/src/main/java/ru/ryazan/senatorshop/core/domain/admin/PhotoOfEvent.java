package ru.ryazan.senatorshop.core.domain.admin;

import javax.persistence.*;
@Entity
public class PhotoOfEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;

    @ManyToOne
    @JoinColumn(name = "life_events_id")
    private LifeEvents lifeEvents;

    public PhotoOfEvent() {
    }

    public PhotoOfEvent(String fileName, String contentType, byte[] bytes, LifeEvents LifeEvents) {
        this.fileName = fileName;
        this.fileType = contentType;
        this.fileData = bytes;
        this.lifeEvents = LifeEvents;
    }

    public PhotoOfEvent(String fileName, String fileType, byte[] fileData) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public LifeEvents getLifeEvents() {
        return lifeEvents;
    }

    public void setLifeEvents(LifeEvents lifeEvents) {
        this.lifeEvents = lifeEvents;
    }
}
