package com.splitify.mvc.split

import groovy.sql.Sql
import org.springframework.stereotype.Repository

import java.sql.SQLException

@Repository
class SplitRepository {

    private Sql sql

    SplitRepository() {
        getDbConnection()
    }

    public void storeSplitRequestSent(SplitRequest splitRequest) {
        String friendList = listToString(splitRequest.friends)
        getDbConnection()

        String query = """INSERT INTO splitrequest (transaction_id, account_id, friends_list)
                 VALUES ('${splitRequest.transactionId}', '${splitRequest.accountId}', '$friendList');
                """
        sql.execute(query)
    }

//    public SplitRequest retrieveSplitRequestByTransaction(String transactionId) {
//        String query = """SELECT * FROM splitrequest
//                 WHERE transaction_id = '$transactionId';
//                """
//        SplitRequest result = new SplitRequest()
//        sql.firstRow(query) { row ->
//            result.transactionId = row.transaction_id
//            result.accountId = row.account_id
//        }
//
//        return result
//    }

    private getDbConnection() throws URISyntaxException, SQLException {
        if (!sql) {
            String dbUrl = System.getenv("JDBC_DATABASE_URL")
            sql = Sql.newInstance(dbUrl)
        }
    }

    private String listToString(List list) {
        String result = ""
        list.each { it -> result += it + ", "}
        return result
    }

}
