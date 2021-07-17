package com.example.springbootnewgame.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springbootnewgame.pojo.GameUser;

public interface GameService {
    public Integer register(String userName, String userPassword);

    public Integer login(String userName, String userPassword);

    public IPage<GameUser> getUserByLevel(Integer currentPage, Integer pageSize);

    public IPage<GameUser> getUserByAverage(Integer currentPage, Integer pageSize);

    public GameUser getOneUser(String userName);

    public Integer updatePassword(String userName, String userPassword);

    public Integer setBestAverage(String userName, Double bestAverage);

    public Double getBestAverage(String userName);

    public Integer setBestLevel(String userName, Integer bestLevel);

    public Integer getBestLevel(String userName);
}
