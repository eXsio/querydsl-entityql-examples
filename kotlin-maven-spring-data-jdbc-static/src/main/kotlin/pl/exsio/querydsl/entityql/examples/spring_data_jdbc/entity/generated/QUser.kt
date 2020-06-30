package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated

import com.querydsl.core.dml.StoreClause
import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.BooleanPath
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import com.querydsl.sql.ForeignKey
import com.querydsl.sql.PrimaryKey
import java.util.ArrayList
import java.util.Arrays
import java.util.Date
import javax.annotation.Generated
import pl.exsio.querydsl.entityql.QColumnMetadataFactory
import pl.exsio.querydsl.entityql.QPathConfig
import pl.exsio.querydsl.entityql.QPathFactory
import pl.exsio.querydsl.entityql.QStaticModel
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Order
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.User
import pl.exsio.querydsl.entityql.path.QEnumPath
import pl.exsio.querydsl.entityql.path.QObjectPath

/**
 *
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql). It is not
 * recommended to make any changes to this class. Any manual changes will be lost upon the next
 * class generation.
 */
@Generated("pl.exsio.querydsl.entityql.QExporter")
class QUser : QStaticModel<User<*>> {

  companion object {
    val instance: QUser = QUser()
    val qUser: QUser = QUser.instance
  }

  lateinit var id: NumberPath<Long>

  lateinit var name: StringPath

  lateinit var typeStr: QEnumPath<UserTypeByName>

  lateinit var typeOrd: QEnumPath<UserTypeByOrdinal>

  lateinit var createdBy: QObjectPath<Any>

  lateinit var createdAt: QObjectPath<Date>

  lateinit var enabled: BooleanPath

  lateinit var orderId: NumberPath<Long>

  lateinit var order: ForeignKey<Order>

  lateinit var _primaryKey: PrimaryKey<User<*>>

  constructor() : this("USERS")

  constructor(variable: String) : super(User::class.java, variable, "", "USERS") {

    // id
    run {
      val config = QPathConfig(Long::class.java, Long::class.java, "USER_ID", true, 1, 1111)

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

    // typeStr
    run {
      val config =
          QPathConfig(UserTypeByName::class.java, Enum::class.java, "TYPE_STR", true, 4, 12)

      this.typeStr = QPathFactory.create<QEnumPath<UserTypeByName>>(this, config)

      addMetadata(this.typeStr, QColumnMetadataFactory.create(config))
      this.columnsMap.put("typeStr", this.typeStr)
    }

    // typeOrd
    run {
      val config =
          QPathConfig(UserTypeByOrdinal::class.java, Enum::class.java, "TYPE_ORD", true, 5, 12)

      this.typeOrd = QPathFactory.create<QEnumPath<UserTypeByOrdinal>>(this, config)

      addMetadata(this.typeOrd, QColumnMetadataFactory.create(config))
      this.columnsMap.put("typeOrd", this.typeOrd)
    }

    // createdBy
    run {
      val config = QPathConfig(Object::class.java, Object::class.java, "CREATED_BY", true, 6, 1111)

      this.createdBy = QPathFactory.create<QObjectPath<Any>>(this, config)

      addMetadata(this.createdBy, QColumnMetadataFactory.create(config))
      this.columnsMap.put("createdBy", this.createdBy)
    }

    // createdAt
    run {
      val config = QPathConfig(Date::class.java, Object::class.java, "CREATED_AT", true, 7, 1111)

      this.createdAt = QPathFactory.create<QObjectPath<Date>>(this, config)

      addMetadata(this.createdAt, QColumnMetadataFactory.create(config))
      this.columnsMap.put("createdAt", this.createdAt)
    }

    // enabled
    run {
      val config = QPathConfig(Boolean::class.java, Boolean::class.java, "ENABLED", true, 8, 1111)

      this.enabled = QPathFactory.create<BooleanPath>(this, config)

      addMetadata(this.enabled, QColumnMetadataFactory.create(config))
      this.columnsMap.put("enabled", this.enabled)
    }

    // orderId
    run {
      val config = QPathConfig(Long::class.java, Long::class.java, "ORDER_ID", true, 3, 1111)

      this.orderId = QPathFactory.create<NumberPath<Long>>(this, config)

      addMetadata(this.orderId, QColumnMetadataFactory.create(config))
      this.columnsMap.put("orderId", this.orderId)
    }

    // order
    run {
      this.order = this.createForeignKey<Order>(this.orderId, "ORDER_ID")

      this.joinColumnsMap.put("order", this.order)
    }

    // _primaryKey
    run {
      val list = mutableListOf<Path<*>>(this.id)

      this.primaryKeyColumns = list
      this._primaryKey = this.createPrimaryKey(*list.toTypedArray())
    }
  }
}
