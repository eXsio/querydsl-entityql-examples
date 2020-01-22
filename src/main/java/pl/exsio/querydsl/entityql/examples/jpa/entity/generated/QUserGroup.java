package pl.exsio.querydsl.entityql.examples.jpa.entity.generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.ForeignKey;
import com.querydsl.sql.PrimaryKey;
import pl.exsio.querydsl.entityql.QColumnMetadataFactory;
import pl.exsio.querydsl.entityql.QPathConfig;
import pl.exsio.querydsl.entityql.QPathFactory;
import pl.exsio.querydsl.entityql.QStaticModel;
import pl.exsio.querydsl.entityql.examples.jpa.entity.Group;
import pl.exsio.querydsl.entityql.examples.jpa.entity.User;
import pl.exsio.querydsl.entityql.examples.jpa.entity.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql). It is not
 * recommended to make any changes to this class. Any manual changes will be lost upon the next
 * class generation.
 */
public final class QUserGroup extends QStaticModel<UserGroup> {

  public static final QUserGroup INSTANCE = new QUserGroup();

  public final NumberPath<Long> groupId;

  public final NumberPath<Long> userId;

  public final ForeignKey<Group> group;

  public final ForeignKey<User> user;

  public final PrimaryKey<UserGroup> _primaryKey;

  public QUserGroup() {
    this("USERS_GROUPS");
  }

  @SuppressWarnings(value = "unchecked")
  public QUserGroup(String variable) {
    super(UserGroup.class, variable, "", "USERS_GROUPS");

    groupId:
    {
      QPathConfig config = new QPathConfig(Long.class, Long.class, "GROUP_ID", true, 3, -5);

      this.groupId = QPathFactory.<NumberPath<Long>>create(this, config);

      addMetadata(this.groupId, QColumnMetadataFactory.create(config));
      this.columnsMap.put("groupId", this.groupId);
    }

    userId:
    {
      QPathConfig config = new QPathConfig(Long.class, Long.class, "USER_ID", true, 4, -5);

      this.userId = QPathFactory.<NumberPath<Long>>create(this, config);

      addMetadata(this.userId, QColumnMetadataFactory.create(config));
      this.columnsMap.put("userId", this.userId);
    }

    group:
    {
      this.group = this.<Group>createForeignKey(this.groupId, "GROUP_ID");
      this.joinColumnsMap.put("group", this.group);
    }

    user:
    {
      this.user = this.<User>createForeignKey(this.userId, "USER_ID");
      this.joinColumnsMap.put("user", this.user);
    }

    _primaryKey:
    {
      List<Path> paths = new ArrayList();

      paths.add(this.groupId);

      paths.add(this.userId);

      this._primaryKey = this.<UserGroup>createPrimaryKey(paths.<Path>toArray(new Path[0]));
    }
  }
}
