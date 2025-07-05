package com.example.transactionalInAsync.event;

import com.example.transactional.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 定义事件
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSaveEvent {

    private Student student;

}
