package net.briandupreez.data;

import net.briandupreez.data.entities.Code;
import net.briandupreez.data.entities.CodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.net.UnknownHostException;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "/poc")
public class DataController {

    private final DataSource dataSource;
    @Autowired
    private CodeRepo codeRepo;
    @Autowired
    private WorkspaceHelper workspaceHelper;


    @Autowired
    public DataController(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping(value = "/workspace/data/code", method = RequestMethod.GET)
    public Iterable<Code> getFromWorkspace() throws UnknownHostException {
        return codeRepo.findAll();
    }
    @RequestMapping(value = "/workspace/data/code", method = RequestMethod.POST)
    public Code createInWorkspace(@RequestBody final CodeRequest codeRequest) throws UnknownHostException {
        return codeRepo.save(new Code(codeRequest.getId(), codeRequest.getDescr(), codeRequest.getType()));
    }

    @RequestMapping(value = "/workspace", method = RequestMethod.GET)
    public String getWorkspace() throws UnknownHostException, SQLException {
        return workspaceHelper.checkCurrentWorkspace();
    }

    @RequestMapping(value = "/workspace/{workspace}", method = RequestMethod.PUT)
    public String updateWorkspace(@PathVariable String workspace) throws UnknownHostException, SQLException {
        if (workspaceHelper.setCurrentWorkspace(workspace)) {
            return "Workspace set to " + workspace;
        }
        return "Workspace not set";
    }

    @RequestMapping(value = "/workspace/{workspace}", method = RequestMethod.POST)
    public String createWorkspace(@PathVariable String workspace) throws UnknownHostException, SQLException {
        workspaceHelper.createWorkspace(workspace);
        return "Workspace created set to " + workspace;
    }

    @RequestMapping(value = "/workspace/{workspace}", method = RequestMethod.DELETE)
    public String removeWorkspace(@PathVariable String workspace) throws UnknownHostException, SQLException {
        workspaceHelper.removeWorkspace(workspace);
        return "Workspace removed";
    }
    @RequestMapping(value = "/workspace/merge/{workspace}", method = RequestMethod.PUT)
    public String mergeWorkspace(@PathVariable String workspace) throws UnknownHostException, SQLException {
        workspaceHelper.mergeWorkspace(workspace);
        return "Workspace merged";
    }
}
