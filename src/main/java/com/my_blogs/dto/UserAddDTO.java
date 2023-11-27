package com.my_blogs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增用户传递的数据模型")
public class UserAddDTO {
    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("头像")
    private String email;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;


}
