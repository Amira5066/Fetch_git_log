package git_repo;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GitLog {

    public static Iterable<RevCommit> getLog(String remoteRepoUrl) {
        String localRepoPath = "E:\\internship tests\\repo_test";

        File file = new File(localRepoPath);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            cloneGitRepository(remoteRepoUrl, localRepoPath);
            return displayGitLog(localRepoPath);
        } catch (IOException | GitAPIException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void cloneGitRepository(String remoteRepoUrl, String localRepoPath) throws GitAPIException, URISyntaxException {
        CloneCommand cloneCommand = Git.cloneRepository()
                .setURI(remoteRepoUrl)
                .setDirectory(new java.io.File(localRepoPath));

        cloneCommand.call();
    }

    private static Iterable<RevCommit> displayGitLog(String localRepoPath) throws IOException, GitAPIException {
        try (Repository repository = new RepositoryBuilder().setGitDir(new java.io.File(localRepoPath + "/.git")).build();
             Git git = new Git(repository)) {

            LogCommand logCommand = git.log();
            Iterable<RevCommit> commits = logCommand.call();

            return commits;
        }
    }
}
