package org.docksidestage.javatry.basic.st6.dbms;

// done mayukorin 抽象クラスの名前を考えるときは、今は存在しないけど追加するとしたらこれ、という具象クラスを想像してみよう by jflute (2024/10/08)
// MySQL, PostgreSQL以外の3つ目の具象クラス、どんなものがあるだろうか？
// GraphQL とか？
// PagingQueryの仕様があるのは SQL だけじゃなさそう
// SQL よりも大きな枠組みに「クエリ言語」という概念がある
// 「クエリ言語」自体にPagingQueryの仕様があると考えて良いかも
// なので抽象クラスの名前 SQL → QL にしてみる？
// done mayukorin [ふぉろー] 考えてくださりありがとうございます。考えること自体がトレーニングなのでOKです by jflute (2024/10/14)
// ある程度MySQLとPostgreSQLを知らないとわからないことではあって、MySQLとかPostgreSQLでググってそれが何であるのか？
// 「MySQL PostgreSQL 何？」でググってみると、GoogleのAIが「どちらもリレーショナルデータベース管理システム（RDBMS）として」って言います。
// そして「RDBMS 種類」でググってみると、「MySQL、PostgreSQL、MariaDB、Microsoft SQL Server、Oracle Database、 IBM DB2」と言います。
// 「たまたま名前にSQLという言葉が入ってる2つの具象クラス」しかなかったわけですが、
// その概念を紐解くとMySQLとPostgreSQLは、SQLではなくDBなんですね。
// DBという言葉が抽象的なので、製品の違いを意識するときはよく「DBMS」と呼ばれます。
// 実は、このクラスのpackageはdbmsパッケージだったりします。
// なるほど...!! 確かに、MySQL PostgreSQLは、SQL というよりは DBMS の概念ですよね！
// パッケージ名もヒントになっていたとは...教えていただきありがとうございます！ by m.inoue (2024/10/16)
/**
 * The object for QL (Query Language).
 * @author mayukorin
 */
public abstract class St6QL {

    // done mayukorin [いいね] ちゃんと流れが再利用できているので素晴らしい by jflute (2024/10/08)
    // たかだか二行だけど、この流れが変わった時に一箇所だけ修正すれば済むようにできている。
    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = calcPagingOffset(pageSize, pageNumber);
        return getPagingQL(pageSize, offset);
    }

    // done mayukorin [いいね] 良いメソッド！ by jflute (2024/10/08)
    private int calcPagingOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }

    protected abstract String getPagingQL(int pageSize, int offset);
}
