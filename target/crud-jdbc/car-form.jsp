<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Car" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand"> Car Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Cars</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${car != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${car == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${car != null}">
                                Edit Car
                            </c:if>
                            <c:if test="${car == null}">
                                Add New Car
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${car != null}">
                        <input type="hidden" name="id" value="<c:out value='${car.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Car Make</label> <input type="text"
                                                        value="<c:out value='${car.make}' />" class="form-control"
                                                        name="make" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Car Model</label> <input type="text"
                                                         value="<c:out value='${car.model}' />" class="form-control"
                                                         name="model">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Car Year</label> <input type="text"
                                                           value="<c:out value='${car.year}' />" class="form-control"
                                                           name="year">
                    </fieldset>

                        <fieldset class="form-group">
                            <label>Car Mileage</label> <input type="text"
                                                           value="<c:out value='${car.mileage}' />" class="form-control"
                                                           name="mileage">
                        </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>