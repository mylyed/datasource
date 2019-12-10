package io.github.mylyed.ds.controller;

import io.github.mylyed.ds.dao.TestMapper;
import io.github.mylyed.ds.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lilei
 * created at 2019/12/10
 */
@RestController
public class HomeController {
    @Resource
    TestMapper testMapper;

    /**
     * 主库读取
     *
     * @return
     */
    @GetMapping("/t1")
    public String master() {
        return testMapper.masterFindUser();

    }

    /**
     * 从库读取
     *
     * @return
     */
    @GetMapping("/t2")
    public String slave() {
        return testMapper.findUser();
    }

    @Autowired
    TestService testService;

    @GetMapping("/t3")
    public String test() {
        return testService.test();
    }
}

