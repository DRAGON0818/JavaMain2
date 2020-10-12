package Chapter05Database;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        //  java7后，获得行集的标准方式  。   行集的优势在于不用一直占用数据库的连接，但是因为是ResultSet的子集，操作与结果集一致。
        //  在显示的请求后，更新后的行集才会重新连接数据库，并将更新后的数据写入到数据库中
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();

        ResultSet resultSet = null;
        // 通过populate方式，通过结果集 来填充一个行集
        crs.populate(resultSet);

        //数据库元数据，分为DatabaseMetaData  和  ResultSetMetaData两个接口

    }
}
