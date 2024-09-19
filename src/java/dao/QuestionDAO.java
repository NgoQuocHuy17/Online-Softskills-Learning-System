package view;

import model.Question;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Minh
 */

public class QuestionDAO extends DBContext<Question> {
    @Override
    public List<Question> select() {
        String sql = "SELECT [id], [course_id], [content], [media_url], [level], [created_at], [updated_at] FROM [dbo].[questions]";
        List<Question> questions = new Vector<>();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); 
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt(1));
                question.setCourseId(rs.getInt(2));
                question.setContent(rs.getString(3));
                question.setMediaUrl(rs.getString(4));
                question.setLevel(rs.getString(5));
                question.setCreatedAt(rs.getTimestamp(6));
                question.setUpdatedAt(rs.getTimestamp(7));
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, "Error selecting questions", ex);
        }
        return questions;
    }

    @Override
    public Question select(int... id) {
        String sql = "SELECT [id], [course_id], [content], [media_url], [level], [created_at], [updated_at] FROM [dbo].[questions] WHERE id = ?";
        Question question = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    question = new Question();
                    question.setId(rs.getInt(1));
                    question.setCourseId(rs.getInt(2));
                    question.setContent(rs.getString(3));
                    question.setMediaUrl(rs.getString(4));
                    question.setLevel(rs.getString(5));
                    question.setCreatedAt(rs.getTimestamp(6));
                    question.setUpdatedAt(rs.getTimestamp(7));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, "Error selecting question with id " + id, ex);
        }
        return question;
    }

    @Override
    public int insert(Question question) {
        String sql = "INSERT INTO [dbo].[questions] "
                + "([course_id], [content], [media_url], [level], [created_at], [updated_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, question.getCourseId());
            pre.setString(2, question.getContent());
            pre.setString(3, question.getMediaUrl());
            pre.setString(4, question.getLevel());
            pre.setTimestamp(5, new java.sql.Timestamp(question.getCreatedAt().getTime()));
            pre.setTimestamp(6, new java.sql.Timestamp(question.getUpdatedAt().getTime()));
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, "Error inserting question", ex);
        }
        return result;
    }

    @Override
    public int update(Question question) {
        String sql = "UPDATE [dbo].[questions] "
                + "SET [course_id] = ?, [content] = ?, [media_url] = ?, [level] = ?, [created_at] = ?, [updated_at] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, question.getCourseId());
            pre.setString(2, question.getContent());
            pre.setString(3, question.getMediaUrl());
            pre.setString(4, question.getLevel());
            pre.setTimestamp(5, new java.sql.Timestamp(question.getCreatedAt().getTime()));
            pre.setTimestamp(6, new java.sql.Timestamp(question.getUpdatedAt().getTime()));
            pre.setInt(7, question.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, "Error updating question", ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[questions] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, "Error deleting question with id " + id, ex);
        }
        return result;
    }
}
