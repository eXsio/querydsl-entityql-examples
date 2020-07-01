package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

import javax.annotation.Nonnull

class UploadedFile {

    @Id
    @Column("FILE_ID")
    @Nonnull
    private final UUID id
    @Nonnull
    private final byte[] data

    UploadedFile(byte[] data, UUID id) {
        this.id = id
        this.data = data
    }

    UUID getId() {
        id
    }

    byte[] getData() {
        data
    }
}
