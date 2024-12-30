package egovframework.example.post.mapper;

import java.util.List;

import egovframework.example.post.dto.PostVO;
import egovframework.example.post.dto.SearchVO;
import egovframework.example.user.dto.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("PostMapper")
public interface PostMapper {
	/**
	 * 게시글 생성한다.
	 * @param post
	 * @param user
	 * @return
	 * @throws Exception
	 */
    int createPost(PostVO post, UserVO user) throws Exception;
    
    /**
     * 하나의 게시글 상세정보를 가져온다.
     * @param postId
     * @return
     * @throws Exception
     */
    PostVO getPost(String postId) throws Exception;
    
    /**
     * 여러개의 게시글 리스트를 가져온다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<PostVO> getAllPosts(SearchVO searchVO) throws Exception;
    
    /**
     * 하나의 게시글을 수정한다.
     * @param post
     * @param user
     * @return
     * @throws Exception
     */
    boolean updatePost(PostVO post, UserVO user) throws Exception;
    
    /**
     * 하나의 게시글을 삭제한다.
     * @param postId
     * @param user
     * @return
     * @throws Exception
     */
    boolean deletePost(String postId, UserVO user) throws Exception;
    
    /**
     * 게시글 총 개수를 조회하는 메서드
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectPostListTotCnt(SearchVO searchVO) throws Exception; // 추가된 메서드
    


	

}
