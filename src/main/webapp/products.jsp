<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Shops</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-light bg-faded">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">
        Shops
    </a>
</nav>

<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/shop">Shops</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/product">Products</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/transaction">Transaction</a>
    </li>
</ul>
<div class="container">
    <div>
        <div>Adding of product</div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <form class="product_form">
                        <td><input class="product name" type="text" name="name" placeholder="Name"> </td>
                        <td><input class="addButton"  type="button" value="Add"></td>
                    </form>
                </tr>
            </tbody>
        </table>
    </div>
<c:choose>
    <c:when test="${fn:length(products) gt 0}">
        <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="transaction" items="${products}">
                <tr>
                    <form class="product_form">
                        <td><input type="text" name="id" value="<c:out value="${transaction.id}"/>" disabled></td>
                        <td><input  type="text" name="name" value="<c:out value="${transaction.name}"/>"></td>
                        <td><input class="updateButton"  type="button" value="Update"></td>
                        <td><input class="deleteButton"  type="button" value="Delete"></td>
                    </form>
                </tr>
        </c:forEach>
        </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning" role="alert">
            <strong>No products.</strong>
        </div>
    </c:otherwise>
</c:choose>

    <script>

        $('.addButton').click(function () {
            var form = this.form;
            $.ajax({
                type: 'GET',
                url: window.location,
                data: {action: 'add',
                       name: form.name.value},
                success: function(result) {
                    alert('Added');
                    window.location.reload();
                },
                error: function (jqXHR, exception) {
                    alert('Error');
                }
            });
        })

        $('.updateButton').click(function () {
            var form = this.form;

            $.ajax({
                type: 'GET',
                url: window.location,
                data: {action: 'edit',
                    id : form.id.value,
                    name: form.name.value},
                success: function(result) {
                    alert('Updated')
                    window.location.reload();
                },
                error: function (jqXHR, exception) {
                    alert('Error');
                }
            });
        })

        $('.deleteButton').click(function () {
            var form = this.form;

            $.ajax({
                type: 'GET',
                url: window.location,
                data: {action: 'delete',
                    id : form.id.value,
                    name: form.name.value},
                success: function(result) {
                    alert('Deleted')
                    window.location.reload();
                },
                error: function (jqXHR, exception) {
                    alert('Error');
                }
            });
        })

    </script>
</div>
</body>
</html>