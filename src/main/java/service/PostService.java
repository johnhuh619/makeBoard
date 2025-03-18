package service;

import model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public void createPost(String title, String content) {
        int id = posts.size() + 1;
        Post newPost = new Post(id, title, content);
        posts.add(newPost);
    }

    public void deletePost(int id) {
        if(id < 1||id > posts.size()) {
            return;
        }
        posts.remove(id-1);

        /// 삭제 후 재정렬
        int newId = 1;
        for(Post post : posts) {
            post.setId(newId++);
        }
    }

    public void updatePost(int id, String title, String content) {
        if(id < 1||id > posts.size()) {
            return;
        }
        Post post = getPost(id);
        post.setTitle(title);
        post.setContent(content);
    }

    public Post getPost(int id) {
        if(id < 1||id > posts.size()) {
            return null;
        }
        return posts.get(id - 1);
    }
}
