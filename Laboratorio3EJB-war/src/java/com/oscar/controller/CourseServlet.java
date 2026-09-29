package com.oscar.controller;

import com.oscar.dao.CourseDaoLocal;
import com.oscar.dao.StudentDaoLocal;
import com.oscar.model.Course;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CourseServlet", urlPatterns = {"/CourseServlet"})
public class CourseServlet extends HttpServlet {

    @EJB
    private CourseDaoLocal courseDao;
    
    @EJB
    private StudentDaoLocal studentDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String courseCodeStr = request.getParameter("courseCode");
        int courseCode = 0;
        if (courseCodeStr != null && !courseCodeStr.equals("")) {
            courseCode = Integer.parseInt(courseCodeStr);
        }

        String courseName = request.getParameter("courseName");
        
        String creditsStr = request.getParameter("credits");
        int credits = 0;
        if (creditsStr != null && !creditsStr.equals("")) {
            credits = Integer.parseInt(creditsStr);
        }

        String semesterStr = request.getParameter("semester");
        int semester = 0;
        if (semesterStr != null && !semesterStr.equals("")) {
            semester = Integer.parseInt(semesterStr);
        }

        String numberOfStudentsStr = request.getParameter("numberOfStudents");
        int numberOfStudents = 0;
        if (numberOfStudentsStr != null && !numberOfStudentsStr.equals("")) {
            numberOfStudents = Integer.parseInt(numberOfStudentsStr);
        }

        Course course = new Course(courseCode, courseName, credits, semester, numberOfStudents);

        if ("Add".equalsIgnoreCase(action)) {
            courseDao.addCourse(course);
        } else if ("Edit".equalsIgnoreCase(action)) {
            courseDao.editCourse(course);
        } else if ("Delete".equalsIgnoreCase(action)) {
            courseDao.deleteCourse(courseCode);
        } else if ("Search".equalsIgnoreCase(action)) {
            course = courseDao.getCourse(courseCode);
        } else if ("Enroll".equalsIgnoreCase(action)) {
            String studentIdStr = request.getParameter("studentIdEnroll");
            if (studentIdStr != null && !studentIdStr.equals("")) {
                int studentId = Integer.parseInt(studentIdStr);
                courseDao.enrollStudent(studentId, courseCode);
            }
        }

        request.setAttribute("course", course);
        request.setAttribute("allCourses", courseDao.getAllCourses());
        request.setAttribute("allStudents", studentDao.getAllStudents());
        request.getRequestDispatcher("courseinfo.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}