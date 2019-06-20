package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest {

    @Autowired
    CourseService courseService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findCourseById()
    {
        assertEquals("Mobile Android", courseService.findCourseById(6).getCoursename());
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(6);
        assertEquals(5, courseService.findAll().size());
    }

    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void save()
    {
        String courseName = "My new course";
        Course newCourse = new Course(courseName);

        Course addCourse = courseService.save(newCourse);

        Course foundCourse = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundCourse.getCoursename());
    }
}