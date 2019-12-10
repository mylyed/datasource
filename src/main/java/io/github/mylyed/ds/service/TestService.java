package io.github.mylyed.ds.service;

import io.github.mylyed.ds.dao.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lilei
 * created at 2019/12/10
 */
@Service
public class TestService {
    @Resource
    TestMapper testMapper;

    public String test() {
        return testMapper.masterFindUser() + "==" + testMapper.findUser();
    }
}
