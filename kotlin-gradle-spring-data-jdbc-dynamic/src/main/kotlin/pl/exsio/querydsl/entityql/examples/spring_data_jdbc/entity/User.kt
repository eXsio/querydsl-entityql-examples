package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal
import java.util.*

class User<T>(@Id @Column("USER_ID") var id: Long,
              var name: String,
              var order: Order,
              var typeStr: UserTypeByName,
              var typeOrd: UserTypeByOrdinal,
              var createdBy: T,
              var createdAt: Date,
              var enabled: Boolean
)