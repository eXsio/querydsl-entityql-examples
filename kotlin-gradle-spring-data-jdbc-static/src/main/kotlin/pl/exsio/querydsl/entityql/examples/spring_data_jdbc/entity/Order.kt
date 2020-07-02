package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class Order(@Id @Column("ORDER_ID") var id: Long, var user: User<*>, var items: List<OrderItem>)
