package com.example.dao;

import com.example.entity.Question;
import com.example.util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class QuestionDao {

    DBUtil util = new DBUtil();

    // 注册试题
    public int add(Question question) {
        String sql = "insert into question(title, optionA, optionB, optionC, optionD, answer) " +
                "values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try{
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    // 查询试题
    public List findAll(){
        List questionlist = new ArrayList();
        String sql = "select * from question";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        try{
            rs = ps.executeQuery();
            while(rs.next()) {
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question que = new Question(questionId, title, optionA, optionB, optionC, optionD, answer);
                questionlist.add(que);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return questionlist;
    }

    // 删除试题
    public int delete(String questionId){
        String sql = "delete from question where questionId = ?";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try{
            ps.setInt(1,Integer.valueOf(questionId));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    // 根据试题编号查询试题信息
    public Question findById(String questionId) {
        String sql = "select * from question where questionId = ?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        Question question = null;
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            rs = ps.executeQuery();
            while(rs.next()) {
                Integer quesId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                question = new Question(quesId, title, optionA, optionB, optionC, optionD, answer);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            util.close();
        }
        return question;
    }

    // 更新试题
    public int update(Question question) {
        String sql = "update question set title = ?, optionA = ?, optionB = ?, optionC = ?, " +
                "optionD = ?, answer = ? where questionId = ?";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try{
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setInt(7,question.getQuestionId());
            result = ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    // 随机出题
    public List rand(){
        List questionlist = new ArrayList();
        String sql = "select * from question order by rand() limit 0,4";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        Question ques = null;
        try {
            rs = ps.executeQuery();
            while(rs.next()) {
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                ques = new Question(questionId, title, optionA, optionB, optionC, optionD, answer);
                questionlist.add(ques);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            util.close();
        }
        return questionlist;
    }
}
