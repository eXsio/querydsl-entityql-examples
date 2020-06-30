package pl.exsio.querydsl.entityql.examples.jpa.entity.generated

import com.querydsl.core.dml.StoreClause
import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import com.querydsl.sql.ForeignKey
import com.querydsl.sql.PrimaryKey
import java.util.ArrayList
import java.util.Arrays
import javax.annotation.Generated
import pl.exsio.querydsl.entityql.QColumnMetadataFactory
import pl.exsio.querydsl.entityql.QPathConfig
import pl.exsio.querydsl.entityql.QPathFactory
import pl.exsio.querydsl.entityql.QStaticModel
import pl.exsio.querydsl.entityql.examples.jpa.entity.Group
import pl.exsio.querydsl.entityql.examples.jpa.entity.GroupAdmin

/**
 *
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql). It is not
 * recommended to make any changes to this class. Any manual changes will be lost upon the next
 * class generation.
 */
@Generated("pl.exsio.querydsl.entityql.QExporter")
class QGroup : QStaticModel<Group> {

  companion object {
    val instance: QGroup = QGroup()
    val qGroup: QGroup = QGroup.instance
  }

  lateinit var id: NumberPath<Long>

  lateinit var name: StringPath

  lateinit var adminId: StringPath

  lateinit var admin: ForeignKey<GroupAdmin>

  lateinit var _primaryKey: PrimaryKey<Group>

  constructor() : this("GROUPS")

  constructor(variable: String) : super(Group::class.java, variable, "", "GROUPS") {

    // id
    run {
      val config = QPathConfig(Long::class.java, Long::class.java, "GROUP_ID", true, 1, -5)

      this.id = QPathFactory.create<NumberPath<Long>>(this, config)

      addMetadata(this.id, QColumnMetadataFactory.create(config))
      this.columnsMap.put("id", this.id)
    }

    // name
    run {
      val config = QPathConfig(String::class.java, String::class.java, "NAME", true, 2, 12)

      this.name = QPathFactory.create<StringPath>(this, config)

      addMetadata(this.name, QColumnMetadataFactory.create(config))
      this.columnsMap.put("name", this.name)
    }

    // adminId
    run {
      val config = QPathConfig(String::class.java, String::class.java, "ADMIN_NAME", false, 4, 12)

      this.adminId = QPathFactory.create<StringPath>(this, config)

      addMetadata(this.adminId, QColumnMetadataFactory.create(config))
      this.columnsMap.put("adminId", this.adminId)
    }

    // admin
    run {
      this.admin = this.createForeignKey<GroupAdmin>(this.adminId, "NAME")

      this.joinColumnsMap.put("admin", this.admin)
    }

    // _primaryKey
    run {
      val list = mutableListOf<Path<*>>(this.id)

      this.primaryKeyColumns = list
      this._primaryKey = this.createPrimaryKey(*list.toTypedArray())
    }
  }
}
