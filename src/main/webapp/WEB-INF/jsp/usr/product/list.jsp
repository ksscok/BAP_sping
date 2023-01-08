<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="상품 목록" />
<%@ include file="../common/head.jspf" %>

<c:set var="pageBaseUri" value="${pageBaseUri}?searchKeyword=${param.searchKeyword}"/>
<c:set var="pageBaseUri" value="${pageBaseUri}&low_price=${param.low_price}"/>
<c:set var="pageBaseUri" value="${pageBaseUri}&high_price=${param.high_price}"/>

<section class="mt-20">
	<div class="con-3 flex flex-row mx-auto px-3 bg-red-200">
		<div class="side-bar-1 bg-green-200 mr-6">
			<form action="../product/list">
				<input type="hidden" name="searchKeyword" value="${param.searchKeyword}" />
				<input type="hidden" name="order_by" value="${param.order_by}" />
				
				<div class="form-control">
				  <label class="cursor-pointer label">
				    <span class="label-text">Remember me</span>
				    <input name="motel" type="radio" checked="checked" class="checkbox checkbox-accent" />
				  </label>
				  <label class="cursor-pointer label">
				    <span class="label-text">Remember me</span>
				    <input type="radio" checked="checked" class="checkbox checkbox-accent" />
				  </label>
				  <label class="cursor-pointer label">
				    <span class="label-text">Remember me</span>
				    <input type="radio" checked="checked" class="checkbox checkbox-accent" />
				  </label>
				</div>
				
				<div class="pricing-box">
					<input type="text" name="low_price" class="input input-bordered"/>
					<input type="text" name="high_price" class="input input-bordered"/>
				</div>
				
				<button type="submit" class="btn btn-primary">적용</button>
			</form>
		</div>
		<div class="table table-fixed">
			<div class="btn-group">
				<button onclick="location.href='${pageBaseUri}&order_by=lowPrice'" class="btn ${order_by.equals('lowPrice') ? 'btn-active' : '' }">낮은 가격순</button>
				<button onclick="location.href='${pageBaseUri}&order_by=highPrice'" class="btn ${order_by.equals('highPrice') ? 'btn-active' : '' }" >높은 가격순</button>
			</div>
			<table class="w-full mt-5">
      	<colgroup>
          <col width="50" />
          <col width="150" />
          <col width="150" />
          <col width="50"/>
        </colgroup>
        <thead>
        	<tr>	
						<th>번호</th>
						<th>숙소 이름</th>
						<th>주소</th>
						<th>가격</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${products}">
						<tr>
							<td>${product.id}</td>
							<td>
								<a href="../product/detail?id=${product.id}">${product.name}</a>
							</td>
							<td>${product.address}</td>
							<td>${product.extra__minFee}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>






<%@ include file="../common/foot.jspf" %>