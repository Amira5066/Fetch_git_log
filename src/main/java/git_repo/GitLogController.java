package git_repo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GitLogController {

    @GetMapping
    public String displayHomePage(){
        return "index";
    }

    @PostMapping("/git_log")
    public String processGitLog(@RequestParam String url, Model model) {
        model.addAttribute("commits", GitLog.getLog(url));
        return "git_log";
    }
}