package pl.mizuirokoala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mizuirokoala.entity.Comment;
import pl.mizuirokoala.entity.Tweet;
import pl.mizuirokoala.entity.User;
import pl.mizuirokoala.repository.CommentRepository;
import pl.mizuirokoala.repository.TweetRepository;
import pl.mizuirokoala.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CommentRepository commentRepo;



    // add
    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("tweet", new Tweet());
        return "tweet/addTweetForm";
    }

    @PostMapping("/add")
    public String formPost(@Valid Tweet tweet, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "tweet/addTweetForm";
        }
        /*
         * get user and add to author of tweet
         */
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("user");
        tweet.setUser(user);

        // if updated
        tweet.setId(tweet.getId());

        tweetRepo.save(tweet);
        return "redirect:/tweet/all/" + user.getId();
    }

    // update
    @GetMapping("/update/{id}")
    public String updateJsp(@PathVariable long id, Model model, HttpServletRequest request) {
        Tweet tweet = tweetRepo.findOne(id);
        model.addAttribute(tweet);
        request.setAttribute("message", "Edit tweet:");
        return "tweet/addTweetForm";
    }

    // delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model model, HttpServletRequest request) {
        List<Comment> list = commentRepo.findAllByTweetId(id);
        if (list != null) {
            commentRepo.delete(list);
        }
        tweetRepo.delete(id);

        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("user");
        model.addAttribute("tweets", tweetRepo.findAllByUserIdOrderByCreatedDesc(user.getId()));
        return "tweet/listTweet";
    }
}