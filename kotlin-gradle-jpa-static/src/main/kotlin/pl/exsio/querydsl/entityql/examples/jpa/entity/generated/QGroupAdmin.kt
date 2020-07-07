 package pl.exsio.querydsl.entityql.examples.jpa.entity.generated

 import com.querydsl.sql.PrimaryKey
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory
 import pl.exsio.querydsl.entityql.QPathConfig
 import pl.exsio.querydsl.entityql.QPathFactory
 import pl.exsio.querydsl.entityql.QStaticModel
 import pl.exsio.querydsl.entityql.examples.jpa.entity.GroupAdmin
 import com.querydsl.core.types.dsl.NumberPath
 import com.querydsl.core.types.dsl.StringPath
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
 class QGroupAdmin : QStaticModel<GroupAdmin> {

     companion object {
         val instance: QGroupAdmin = QGroupAdmin()
         val qGroupAdmin: QGroupAdmin = QGroupAdmin.instance
     }

     val id: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "GA_ID", true, 1, -5)
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

     val _primaryKey: PrimaryKey<GroupAdmin> = run {
         val list = mutableListOf<Path<*>>(this.id)
         this.primaryKeyColumns = list
         this.createPrimaryKey(*list.toTypedArray())
     }

     constructor(): this("GROUP_ADMINS")

     constructor(variable: String): super(GroupAdmin::class.java, variable, "", "GROUP_ADMINS")
 } 