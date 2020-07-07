 package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated

 import com.querydsl.sql.PrimaryKey
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory
 import pl.exsio.querydsl.entityql.QPathConfig
 import pl.exsio.querydsl.entityql.QPathFactory
 import pl.exsio.querydsl.entityql.QStaticModel
 import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.UploadedFile
 import pl.exsio.querydsl.entityql.path.QUuidPath
 import java.util.UUID
 import com.querydsl.core.types.dsl.ArrayPath
 import java.lang.reflect.Array
 import javax.annotation.Generated
 import com.querydsl.core.types.Path
 

 /**
 *
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql).
 * It is not recommended to make any changes to this class.
 * Any manual changes will be lost upon the next class generation.
 *
 */
 @Generated("pl.exsio.querydsl.entityql.QExporter")
 class QUploadedFile : QStaticModel<UploadedFile> {

     companion object {
         val instance: QUploadedFile = QUploadedFile()
         val qUploadedFile: QUploadedFile = QUploadedFile.instance
     }

     val id: QUuidPath = run {
         val config = QPathConfig(UUID::class.java, UUID::class.java, "FILE_ID", true, 1, 12)
         val id = QPathFactory.create<QUuidPath>(this, config)
         addMetadata(id, QColumnMetadataFactory.create(config))
         this.columnsMap.put("id", id)
         id
     }

     val data: ArrayPath<ByteArray, Byte> = run {
         val config = QPathConfig(ByteArray::class.java, Array::class.java, "DATA", true, 2, 2003)
         val data = QPathFactory.create<ArrayPath<ByteArray, Byte>>(this, config)
         addMetadata(data, QColumnMetadataFactory.create(config))
         this.columnsMap.put("data", data)
         data
     }

     val _primaryKey: PrimaryKey<UploadedFile> = run {
         val list = mutableListOf<Path<*>>(this.id)
         this.primaryKeyColumns = list
         this.createPrimaryKey(*list.toTypedArray())
     }

     constructor(): this("UPLOADED_FILES")

     constructor(variable: String): super(UploadedFile::class.java, variable, "", "UPLOADED_FILES")
 } 