package com.example.springbootnewgame.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootnewgame.mapper.GameMapper;
import com.example.springbootnewgame.pojo.GameUser;
import com.example.springbootnewgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameMapper gameMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Integer register(String userName, String userPassword) {
        if (gameMapper.selectById(userName) == null) {
            GameUser gameUser = new GameUser();
            gameUser.setUserName(userName);
            gameUser.setUserPassword(userPassword);
            return gameMapper.insert(gameUser);
        } else {
            return 0;
        }
    }

    @Override
    public Integer login(String userName, String userPassword) {
        QueryWrapper<GameUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("user_password", userPassword);
        return gameMapper.selectCount(queryWrapper);
    }

    @Override
    public IPage<GameUser> getUserByLevel(Integer currentPage, Integer pageSize) {
        IPage<GameUser> iPage = new Page<>();
        iPage.setCurrent(currentPage);
        iPage.setSize(pageSize);
        QueryWrapper<GameUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("user_best_level");
        return gameMapper.selectPage(iPage, queryWrapper);
    }

    @Override
    public IPage<GameUser> getUserByAverage(Integer currentPage, Integer pageSize) {
        IPage<GameUser> iPage = new Page<>();
        iPage.setCurrent(currentPage);
        iPage.setSize(pageSize);
        QueryWrapper<GameUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("user_best_average");
        return gameMapper.selectPage(iPage, queryWrapper);
    }

    @Override
    public GameUser getOneUser(String userName) {
        return gameMapper.selectById(userName);
    }

    @Override
    public Integer updatePassword(String userName, String userPassword) {
        GameUser gameUser = new GameUser();
        gameUser.setUserName(userName);
        gameUser.setUserPassword(userPassword);
        UpdateWrapper<GameUser> updateWrapper = new UpdateWrapper<>();
        return gameMapper.update(gameUser, updateWrapper);
    }

    @Override
    public Integer setBestAverage(String userName, Double bestAverage) {
        if (gameMapper.selectById(userName).getUserBestAverage() > bestAverage) {
            UpdateWrapper<GameUser> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("user_name", userName);
            GameUser gameUser = new GameUser();
            gameUser.setUserBestAverage(bestAverage);
            return gameMapper.update(gameUser, userUpdateWrapper);
        }
        return null;
    }

    @Override
    public Double getBestAverage(String userName) {
        return gameMapper.selectById(userName).getUserBestAverage();
    }

    @Override
    public Integer setBestLevel(String userName, Integer bestLevel) {
        if (gameMapper.selectById(userName).getUserBestLevel() < bestLevel) {
            UpdateWrapper<GameUser> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("user_name", userName);
            GameUser gameUser = new GameUser();
            gameUser.setUserBestLevel(bestLevel);
            return gameMapper.update(gameUser, userUpdateWrapper);
        }
        return null;
    }

    @Override
    public Integer getBestLevel(String userName) {
        return gameMapper.selectById(userName).getUserBestLevel();
    }
}
