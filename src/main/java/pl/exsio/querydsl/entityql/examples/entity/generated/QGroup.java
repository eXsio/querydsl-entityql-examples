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
public final class QGroup extends QStaticModel<pl.exsio.querydsl.entityql.examples.entity.Group> {

  public static final QGroup INSTANCE = new QGroup();

  public final NumberPath<java.lang.Long> id;

  public final StringPath name;

  public final StringPath adminId;

  public final ForeignKey<pl.exsio.querydsl.entityql.examples.entity.GroupAdmin> admin;

  public final PrimaryKey<pl.exsio.querydsl.entityql.examples.entity.Group> _primaryKey;

  public QGroup() {
    this("GROUPS");
  }

  @SuppressWarnings(value = "unchecked")
  public QGroup(String variable) {
    super(pl.exsio.querydsl.entityql.examples.entity.Group.class, variable, "", "GROUPS");

    id:
    {
      QPathConfig config =
          new QPathConfig(java.lang.Long.class, java.lang.Long.class, "GROUP_ID", true, 1, -5);

      this.id = QPathFactory.<NumberPath<java.lang.Long>>create(this, config);

      addMetadata(this.id, QColumnMetadataFactory.create(config));
    }

    name:
    {
      QPathConfig config =
          new QPathConfig(java.lang.String.class, java.lang.String.class, "NAME", true, 2, 12);

      this.name = QPathFactory.<StringPath>create(this, config);

      addMetadata(this.name, QColumnMetadataFactory.create(config));
    }

    adminId:
    {
      QPathConfig config =
          new QPathConfig(
              java.lang.String.class, java.lang.String.class, "ADMIN_NAME", false, 4, 12);

      this.adminId = QPathFactory.<StringPath>create(this, config);

      addMetadata(this.adminId, QColumnMetadataFactory.create(config));
    }

    admin:
    {
      this.admin =
          this.<pl.exsio.querydsl.entityql.examples.entity.GroupAdmin>createForeignKey(
              this.adminId, "NAME");
    }

    _primaryKey:
    {
      List<Path> paths = new ArrayList();

      paths.add(this.id);

      this._primaryKey =
          this.<pl.exsio.querydsl.entityql.examples.entity.Group>createPrimaryKey(
              paths.<Path>toArray(new Path[0]));
    }
  }
}
