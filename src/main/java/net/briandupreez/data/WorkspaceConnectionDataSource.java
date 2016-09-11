package net.briandupreez.data;

import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Oracle Workspace Manager Connection DataSource
 * Executes the GotoWorkspace function when retrieving a data source
 * Created by brian on 2016/09/01.
 */
public class WorkspaceConnectionDataSource implements DataSource {


    private final DataSource dataSource;
    private final WorkspaceHelper workspaceHelper;

    public WorkspaceConnectionDataSource(final DataSource dataSource, final WorkspaceHelper workspaceHelper) {

        this.dataSource = dataSource;
        this.workspaceHelper = workspaceHelper;
    }

    @Override
    public Connection getConnection() throws SQLException {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        executeGoto(connection);
        return connection;
    }

    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        final Connection connection = dataSource.getConnection(username, password);
        executeGoto(connection);
        return connection;
    }

    private void executeGoto(final Connection connection) throws SQLException {
        final CallableStatement callableStatement = connection.prepareCall("CALL dbms_wm.GotoWorkspace('" + workspaceHelper.getWorkspace() + "')");
        callableStatement.execute();
        callableStatement.close();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    @Override
    public void setLoginTimeout(final int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }
}
