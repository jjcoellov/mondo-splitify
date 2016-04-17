package com.splitify.mvc.split

import org.springframework.stereotype.Repository

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

@Repository
class SplitRepository {

    private Connection connection

    public void storeSplitRequestSent(SplitRequest splitRequest) {
        String friendList = listToString(splitRequest.friends)
        String query = """INSERT INTO splitrequest (transaction_id, account_id, friends_list)
                 VALUES ('${splitRequest.transactionId}', '${splitRequest.accountId}', '$friendList');
                """
        getDbConnection()
        connection.createStatement().execute(query)
    }

    public SplitRequest retrieveSplitRequestByTransaction(String transactionId) {
        String query = """SELECT * FROM splitrequest
                 WHERE transaction_id = '$transactionId';
                """
        getDbConnection()
        ResultSet result = connection.createStatement().executeQuery(query)
        result.next()
        return new SplitRequest(transactionId: result.getString("transacion_id"), accountId: result.getString("account_id"))
    }

    private getDbConnection() throws URISyntaxException, SQLException {
        if (!connection) {
            String dbUrl = System.getenv("JDBC_DATABASE_URL")
            connection = DriverManager.getConnection(dbUrl)
        }
    }

    private String listToString(List list) {
        String result = ""
        list.each { it -> result += it + ", "}
        return result
    }

}
