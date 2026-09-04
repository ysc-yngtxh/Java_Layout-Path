package com.example.vo;

import com.example.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 学生视图对象（VO）
 * 用于前端展示，可以只包含需要的字段，或对字段进行格式化
 *
 * @author 游家纨绔
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7918532148559236543L;

    private Integer id;
    private String name;
    private Integer teacherId;
    private String email;
    private Integer age;
    private Integer departmentId;

    public StudentVO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.teacherId = student.getTeacherId();
        this.email = student.getEmail();
        this.age = student.getAge();
        this.departmentId = student.getDepartmentId();
    }

}
