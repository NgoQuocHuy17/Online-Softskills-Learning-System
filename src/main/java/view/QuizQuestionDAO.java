package view;

import model.QuizQuestion;
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

public class QuizQuestionDAO extends DBContext<QuizQuestion> {

    @Override
    public List<QuizQuestion> select() {
        String sql = "SELECT [quiz_id], [question_id] FROM [dbo].[quiz_questions]";
        List<QuizQuestion> quizQuestions = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql); 
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuizId(rs.getInt(1));
                quizQuestion.setQuestionId(rs.getInt(2));
                quizQuestions.add(quizQuestion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizQuestionDAO.class.getName()).log(Level.SEVERE, "Error selecting quiz questions", ex);
        }
        return quizQuestions;
    }

    @Override
    public QuizQuestion select(int... id) {
        String sql = "SELECT [quiz_id], [question_id] FROM [dbo].[quiz_questions] WHERE quiz_id = ? AND question_id = ?";
        QuizQuestion quizQuestion = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]); // quiz_id
            pre.setInt(2, id[1]); // question_id
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    quizQuestion = new QuizQuestion();
                    quizQuestion.setQuizId(rs.getInt(1));
                    quizQuestion.setQuestionId(rs.getInt(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizQuestionDAO.class.getName()).log(Level.SEVERE, "Error selecting quiz question with quiz_id " + id[0] + " and question_id " + id[1], ex);
        }
        return quizQuestion;
    }

    @Override
    public int insert(QuizQuestion quizQuestion) {
        String sql = "INSERT INTO [dbo].[quiz_questions] ([quiz_id], [question_id]) VALUES (?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, quizQuestion.getQuizId());
            pre.setInt(2, quizQuestion.getQuestionId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizQuestionDAO.class.getName()).log(Level.SEVERE, "Error inserting quiz question", ex);
        }
        return result;
    }

    @Override
    public int update(QuizQuestion quizQuestion) {
        // Typically quiz_questions won't have a traditional update case (usually handled with delete/insert)
        return 0;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[quiz_questions] WHERE quiz_id = ? AND question_id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]); // quiz_id
            pre.setInt(2, id[1]); // question_id
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizQuestionDAO.class.getName()).log(Level.SEVERE, "Error deleting quiz question with quiz_id " + id[0] + " and question_id " + id[1], ex);
        }
        return result;
    }
}
