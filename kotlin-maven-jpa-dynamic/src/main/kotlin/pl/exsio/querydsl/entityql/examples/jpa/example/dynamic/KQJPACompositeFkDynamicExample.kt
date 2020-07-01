package pl.exsio.querydsl.entityql.examples.jpa.example.dynamic

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositeFk
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositePk
import pl.exsio.querydsl.entityql.examples.jpa.entity.SingularPk

@Service
class KQJPACompositeFkDynamicExample(@Autowired var queryFactory: SQLQueryFactory) : Example {
    
    fun getAllRowsFromAnEntityBasedOnACompositeFKJoinToCompositePK() {

        val compositePk = qEntity(CompositePk::class.java)
        val compositeFk = qEntity(CompositeFk::class.java)


        val pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk::class.java,
                                compositePk.longNumber("id1"),
                                compositePk.string("id2"),
                                compositePk.string("desc")
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.joinColumn<CompositePk>("compositePk"), compositePk)
                .where(compositeFk.string("desc").eq("fkd2"))
                .fetch()


        println(pks)
    }

    
    fun getAllRowsFromAnEntityBasedOnACompositeFKJoinToSingularPK() {

        val singularPk = qEntity(SingularPk::class.java)
        val compositeFk = qEntity(CompositeFk::class.java)


        val pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk::class.java,
                                singularPk.longNumber("id1"),
                                singularPk.string("id2"),
                                singularPk.string("desc")
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.joinColumn<SingularPk>("singularPk"), singularPk)
                .where(compositeFk.string("desc").eq("fkd2"))
                .fetch()


        println(pks)
    }

    fun getAllRowsGromAnEntityBasedOnAnInverseCompositeFKJoinToCompositePK() {

        val compositePk = qEntity(CompositePk::class.java)
        val compositeFk = qEntity(CompositeFk::class.java)


        val pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk::class.java,
                                compositePk.longNumber("id1"),
                                compositePk.string("id2"),
                                compositeFk.string("desc")
                        ))
                .from(compositePk)
                .innerJoin(compositePk.inverseJoinColumn<CompositeFk>("compositeFks"), compositeFk)
                .where(compositeFk.string("desc").eq("fkd2"))
                .fetch()


        println(pks)
    }

    
    fun getAllRowsFromAnEntityBasedOnAnInverseCompositeFKJoinToSingularPK() {

        val singularPk = qEntity(SingularPk::class.java)
        val compositeFk = qEntity(CompositeFk::class.java)


        val pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk::class.java,
                                singularPk.longNumber("id1"),
                                singularPk.string("id2"),
                                compositeFk.string("desc")
                        ))
                .from(singularPk)
                .innerJoin(singularPk.inverseJoinColumn<CompositeFk>("compositeFks"), compositeFk)
                .where(compositeFk.string("desc").eq("fkd2"))
                .fetch()


        println(pks)
    }

    override fun run() {
        getAllRowsFromAnEntityBasedOnACompositeFKJoinToCompositePK()
        getAllRowsFromAnEntityBasedOnACompositeFKJoinToSingularPK()
        getAllRowsFromAnEntityBasedOnAnInverseCompositeFKJoinToSingularPK()
        getAllRowsGromAnEntityBasedOnAnInverseCompositeFKJoinToCompositePK()
    }

}
