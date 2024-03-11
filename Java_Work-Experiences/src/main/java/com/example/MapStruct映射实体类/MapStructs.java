package com.example.MapStruct映射实体类;

import com.example.vo.ModelView;
import com.example.vo.User;
import com.example.vo.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022/11/30 17:29
 */
// @Mapper
@Mapper(componentModel = "spring")  //这个注解表示把该接口注入到Spring容器中，就不需要下面的实例对象mapStruct mapStr = Mappers.getMapper(MapStructs.class);
public interface MapStructs {
    // 这里是获取mapStruct实例对象
    MapStructs mapStr = Mappers.getMapper(MapStructs.class);

    @Mappings({
            @Mapping(source = "id", target = "idVO", numberFormat = "#.00"),
            @Mapping(source = "name", target = "nameVO"),
            @Mapping(source = "email", target = "emailVO"),
            @Mapping(source = "date", target = "dateVO", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    Users toUsers(User user);

    @Mappings({
            @Mapping(source = "user.id", target = "idVO"),
            @Mapping(source = "user.name", target = "nameVO"),
            @Mapping(source = "user.email", target = "emailVO"),
            @Mapping(source = "modelView.address", target = "addressVO")})
    Users toUsers1(User user, ModelView modelView);

    @Mappings({
            @Mapping(source = "user.id", target = "idVO"),
            @Mapping(source = "user.name", target = "nameVO"),
            @Mapping(source = "user.email", target = "emailVO"),
            @Mapping(source = "user.models.modelView.address", target = "addressVO")})
    Users toUsers2(User user);
}
