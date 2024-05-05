package com.example.handler;

import com.example.domain.Student;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;  
  
public class CsvResultHandler implements ResultHandler<Student> {
    private BufferedWriter writer;  
  
    public CsvResultHandler(String fileName) {  
        try {  
            writer = new BufferedWriter(new FileWriter(fileName));  
            // 写入CSV文件头  
            writer.write("ID, Name, Age, Email\n");
        } catch (IOException e) {  
            throw new RuntimeException("Could not create file writer", e);  
        }  
    }  
  
    @Override  
    public void handleResult(ResultContext<? extends Student> resultContext) {
        Student student = resultContext.getResultObject();
        try {  
            // 将用户数据写入CSV文件  
            writer.write(String.format("%d,%s,%d,%s\n", student.getId(), student.getName(), student.getAge(), student.getEmail()));
        } catch (IOException e) {  
            throw new RuntimeException("Could not write to file", e);  
        }  
    }  
  
    public void close() {  
        try {  
            writer.close();  
        } catch (IOException e) {  
            throw new RuntimeException("Could not close file writer", e);  
        }  
    }  
}