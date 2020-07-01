package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "UPLOADED_FILES")
class UploadedFile {

    @Id
    @Column(name = "FILE_ID", nullable = false, columnDefinition = "UUID")
    @GeneratedValue
    private UUID id

    @Lob
    @Column(name = "DATA", nullable =  false)
    private byte[] data

    UploadedFile() {
    }

    UploadedFile(byte[] data, UUID id) {
        this.id = id
        this.data = data
    }

    UUID getId() {
        id
    }

    void setId(UUID id) {
        this.id = id
    }

    byte[] getData() {
        data
    }

    void setData(byte[] data) {
        this.data = data
    }
}
