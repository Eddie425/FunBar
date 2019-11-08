package tw.FunBar.service;

import java.util.List;

import tw.FunBar.model.LikePost;
import tw.FunBar.model.Post;

public interface DiscussService {

	List<Post> getAllPostDetail();
	
	void createPost(Post post);
	
	Post findByIdPost(int postId);
	
	void updatePostContent(Post post);
	
	void deletePostContent(Integer postId);
	
	
	long getLikeByPostId(Integer postId);

	void addLike(LikePost like);
	
	void unLike(LikePost like);
	
}
