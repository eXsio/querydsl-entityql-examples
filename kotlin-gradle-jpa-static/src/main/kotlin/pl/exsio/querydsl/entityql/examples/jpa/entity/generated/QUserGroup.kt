 package pl.exsio.querydsl.entityql.examples.jpa.entity.generated

 import com.querydsl.sql.PrimaryKey
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory
 import pl.exsio.querydsl.entityql.QPathConfig
 import pl.exsio.querydsl.entityql.QPathFactory
 import pl.exsio.querydsl.entityql.QStaticModel
 import com.querydsl.sql.ForeignKey
 import pl.exsio.querydsl.entityql.examples.jpa.entity.UserGroup
 import com.querydsl.core.types.dsl.NumberPath
 import pl.exsio.querydsl.entityql.examples.jpa.entity.Group
 import pl.exsio.querydsl.entityql.examples.jpa.entity.User
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
 class QUserGroup : QStaticModel<UserGroup> {

     companion object {
         val instance: QUserGroup = QUserGroup()
         val qUserGroup: QUserGroup = QUserGroup.instance
     }

     val groupId: NumberPath <Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "GROUP_ID", true, 3, -5)
         val groupId = QPathFactory.create<NumberPath <Long>>(this, config)
         addMetadata(groupId, QColumnMetadataFactory.create(config))
         this.columnsMap.put("groupId", groupId)
         groupId
     }

     val userId: NumberPath <Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "USER_ID", true, 4, -5)
         val userId = QPathFactory.create<NumberPath <Long>>(this, config)
         addMetadata(userId, QColumnMetadataFactory.create(config))
         this.columnsMap.put("userId", userId)
         userId
     }

     val group: ForeignKey<Group> = run {
         val group = this.createForeignKey<Group>(this.groupId, "GROUP_ID")
         this.joinColumnsMap.put("group", group)
         group
     }

     val user: ForeignKey<User<*>> = run {
         val user = this.createForeignKey<User<*>>(this.userId, "USER_ID")
         this.joinColumnsMap.put("user", user)
         user
     }

     val _primaryKey: PrimaryKey<UserGroup> = run {
         val list = mutableListOf<Path<*>>(this.groupId, this.userId)
         this.primaryKeyColumns = list
         this.createPrimaryKey(*list.toTypedArray())
     }

     constructor(): this("USERS_GROUPS")

     constructor(variable: String): super(UserGroup::class.java, variable, "", "USERS_GROUPS")
 } 