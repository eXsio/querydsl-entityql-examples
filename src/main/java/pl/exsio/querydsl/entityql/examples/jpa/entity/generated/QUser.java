package pl.exsio.querydsl.entityql.examples.jpa.entity.generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.PrimaryKey;
import pl.exsio.querydsl.entityql.QColumnMetadataFactory;
import pl.exsio.querydsl.entityql.QPathConfig;
import pl.exsio.querydsl.entityql.QPathFactory;
import pl.exsio.querydsl.entityql.QStaticModel;
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName;
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal;
import pl.exsio.querydsl.entityql.examples.jpa.entity.User;
import pl.exsio.querydsl.entityql.path.QEnumPath;
import pl.exsio.querydsl.entityql.path.QObjectPath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql). It is not
 * recommended to make any changes to this class. Any manual changes will be lost upon the next
 * class generation.
 */
public final class QUser extends QStaticModel<User> {

  public static final QUser INSTANCE = new QUser();

  public final NumberPath<Long> id;

  public final StringPath name;

  public final QEnumPath<UserTypeByName> typeStr;

  public final QEnumPath<UserTypeByOrdinal> typeOrd;

  public final QObjectPath<Object> createdBy;

  public final QObjectPath<Date> createdAt;

  public final PrimaryKey<User> _primaryKey;

  public QUser() {
    this("USERS");
  }

  @SuppressWarnings(value = "unchecked")
  public QUser(String variable) {
    super(User.class, variable, "", "USERS");

    id:
    {
      QPathConfig config = new QPathConfig(Long.class, Long.class, "USER_ID", true, 1, -5);

      this.id = QPathFactory.<NumberPath<Long>>create(this, config);

      addMetadata(this.id, QColumnMetadataFactory.create(config));
      this.columnsMap.put("id", this.id);
    }

    name:
    {
      QPathConfig config = new QPathConfig(String.class, String.class, "NAME", true, 2, 12);

      this.name = QPathFactory.<StringPath>create(this, config);

      addMetadata(this.name, QColumnMetadataFactory.create(config));
      this.columnsMap.put("name", this.name);
    }

    typeStr:
    {
      QPathConfig config =
          new QPathConfig(UserTypeByName.class, Enum.class, "TYPE_STR", false, 4, 12);

      this.typeStr = QPathFactory.<QEnumPath<UserTypeByName>>create(this, config);

      addMetadata(this.typeStr, QColumnMetadataFactory.create(config));
      this.columnsMap.put("typeStr", this.typeStr);
    }

    typeOrd:
    {
      QPathConfig config =
          new QPathConfig(UserTypeByOrdinal.class, Enum.class, "TYPE_ORD", false, 5, 12);

      this.typeOrd = QPathFactory.<QEnumPath<UserTypeByOrdinal>>create(this, config);

      addMetadata(this.typeOrd, QColumnMetadataFactory.create(config));
      this.columnsMap.put("typeOrd", this.typeOrd);
    }

    createdBy:
    {
      QPathConfig config = new QPathConfig(Object.class, Object.class, "CREATED_BY", true, 6, 1111);

      this.createdBy = QPathFactory.<QObjectPath<Object>>create(this, config);

      addMetadata(this.createdBy, QColumnMetadataFactory.create(config));
      this.columnsMap.put("createdBy", this.createdBy);
    }

    createdAt:
    {
      QPathConfig config = new QPathConfig(Date.class, Object.class, "CREATED_AT", true, 7, 1111);

      this.createdAt = QPathFactory.<QObjectPath<Date>>create(this, config);

      addMetadata(this.createdAt, QColumnMetadataFactory.create(config));
      this.columnsMap.put("createdAt", this.createdAt);
    }

    _primaryKey:
    {
      List<Path> paths = new ArrayList();

      paths.add(this.id);

      this._primaryKey = this.<User>createPrimaryKey(paths.<Path>toArray(new Path[0]));
    }
  }
}
