

import java.util.Stack;
import java.util.regex.Pattern;

/**
 *
 * Description Please implement WorkSpace.changeDirectory().
 *<p>
 * Hint
 * <li>Root directory is ‘/’</li>
 * <li>Current directory is ‘.’ </li>
 * <li>Parent directory is ‘..’ </li>
 * <li>The separator of path is ‘/’ </li>
 * <li>A valid path’s characters include: ‘A’-’Z’, ‘a’-’z’, ‘0’-’9’</li>
 * <li>Please don’t use utility methods from any toolkits</li>
 * <p>
 * Sample WorkSpace<br>
 * <pre>
 * workspace = new WorkSpace("/a/b/c");
 * workspace.changeDirectory('..');
 * System.out.println(workspace.getPath());
 * workspace.changeDirectory('../../d');
 * System.out.println(workspace.getPath());
 * workspace.changeDirectory('./e');
 * System.out.println(workspace.getPath());
 * workspace.changeDirectory('/s@^');
 * System.out.println(workspace.getPath());
 * </pre>
 *
 * <p>
 * Sample Output<br>
 * /a/b<br>
 * /d<br>
 * /d/e<br>
 * /d/e<br>
 *
 */
public class WorkSpace {

    public static final String ROOT = "/";
    public static final String CURRENT = ".";
    public static final String PARENT = "..";
    public static final String SEPARATOR = "/";

    public static Pattern sPathPattern = Pattern.compile("^[A-Za-z0-9]+$");

    private Stack<String> workspaceStack = new Stack<>();

    public WorkSpace(String path) throws Exception {
        String[] paths = path.split(SEPARATOR);
        if(paths == null | paths.length == 0) {
            throw new Exception("Error Path !");
        }

        for(String dir : paths) {
            if(!sPathPattern.matcher(dir).find()) {
                continue;
            } else {
                workspaceStack.push(dir);
            }
        }
        System.out.println("workspaceStack = "+workspaceStack);
    }

    public void changeDirectory(String command) throws Exception {
        String[] dir = command.split(SEPARATOR);
        if(dir == null | dir.length == 0) {
            throw new Exception("Error Command !");
        }
        for(String d : dir) {
            if(PARENT.equals(d)) {
                workspaceStack.pop();
            } else if (CURRENT.equals(d)) {
                continue;
            } else if (sPathPattern.matcher(d).find()) {
                workspaceStack.push(d);
            }
        }
    }

    public String getPath() {
        if(workspaceStack.size() <= 0) {
            return ROOT;
        }
        StringBuilder path = new StringBuilder();
        for(String dir : workspaceStack) {
            path.append(SEPARATOR);
            path.append(dir);
        }
        return path.toString();
    }

    public static void main(String[] args) {
        try {
            WorkSpace workspace = new WorkSpace("/03kd/a/b/c");
            workspace.changeDirectory("..");
            System.out.println(workspace.getPath());
            workspace.changeDirectory("../../d");
            System.out.println(workspace.getPath());
            workspace.changeDirectory("./e");
            System.out.println(workspace.getPath());
            workspace.changeDirectory("/s@^");
            System.out.println(workspace.getPath());
            workspace.changeDirectory("/s/a");
            System.out.println(workspace.getPath());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
