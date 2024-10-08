package org.docksidestage.javatry.basic.st6.dbms;

// TODO mayukorin 抽象クラスの名前を考えるときは、今は存在しないけど追加するとしたらこれ、という具象クラスを想像してみよう by jflute (2024/10/08)
// MySQL, PostgreSQL以外の3つ目の具象クラス、どんなものがあるだろうか？
/**
 * The object for sql.
 * @author mayukorin
 */
public abstract class St6Sql {

    // TODO mayukorin [いいね] ちゃんと流れが再利用できているので素晴らしい by jflute (2024/10/08)
    // たかだか二行だけど、この流れが変わった時に一箇所だけ修正すれば済むようにできている。
    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = calcPagingOffset(pageSize, pageNumber);
        return getPagingSQL(pageSize, offset);
    }

    // TODO mayukorin [いいね] 良いメソッド！ by jflute (2024/10/08)
    private int calcPagingOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }

    protected abstract String getPagingSQL(int pageSize, int offset);
}