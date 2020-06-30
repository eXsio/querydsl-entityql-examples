package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.math.BigDecimal

class Book(@Id @Column("BOOK_ID") var id: Long,
           var name: String,
           var desc: String?,
           var price: BigDecimal
)
