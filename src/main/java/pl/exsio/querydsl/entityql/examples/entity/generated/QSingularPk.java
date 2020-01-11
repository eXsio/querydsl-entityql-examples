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
public final class QSingularPk
    extends QStaticModel<pl.exsio.querydsl.entityql.examples.entity.SingularPk> {

  public static final QSingularPk INSTANCE = new QSingularPk();

  public final NumberPath<java.lang.Long> id1;

  public final StringPath id2;

  public final StringPath desc;

  public final PrimaryKey<pl.exsio.querydsl.entityql.examples.entity.SingularPk> _primaryKey;

  public QSingularPk() {
    this("SINGULAR_PK");
  }

  @SuppressWarnings(value = "unchecked")
  public QSingularPk(String variable) {
    super(pl.exsio.querydsl.entityql.examples.entity.SingularPk.class, variable, "", "SINGULAR_PK");

    id1:
    {
      this.id1 =
          QPathFactory.<NumberPath<java.lang.Long>>create(
              this,
              new QPathConfig(java.lang.Long.class, java.lang.Long.class, "ID_1", true, 1, -5));

      addMetadata(this.id1, QColumnMetadataFactory.create("ID_1", 1, -5, true));
    }

    id2:
    {
      this.id2 =
          QPathFactory.<StringPath>create(
              this,
              new QPathConfig(java.lang.String.class, java.lang.String.class, "ID_2", true, 2, 12));

      addMetadata(this.id2, QColumnMetadataFactory.create("ID_2", 2, 12, true));
    }

    desc:
    {
      this.desc =
          QPathFactory.<StringPath>create(
              this,
              new QPathConfig(java.lang.String.class, java.lang.String.class, "DESC", true, 3, 12));

      addMetadata(this.desc, QColumnMetadataFactory.create("DESC", 3, 12, true));
    }

    _primaryKey:
    {
      List<Path> paths = new ArrayList();

      paths.add(this.id1);

      this._primaryKey =
          this.<pl.exsio.querydsl.entityql.examples.entity.SingularPk>createPrimaryKey(
              paths.<Path>toArray(new Path[0]));
    }
  }
}
