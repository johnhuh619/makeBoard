package repository;

import model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private final List<Post> posts = new ArrayList<>();

    public int save(Post post) {
        int id = posts.size() + 1;
        LocalDateTime now = LocalDateTime.now();
        post.setId(id);
        post.setCreatedDate(now);
        post.setUpdatedDate(now);
        posts.add(post);
        return id;
    }

    public Post update(Post post) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == post.getId()) {
                posts.set(i, post);
                return post;
            }
        }
        return null;
    }


    public Post findById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public List<Post> findByBoardId(int boardId) {
        List<Post> res = new ArrayList<>();
        for (Post post : posts) {
            if (post.getBoardId() == boardId) {
                res.add(post);
            }
        }
        return res;
    }
    public boolean delete(int id) {
        return posts.removeIf((post -> post.getId() == id));
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }
}
