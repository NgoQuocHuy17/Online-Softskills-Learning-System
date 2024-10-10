package Test;

import java.util.List;
import model.UserVideo;
import view.UserVideoDAO;

public class Test {
    public static void main(String[] args) {
        UserVideoDAO userVideosDAO = new UserVideoDAO();
        
        // Giả sử bạn muốn kiểm tra userId = 1
        int testUserId = 1;
        
        // Lấy danh sách video của người dùng
        List<UserVideo> videos = userVideosDAO.getUserVideo(testUserId);
        
        // Kiểm tra và in ra kết quả
        if (videos.isEmpty()) {
            System.out.println("No videos found for user ID: " + testUserId);
        } else {
            System.out.println("Videos for user ID: " + testUserId);
            for (UserVideo video : videos) {
                System.out.println("Video ID: " + video.getId() + ", Video URL: " + video.getVideoUrl());
            }
        }
    }
}
