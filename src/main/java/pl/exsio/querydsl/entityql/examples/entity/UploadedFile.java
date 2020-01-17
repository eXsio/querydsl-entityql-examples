package pl.exsio.querydsl.entityql.examples.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "UPLOADED_FILES")
public class UploadedFile {

    @Id
    @Column(name = "FILE_ID", nullable = false, columnDefinition = "UUID")
    @GeneratedValue
    private UUID id;

    @Lob
    @Column(name = "DATA", nullable =  false)
    private byte[] data;

    public UploadedFile() {
    }

    public UploadedFile(byte[] data, UUID id) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "UploadedFile{" +
                "id=" + id +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
