package pl.exsio.querydsl.entityql.examples.jpa.example.dynamic;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositeFk;
import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositePk;
import pl.exsio.querydsl.entityql.examples.jpa.entity.SingularPk;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

@Service
class QJPACompositeFkDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QJPACompositeFkDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntityBasedOnCompositeFKJoinToCompositePK() {

        Q<CompositePk> compositePk = EntityQL.qEntity(CompositePk.class);
        Q<CompositeFk> compositeFk = EntityQL.qEntity(CompositeFk.class);

        List<CompositePk> pks = queryFactory.query()
                .select(
                        constructor(
                                CompositePk.class,
                                compositePk.longNumber("id1"),
                                compositePk.string("id2"),
                                compositePk.string("desc")
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.<CompositePk>joinColumn("compositePk"), compositePk)
                .where(compositeFk.string("desc").eq("fkd2"))
                .fetch();

        System.out.println(pks);
    }


    private void getAllRowsFromAnEntityBasedOnCompositeFKJoinToSingularPK() {

        Q<SingularPk> singularPk = EntityQL.qEntity(SingularPk.class);
        Q<CompositeFk> compositeFk = EntityQL.qEntity(CompositeFk.class);

        List<SingularPk> pks = queryFactory.query()
                .select(
                        constructor(
                                SingularPk.class,
                                singularPk.longNumber("id1"),
                                singularPk.string("id2"),
                                singularPk.string("desc")
                        ))
                .from(compositeFk)
                .innerJoin(compositeFk.<SingularPk>joinColumn("singularPk"), singularPk)
                .where(compositeFk.string("desc").eq("fkd2"))
                .fetch();

        System.out.println(pks);

    }

    @Override
    public void run() {
        getAllRowsFromAnEntityBasedOnCompositeFKJoinToCompositePK();
        getAllRowsFromAnEntityBasedOnCompositeFKJoinToSingularPK();
    }
}
