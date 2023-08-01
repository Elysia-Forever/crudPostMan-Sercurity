<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

</head>
<body>
<h1> Quản lý khách hàng</h1> <br> <br>
<form method="post" action="/khach-hang/search">
Search : <input type="text" name="nhap">
<select name="hang">
    <c:forEach items="${dsHang}" var="abc">
        <option value="${abc.maHang}" label="">${abc.tenHang}</option>
    </c:forEach>
</select>
    <button type="submit">Search</button>
</form>



<form:form method="post" action="/khach-hang/add" modelAttribute="khachHang" onsubmit="return confirm('Xác nhận thêm')">
  <div class="container">
      <div class="row">
          <div class="col-8">

          <br> <br>    Mã khách hàng : <form:input path="maKhachHang" class="form-control" readonly="true" />

              <br> <br>  Mã hạng : <form:select path="hangKH" cssStyle="width:100px ">
              <form:options items="${dsHang}" itemLabel="maHang" itemValue="maHang"/>
          </form:select>

       <br> <br>       Tên khách hàng :  <form:input path="tenKH" class="form-control"/>

              <br> <br>       Số điện thoại :  <form:input path="sdt" class="form-control"/>

              <br> <br> Gioi tính : <form:radiobutton path="gioiTinh" label="Nam" value="true" checked="true"/>
                                    <form:radiobutton path="gioiTinh" value="false" label="Nữ"/>

           <br> <br>   <button type="submit" class="btn btn-primary">ADD</button>
          </div>
      </div>
  </div>

</form:form>


<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Tên khách hàng</th>
        <th scope="col">Số điện thoại</th>
        <th scope="col">Gioiws tính</th>
        <th scope="col">Tên Hạng</th>
        <th scope="col">Action</th>

    </tr>
    </thead>
    <tbody>
<c:forEach items="${dsKH.content}" var="kh" varStatus="loop">
    <tr>
        <th scope="row">${kh.maKhachHang}</th>
        <td>${kh.tenKH}</td>
        <td>${kh.sdt}</td>
        <td>${kh.gioiTinh == true ? 'Nam' : 'Nữ'}</td>
        <td>${kh.hangKH.tenHang}</td>
        <td>
            <a href="/khach-hang/delete?maKH=${kh.maKhachHang}" onclick="return confirm('Xác nhận xóa')"><button type="button" class="btn btn-danger">Remove</button></a>
        </td>
    </tr>
</c:forEach>
    </tbody>
</table>

<c:forEach begin="0" end="${dsKH.totalPages-1}" varStatus="vst">
    <a href="/khach-hang/hien-thi?page=${vst.index}"><button type="button" class="btn btn-outline-info">${vst.index+1}</button></a>
</c:forEach>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

</body>
</html>