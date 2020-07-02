package pl.exsio.querydsl.entityql.examples.jpa.example.generated

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositeFk
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositePk
import pl.exsio.querydsl.entityql.examples.jpa.entity.SingularPk
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QCompositeFk
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QCompositePk
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QSingularPk

@Service
class KQJPACompositeFkGeneratedExample(@Autowired val queryFactory: SQLQueryFactory) : Example {
    
    fun getAllRowsFromAnEntityBasedOnACompositeFKJoinToCompositePK() {

        val compositePk = QCompositePk.instance
        val compositeFk = QCompositeFk.instance


        val pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk::class.java,
                                compositePk.id1,
                                compositePk.id2,
                                compositePk.desc
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.compositePk, compositePk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()


        println(pks)
    }

    
    fun getAllRowsFromAnEntityBasedOnACompositeFKJoinToSingularPK() {

        val singularPk = QSingularPk.instance
        val compositeFk = QCompositeFk.instance


        val pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk::class.java,
                                singularPk.id1,
                                singularPk.id2,
                                singularPk.desc
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.singularPk, singularPk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()


        println(pks)
    }

    fun getAllRowsGromAnEntityBasedOnAnInverseCompositeFKJoinToCompositePK() {

        val compositePk = QCompositePk.instance
        val compositeFk = QCompositeFk.instance


        val pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk::class.java,
                                compositePk.id1,
                                compositePk.id2,
                                compositeFk.desc
                        ))
                .from(compositePk)
                .innerJoin(compositePk.compositeFks, compositeFk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()


        println(pks)
    }

    
    fun getAllRowsFromAnEntityBasedOnAnInverseCompositeFKJoinToSingularPK() {

        val singularPk = QSingularPk.instance
        val compositeFk = QCompositeFk.instance


        val pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk::class.java,
                                singularPk.id1,
                                singularPk.id2,
                                compositeFk.desc
                        ))
                .from(singularPk)
                .innerJoin(singularPk.compositeFks, compositeFk)
                .where(compositeFk.desc.eq("fkd2"))
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
