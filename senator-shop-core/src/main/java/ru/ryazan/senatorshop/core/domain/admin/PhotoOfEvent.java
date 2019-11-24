package ru.ryazan.senatorshop.core.domain.admin;

import javax.persistence.*;
import java.util.Base64;

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

    @Transient
    private String dataImg;

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
    public String getDataImg() {
        setDataImg();
        return dataImg;
    }

    private void setDataImg() {
        if (fileData != null && fileData.length > 0){
            this.dataImg =  Base64.getEncoder().encodeToString(fileData);
        }
    }
}
