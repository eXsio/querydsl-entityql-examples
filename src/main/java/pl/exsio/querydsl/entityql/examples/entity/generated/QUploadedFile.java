package pl.exsio.querydsl.entityql.examples.entity.generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import com.querydsl.sql.*;
import java.util.ArrayList;
import java.util.List;
import pl.exsio.querydsl.entityql.*;
import pl.exsio.querydsl.entityql.ex.*;
import pl.exsio.querydsl.entityql.path.*;
import pl.exsio.querydsl.entityql.type.*;

/**
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql). It is not
 * recommended to make any changes to this class. Any manual changes will be lost upon the next
 * class generation.
 */
public final class QUploadedFile
    extends QStaticModel<pl.exsio.querydsl.entityql.examples.entity.UploadedFile> {

  public static final QUploadedFile INSTANCE = new QUploadedFile();

  public final QUuidPath id;

  public final ArrayPath<byte[], java.lang.Byte> data;

  public final PrimaryKey<pl.exsio.querydsl.entityql.examples.entity.UploadedFile> _primaryKey;

  public QUploadedFile() {
    this("UPLOADED_FILES");
  }

  @SuppressWarnings(value = "unchecked")
  public QUploadedFile(String variable) {
    super(
        pl.exsio.querydsl.entityql.examples.entity.UploadedFile.class,
        variable,
        "",
        "UPLOADED_FILES");

    id:
    {
      QPathConfig config =
          new QPathConfig(java.util.UUID.class, java.util.UUID.class, "FILE_ID", false, 1, 12);

      this.id = QPathFactory.<QUuidPath>create(this, config);

      addMetadata(this.id, QColumnMetadataFactory.create(config));
    }

    data:
    {
      QPathConfig config =
          new QPathConfig(byte[].class, java.lang.reflect.Array.class, "DATA", false, 2, 2003);

      this.data = QPathFactory.<ArrayPath<byte[], java.lang.Byte>>create(this, config);

      addMetadata(this.data, QColumnMetadataFactory.create(config));
    }

    _primaryKey:
    {
      List<Path> paths = new ArrayList();

      paths.add(this.id);

      this._primaryKey =
          this.<pl.exsio.querydsl.entityql.examples.entity.UploadedFile>createPrimaryKey(
              paths.<Path>toArray(new Path[0]));
    }
  }
}
