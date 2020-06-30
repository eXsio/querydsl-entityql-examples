package pl.exsio.querydsl.entityql.examples.jpa.example.generated

import com.querydsl.sql.SQLQueryFactory
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositePk
import pl.exsio.querydsl.entityql.examples.jpa.entity.SingularPk
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QCompositeFk
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QCompositePk
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QSingularPk

import java.util.List

import static com.querydsl.core.types.Projections.constructor

@Service
class QJPACompositeFkGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory

    QJPACompositeFkGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory
    }

    private void getAllRowsFromAnEntityBasedOnCompositeFKJoinToCompositePK() {

        QCompositePk compositePk = QCompositePk.INSTANCE
        QCompositeFk compositeFk = QCompositeFk.INSTANCE

        List<CompositePk> pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk.class,
                                compositePk.id1,
                                compositePk.id2,
                                compositePk.desc
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.compositePk, compositePk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()

        System.out.println(pks)
    }


    private void getAllRowsFromAnEntityBasedOnCompositeFKJoinToSingularPK() {

        QSingularPk singularPk = QSingularPk.INSTANCE
        QCompositeFk compositeFk = QCompositeFk.INSTANCE

        List<SingularPk> pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk.class,
                                singularPk.id1,
                                singularPk.id2,
                                singularPk.desc
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.singularPk, singularPk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()

        System.out.println(pks)

    }


    private void getAllRowsFromAnEntityBasedOnInverseCompositeFKJoinToCompositePK() {

        QCompositePk compositePk = QCompositePk.INSTANCE
        QCompositeFk compositeFk = QCompositeFk.INSTANCE


        List<CompositePk> pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk.class,
                                compositePk.id1,
                                compositePk.id2,
                                compositeFk.desc
                        ))
                .from(compositePk)
                .innerJoin(compositePk.compositeFks, compositeFk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()

        System.out.println(pks)
    }

    private void getAllRowsFromAnEntityBasedOnInverseCompositeFKJoinToSingularPK() {

        QSingularPk singularPk = QSingularPk.INSTANCE
        QCompositeFk compositeFk = QCompositeFk.INSTANCE

        List<SingularPk> pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk.class,
                                singularPk.id1,
                                singularPk.id2,
                                compositeFk.desc
                        ))
                .from(singularPk)
                .innerJoin(singularPk.compositeFks, compositeFk)
                .where(compositeFk.desc.eq("fkd2"))
                .fetch()

        System.out.println(pks)

    }

    @Override
    void run() {
        getAllRowsFromAnEntityBasedOnCompositeFKJoinToCompositePK()
        getAllRowsFromAnEntityBasedOnCompositeFKJoinToSingularPK()
        getAllRowsFromAnEntityBasedOnInverseCompositeFKJoinToCompositePK()
        getAllRowsFromAnEntityBasedOnInverseCompositeFKJoinToSingularPK()
    }
}
