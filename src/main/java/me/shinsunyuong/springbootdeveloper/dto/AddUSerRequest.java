package me.shinsunyuong.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUSerRequest {
    private String email;
    private String password;
}
