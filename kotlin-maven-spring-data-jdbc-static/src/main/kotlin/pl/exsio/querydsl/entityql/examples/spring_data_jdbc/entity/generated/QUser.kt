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

  val id: NumberPath<Long> =
      run {
        val config = QPathConfig(Long::class.java, Long::class.java, "USER_ID", true, 1, 1111)

        val id = QPathFactory.create<NumberPath<Long>>(this, config)

        addMetadata(id, QColumnMetadataFactory.create(config))
        this.columnsMap.put("id", id)
        id
      }

  val name: StringPath =
      run {
        val config = QPathConfig(String::class.java, String::class.java, "NAME", true, 2, 12)

        val name = QPathFactory.create<StringPath>(this, config)

        addMetadata(name, QColumnMetadataFactory.create(config))
        this.columnsMap.put("name", name)
        name
      }

  val typeStr: QEnumPath<UserTypeByName> =
      run {
        val config =
            QPathConfig(UserTypeByName::class.java, Enum::class.java, "TYPE_STR", true, 4, 12)

        val typeStr = QPathFactory.create<QEnumPath<UserTypeByName>>(this, config)

        addMetadata(typeStr, QColumnMetadataFactory.create(config))
        this.columnsMap.put("typeStr", typeStr)
        typeStr
      }

  val typeOrd: QEnumPath<UserTypeByOrdinal> =
      run {
        val config =
            QPathConfig(UserTypeByOrdinal::class.java, Enum::class.java, "TYPE_ORD", true, 5, 12)

        val typeOrd = QPathFactory.create<QEnumPath<UserTypeByOrdinal>>(this, config)

        addMetadata(typeOrd, QColumnMetadataFactory.create(config))
        this.columnsMap.put("typeOrd", typeOrd)
        typeOrd
      }

  val createdBy: QObjectPath<Any> =
      run {
        val config =
            QPathConfig(Object::class.java, Object::class.java, "CREATED_BY", true, 6, 1111)

        val createdBy = QPathFactory.create<QObjectPath<Any>>(this, config)

        addMetadata(createdBy, QColumnMetadataFactory.create(config))
        this.columnsMap.put("createdBy", createdBy)
        createdBy
      }

  val createdAt: QObjectPath<Date> =
      run {
        val config = QPathConfig(Date::class.java, Object::class.java, "CREATED_AT", true, 7, 1111)

        val createdAt = QPathFactory.create<QObjectPath<Date>>(this, config)

        addMetadata(createdAt, QColumnMetadataFactory.create(config))
        this.columnsMap.put("createdAt", createdAt)
        createdAt
      }

  val enabled: BooleanPath =
      run {
        val config = QPathConfig(Boolean::class.java, Boolean::class.java, "ENABLED", true, 8, 1111)

        val enabled = QPathFactory.create<BooleanPath>(this, config)

        addMetadata(enabled, QColumnMetadataFactory.create(config))
        this.columnsMap.put("enabled", enabled)
        enabled
      }

  val orderId: NumberPath<Long> =
      run {
        val config = QPathConfig(Long::class.java, Long::class.java, "ORDER_ID", true, 3, 1111)

        val orderId = QPathFactory.create<NumberPath<Long>>(this, config)

        addMetadata(orderId, QColumnMetadataFactory.create(config))
        this.columnsMap.put("orderId", orderId)
        orderId
      }

  val order: ForeignKey<Order> =
      run {
        val order = this.createForeignKey<Order>(this.orderId, "ORDER_ID")

        this.joinColumnsMap.put("order", order)
        order
      }

  val _primaryKey: PrimaryKey<User<*>> =
      run {
        val list = mutableListOf<Path<*>>(this.id)

        this.primaryKeyColumns = list
        this.createPrimaryKey(*list.toTypedArray())
      }

  constructor() : this("USERS")

  constructor(variable: String) : super(User::class.java, variable, "", "USERS")
}
