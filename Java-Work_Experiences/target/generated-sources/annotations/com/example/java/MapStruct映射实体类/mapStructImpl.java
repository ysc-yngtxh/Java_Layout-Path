package com.example.java.MapStruct映射实体类;

import com.example.java.vo.ModelView;
import com.example.java.vo.Models;
import com.example.java.vo.User;
import com.example.java.vo.Users;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-17T15:20:00+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class mapStructImpl implements mapStruct {

    @Override
    public Users toUsers(User user) {
        if ( user == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        if ( user.getId() != null ) {
            users.idVO( new DecimalFormat( "#.00" ).format( user.getId() ) );
        }
        users.nameVO( user.getName() );
        users.emailVO( user.getEmail() );
        if ( user.getDate() != null ) {
            users.dateVO( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( user.getDate() ) );
        }

        return users.build();
    }

    @Override
    public Users toUsers1(User user, ModelView modelView) {
        if ( user == null && modelView == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        if ( user != null ) {
            if ( user.getId() != null ) {
                users.idVO( String.valueOf( user.getId() ) );
            }
            users.nameVO( user.getName() );
            users.emailVO( user.getEmail() );
        }
        if ( modelView != null ) {
            users.addressVO( modelView.getAddress() );
        }

        return users.build();
    }

    @Override
    public Users toUsers2(User user) {
        if ( user == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        if ( user.getId() != null ) {
            users.idVO( String.valueOf( user.getId() ) );
        }
        users.nameVO( user.getName() );
        users.emailVO( user.getEmail() );
        users.addressVO( userModelsModelViewAddress( user ) );

        return users.build();
    }

    private String userModelsModelViewAddress(User user) {
        if ( user == null ) {
            return null;
        }
        Models models = user.getModels();
        if ( models == null ) {
            return null;
        }
        ModelView modelView = models.getModelView();
        if ( modelView == null ) {
            return null;
        }
        String address = modelView.getAddress();
        if ( address == null ) {
            return null;
        }
        return address;
    }
}
