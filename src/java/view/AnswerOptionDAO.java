package view;

import model.AnswerOption;
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
public class AnswerOptionDAO extends DBContext<AnswerOption> {

    @Override
    public List<AnswerOption> select() {
        String sql = "SELECT [id], [question_id], [option_text], [is_correct] FROM [dbo].[answer_options]";
        List<AnswerOption> answerOptions = new Vector();
        try (PreparedStatement pre = super.getConn().prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                AnswerOption answerOption = new AnswerOption();
                answerOption.setId(rs.getInt(1));
                answerOption.setQuestionId(rs.getInt(2));
                answerOption.setOptionText(rs.getString(3));
                answerOption.setIsCorrect(rs.getBoolean(4));
                answerOptions.add(answerOption);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerOptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerOptions;
    }

    @Override
    public AnswerOption select(int... id) {
        String sql = "SELECT [id], [question_id], [option_text], [is_correct] FROM [dbo].[answer_options] WHERE id = ?";
        AnswerOption answerOption = null;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    answerOption = new AnswerOption();
                    answerOption.setId(rs.getInt(1));
                    answerOption.setQuestionId(rs.getInt(2));
                    answerOption.setOptionText(rs.getString(3));
                    answerOption.setIsCorrect(rs.getBoolean(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerOptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerOption;
    }

    @Override
    public int insert(AnswerOption answerOption) {
        String sql = "INSERT INTO [dbo].[answer_options] "
                + "([question_id], [option_text], [is_correct]) "
                + "VALUES (?, ?, ?)";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, answerOption.getQuestionId());
            pre.setString(2, answerOption.getOptionText());
            pre.setBoolean(3, answerOption.isCorrect());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerOptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(AnswerOption answerOption) {
        String sql = "UPDATE [dbo].[answer_options] "
                + "SET [question_id] = ?, [option_text] = ?, [is_correct] = ? "
                + "WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, answerOption.getQuestionId());
            pre.setString(2, answerOption.getOptionText());
            pre.setBoolean(3, answerOption.isCorrect());
            pre.setInt(4, answerOption.getId());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerOptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int... id) {
        String sql = "DELETE FROM [dbo].[answer_options] WHERE id = ?";
        int result = 0;
        try (PreparedStatement pre = super.getConn().prepareStatement(sql)) {
            pre.setInt(1, id[0]);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerOptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
