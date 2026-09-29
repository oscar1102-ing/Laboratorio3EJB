<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Information</title>
    </head>
    <body>
        <h1>Course Information</h1>
        <p><a href="studentinfo.jsp">Ir a Gestión de Estudiantes</a></p>

        <form action="CourseServlet" method="POST">
            <table>
                <tr>
                    <td>Course Code:</td>
                    <td><input type="text" name="courseCode" value="${course.courseCode}" /></td>
                </tr>
                <tr>
                    <td>Course Name:</td>
                    <td><input type="text" name="courseName" value="${course.courseName}" /></td>
                </tr>
                <tr>
                    <td>Credits:</td>
                    <td><input type="text" name="credits" value="${course.credits}" /></td>
                </tr>
                <tr>
                    <td>Semester:</td>
                    <td><input type="text" name="semester" value="${course.semester}" /></td>
                </tr>
                <tr>
                    <td>Max Students:</td>
                    <td><input type="text" name="numberOfStudents" value="${course.numberOfStudents}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                    </td>
                </tr>
            </table>

            <h3>Inscribir Estudiante en este Curso</h3>
            <p>Seleccione un estudiante e ingrese el código del curso arriba, luego presione Enroll:</p>
            <select name="studentIdEnroll">
                <c:forEach items="${allStudents}" var="st">
                    <option value="${st.studentId}">${st.studentId} - ${st.firstname} ${st.lastname}</option>
                </c:forEach>
            </select>
            <input type="submit" name="action" value="Enroll" />
        </form>

        <br><hr><br>

        <h2>Lista de Cursos</h2>
        <table border="1">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Credits</th>
                <th>Semester</th>
                <th>Max Students</th>
                <th>Estudiantes Inscritos</th>
            </tr>
            <c:forEach items="${allCourses}" var="c">
                <tr>
                    <td>${c.courseCode}</td>
                    <td>${c.courseName}</td>
                    <td>${c.credits}</td>
                    <td>${c.semester}</td>
                    <td>${c.numberOfStudents}</td>
                    <td>
                        <c:forEach items="${c.students}" var="s">
                            [${s.studentId}] ${s.firstname} ${s.lastname}<br>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>