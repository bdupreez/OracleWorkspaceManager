package net.briandupreez.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by brian on 2016/09/01.
 */
public class WorkspaceHelper {
    private final DataSource dataSource;
    //Default
    private String workspace = "LIVE";

    @Autowired
    public WorkspaceHelper(final DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public String getWorkspace() {
        return workspace;
    }

    public boolean setCurrentWorkspace(final String workspace) throws SQLException {
        //check if it exists
        final Connection connection = DataSourceUtils.getConnection(this.dataSource);
        final PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT WORKSPACE FROM ALL_WORKSPACES where WORKSPACE = '%s'", workspace));
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            this.workspace = workspace;
            return true;
        } else {
            return false;
        }

    }

    public void createWorkspace(final String workspaceName) throws SQLException {
        final Connection connection = DataSourceUtils.getConnection(this.dataSource);
        CallableStatement callableStatement = connection.prepareCall(String.format("CALL dbms_wm.CreateWorkspace('%s')", workspaceName));
        callableStatement.execute();
        callableStatement.close();
        /*
            If you are using different users and not system (who has the rights)... need to grant privileges

            callableStatement = connection.prepareCall(String.format("CALL dbms_wm.grantworkspacepriv('ACCESS_WORKSPACE', '%s', 'system')", workspaceName));
            callableStatement.execute();
            callableStatement.close();

        */
    }

    public void removeWorkspace(final String workspaceName) throws SQLException {
        final Connection connection = DataSourceUtils.getConnection(this.dataSource);
        CallableStatement callableStatement = connection.prepareCall(String.format("CALL dbms_wm.RemoveWorkspace('%s')", workspaceName));
        callableStatement.execute();
        callableStatement.close();
    }

    /**
     * DBMS_WM.MergeWorkspace(
     * workspace         IN VARCHAR2,
     * create_savepoint  IN BOOLEAN DEFAULT FALSE,
     * remove_workspace  IN BOOLEAN DEFAULT FALSE,
     * auto_commit       IN BOOLEAN DEFAULT TRUE);
     *
     * @param workspaceName
     * @throws SQLException
     */
    public void mergeWorkspace(final String workspaceName) throws SQLException {
        final Connection connection = DataSourceUtils.getConnection(this.dataSource);
        CallableStatement callableStatement = connection.prepareCall(String.format("CALL dbms_wm.MergeWorkspace('%s')", workspaceName));
        callableStatement.execute();
        callableStatement.close();
    }

    public String checkCurrentWorkspace() throws SQLException {
        final Connection connection = DataSourceUtils.getConnection(this.dataSource);
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT dbms_wm.getworkspace FROM dual");
        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("GETWORKSPACE");
    }


}
