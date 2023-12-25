package git_repo;

import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GitLogController {

    @GetMapping
    public String displayHomePage(){
        return "index";
    }

    @PostMapping("/git_log")
    public String processGitLog(@RequestParam String url, Model model) {
        List<RevCommit> commitList = GitLog.getLog(url);
        if (commitList != null) {
            model.addAttribute("commits", commitList);
        } else {
            model.addAttribute("dataDetails", "No data found");
        }
        return "git_log";
    }
}