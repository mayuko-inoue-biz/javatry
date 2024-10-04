package org.docksidestage.javatry.basic.st6.dbms;

/**
 * The object for sql.
 * @author mayukorin
 */
public abstract class St6Sql {

    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = calcPagingOffset(pageSize, pageNumber);
        return getPagingSQL(pageSize, offset);
    }

    private int calcPagingOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }

    protected abstract String getPagingSQL(int pageSize, int offset);
}
