 package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated

 import com.querydsl.sql.PrimaryKey
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory
 import pl.exsio.querydsl.entityql.QPathConfig
 import pl.exsio.querydsl.entityql.QPathFactory
 import pl.exsio.querydsl.entityql.QStaticModel
 import com.querydsl.sql.ForeignKey
 import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Group
 import com.querydsl.core.types.dsl.NumberPath
 import com.querydsl.core.types.dsl.StringPath
 import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.GroupAdmin
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
 class QGroup : QStaticModel<Group> {

     companion object {
         val instance: QGroup = QGroup()
         val qGroup: QGroup = QGroup.instance
     }

     val id: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "GROUP_ID", true, 1, 1111)
         val id = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(id, QColumnMetadataFactory.create(config))
         this.columnsMap.put("id", id)
         id
     }

     val name: StringPath = run {
         val config = QPathConfig(String::class.java, String::class.java, "NAME", true, 2, 12)
         val name = QPathFactory.create<StringPath>(this, config)
         addMetadata(name, QColumnMetadataFactory.create(config))
         this.columnsMap.put("name", name)
         name
     }

     val adminId: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "GA_ID", true, 3, 1111)
         val adminId = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(adminId, QColumnMetadataFactory.create(config))
         this.columnsMap.put("adminId", adminId)
         adminId
     }

     val admin: ForeignKey<GroupAdmin> = run {
         val admin = this.createForeignKey<GroupAdmin>(this.adminId, "GA_ID")
         this.joinColumnsMap.put("admin", admin)
         admin
     }

     val _primaryKey: PrimaryKey<Group> = run {
         val list = mutableListOf<Path<*>>(this.id)
         this.primaryKeyColumns = list
         this.createPrimaryKey(*list.toTypedArray())
     }

     constructor(): this("GROUPS")

     constructor(variable: String): super(Group::class.java, variable, "", "GROUPS")
 } 