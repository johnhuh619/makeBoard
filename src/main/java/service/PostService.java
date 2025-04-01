package service;

import model.Post;
import repository.PostRepository;

import java.util.List;

public class PostService {

    private PostRepository rep;

    public PostService(PostRepository rep) {
        this.rep = rep;
    }

    public List<Post> getPosts() {
        return rep.findAll();
    }

    public void createPost(String title, String content, int BoardId, int userId) {
        Post newPost = new Post(0, title, content, null, BoardId, userId);
        rep.save(newPost);
    }

    public void deletePost(int id) {
        rep.delete(id);
    }

    public void updatePost(int id, String title, String content) {
        Post post = rep.findById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            rep.update(post);
        }
    }

    public Post getPost(int id) {
        return rep.findById(id);
    }

    public List<Post> getPostsByBoardId(int boardId) {
        return rep.findByBoardId(boardId);
    }
}
