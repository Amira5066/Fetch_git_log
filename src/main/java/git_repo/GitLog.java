package git_repo;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitLog {
    public static String tempRepoPath = "E:\\internship tests\\repo_test";
    private static File tempRepoDir = new File(tempRepoPath);

    public static List<RevCommit> getLog(String remoteRepoUrl) {
        try {
            FileUtils.deleteDirectory(tempRepoDir);
            Git tempRepo = cloneGitRepository(remoteRepoUrl);
            if (tempRepo != null) {
                return getCommitDetails(tempRepo);
            }
        } catch (IOException e) {
            System.err.println("Could not clone temporary repo.");
            e.printStackTrace();
        }
        return null;
    }

    private static Git cloneGitRepository(String remoteRepoUrl) {
        try {
            Git git = Git.cloneRepository()
                    .setURI(remoteRepoUrl)
                    .setDirectory(tempRepoDir)
                    .call();
            return git;
        } catch (GitAPIException e) {
            System.err.println("Could not clone repository.");
            e.printStackTrace();
        }
        return null;
    }

    private static List<RevCommit> getCommitDetails(Git tempRepo) {
        try {
            LogCommand logCommand = tempRepo.log();
            Iterable<RevCommit> commits = logCommand.call();
            List<RevCommit> commitList = new ArrayList<>();
            for (RevCommit commit : commits) {
                commitList.add(commit);
            }
            return commitList;
        } catch (GitAPIException e) {
            System.err.println("Could not retrieve details of 'git log' command.");
            e.printStackTrace();
        } finally {
            tempRepo.close();
        }
        return null;
    }
}
