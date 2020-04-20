package com.rainier.mapper;

import com.rainier.model.User;
import com.rainier.util.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    void addUser(@Param("user") User user);

    void updateUser(@Param("user") User user);

    void deleteUserRoleByuUserId(@Param("userid")Integer userid);

    void addUserRole(@Param("userid")Integer userid, @Param("roleids")List roleids);

    void deleteUsers(@Param("userids") List userids);

    Integer queryUserCount(@Param("name") String name);

    List queryUserList(@Param("name") String name, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    User getUserbyName(@Param("user") User user);

    List queryUserByid(@Param("userid") Integer userid);

    void updatePassword(@Param("userid") Integer userid,@Param("password") String password);

    void deleteUserRoleByuUserIds(@Param("userids") List userids);

    void updateState(@Param("state") Integer state,@Param("userid") String userid);

    List getRole();

    List getRoleByUserid(@Param("userid") Integer userid);
}
