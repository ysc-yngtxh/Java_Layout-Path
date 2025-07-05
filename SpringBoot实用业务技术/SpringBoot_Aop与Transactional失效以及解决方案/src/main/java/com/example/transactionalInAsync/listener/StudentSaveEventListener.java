package com.example.transactionalInAsync.listener;

import com.example.transactional.mapper.StudentMapper;
import com.example.transactionalInAsync.event.StudentSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// 事件监听器
@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class StudentSaveEventListener {

    private final StudentMapper studentMapper;

    @Async // 异步执行
    @EventListener
    public void handleStudentSaveEvent(StudentSaveEvent event) {
        studentMapper.insert(event.getStudent());
    }

}
