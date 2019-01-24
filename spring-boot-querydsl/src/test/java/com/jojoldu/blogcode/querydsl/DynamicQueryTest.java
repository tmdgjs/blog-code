package com.jojoldu.blogcode.querydsl;

import com.jojoldu.blogcode.querydsl.domain.academy.Academy;
import com.jojoldu.blogcode.querydsl.domain.academy.AcademyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2019-01-23
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicQueryTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Test
    public void 동적쿼리_name() {
        //given
        String targetName = "name";
        academyRepository.saveAll(Arrays.asList(
                new Academy(targetName, targetName, ""),
                new Academy("not target", "", "")
        ));

        //when
        List<Academy> academies = academyRepository.findDynamicQuery(targetName, "", "");

        //then
        assertThat(academies.size(), is(1));
        assertThat(academies.get(0).getAddress(), is(targetName));
    }
}
