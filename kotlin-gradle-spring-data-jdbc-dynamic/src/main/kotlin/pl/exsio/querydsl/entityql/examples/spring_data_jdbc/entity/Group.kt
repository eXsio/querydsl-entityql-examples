package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class Group(@Id @Column("GROUP_ID") var id: Long, var name: String, var admin: GroupAdmin?) {

    constructor(id: Long, name: String) : this(id, name, null)
}


