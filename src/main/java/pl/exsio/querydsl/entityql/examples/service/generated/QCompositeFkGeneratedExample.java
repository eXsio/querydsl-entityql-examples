package pl.exsio.querydsl.entityql.examples.service.generated;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.entity.CompositeFk;
import pl.exsio.querydsl.entityql.examples.entity.CompositePk;
import pl.exsio.querydsl.entityql.examples.entity.SingularPk;
import pl.exsio.querydsl.entityql.examples.entity.generated.QCompositeFk;
import pl.exsio.querydsl.entityql.examples.entity.generated.QCompositePk;
import pl.exsio.querydsl.entityql.examples.entity.generated.QSingularPk;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

@Service
class QCompositeFkGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QCompositeFkGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntityBasedOnCompositeFKJoinToCompositePK() {

        QCompositePk compositePk = QCompositePk.INSTANCE;
        QCompositeFk compositeFk = QCompositeFk.INSTANCE;

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
                .fetch();

        System.out.println(pks);
    }


    private void getAllRowsFromAnEntityBasedOnCompositeFKJoinToSingularPK() {

        QSingularPk singularPk = QSingularPk.INSTANCE;
        QCompositeFk compositeFk = QCompositeFk.INSTANCE;

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
                .fetch();

        System.out.println(pks);

    }

    @Override
    public void run() {
        getAllRowsFromAnEntityBasedOnCompositeFKJoinToCompositePK();
        getAllRowsFromAnEntityBasedOnCompositeFKJoinToSingularPK();
    }
}
