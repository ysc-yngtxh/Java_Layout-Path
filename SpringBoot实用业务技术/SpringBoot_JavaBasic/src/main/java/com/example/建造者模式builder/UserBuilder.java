package com.example.建造者模式builder;

import com.example.vo.User;

public class UserBuilder {

    private UserBuilder() {}

    public static IdStep builder(){
        return new UserSteps();
    }

    interface IdStep{
        NameStep withId(Long Id);
    }

    interface NameStep{
        EmailStep withName(String name);
    }

    interface EmailStep{
        AgeStep withEmail(String email);
    }

    interface AgeStep{
        BuildStep withAge(int age);
    }

    interface BuildStep{
        User build();
    }

    public static class UserSteps implements IdStep,NameStep,EmailStep,AgeStep,BuildStep{
       private User user;

        public UserSteps() {
            this.user = new User();
        }

        @Override
        public NameStep withId(Long Id) {
            this.user.setId(Id);
            return this;
        }

        @Override
        public EmailStep withName(String name) {
            this.user.setName(name);
            return this;
        }

        @Override
        public AgeStep withEmail(String email) {
            this.user.setEmail(email);
            return this;
        }

        @Override
        public BuildStep withAge(int age) {
            this.user.setAge(age);
            return this; // 返回当前类实例
        }

        @Override
        public User build() {
            return this.user; //返回当前类实例的user对象
        }
    }
}