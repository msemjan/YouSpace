package sk.youSpace.app.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.youSpace.app.model.RequestQuery;
import sk.youSpace.app.repository.PostMongoRepository;
import sk.youSpace.app.model.Post;

@Controller
public class BlogController {
    @Autowired
    PostMongoRepository postMongoRepository; // = new PostMongoRepository();


    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String home(Model model){
        var posts =  postMongoRepository.findAll();
        if(posts == null) {
            return "error";
        }
        model.addAttribute("blogposts", posts);

        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Post post) {
        post.setAdded();
        postMongoRepository.save(post);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPost(@PathVariable("id") ObjectId id, @ModelAttribute Post post_edited) {
        var post = postMongoRepository.findPostById(id);
        post.setText(post_edited.getText());
        post.setTitle(post_edited.getTitle());

        postMongoRepository.save(post);

        return "redirect:/";
    }

    @RequestMapping(value = "/remove/{id}")
    public String removePost(@PathVariable("id") ObjectId id) {
        try {
            postMongoRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("Something went wrong, while deleting post...");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") ObjectId id, Model model) {
        var post = postMongoRepository.findPostById(id);
        model.addAttribute("post", post);

        return "post";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/searchResults/", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchPost(@ModelAttribute("query") RequestQuery query, Model model) {
        var posts = postMongoRepository.searchPosts(query.getQuery());
        model.addAttribute("blogposts", posts);

        return "search";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(){
        return "about";
    }

}