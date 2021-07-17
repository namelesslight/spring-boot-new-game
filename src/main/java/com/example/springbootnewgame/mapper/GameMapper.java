package com.example.springbootnewgame.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootnewgame.pojo.GameUser;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMapper extends BaseMapper<GameUser> {
}
