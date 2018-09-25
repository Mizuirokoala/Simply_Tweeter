<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
</head>
<body>

	<%@ include file="/WEB-INF/views/jspf/navbar.jspf"%>

	<div class="container">
		<div class="form-row justify-content-center">
			<div class="form-group col-md-8 ">
				<!-- after logging in show message -->
				${message}

				<!-- add new tweet form -->
				<c:choose>
					<c:when test="${sessionScope.user != null}">
						<div class="container-box-child">
							<div id="div-tweet">
								<i class="fas fa-plus"></i> Add new tweet
							</div>
							<form:form action="tweet/add" id="addTweet" style="display: none" method="POST" modelAttribute="tweet">
								<div class="form-row">
									<div class="form-group col-md-12">
										<form:textarea class="form-control is-valid" path="tweetText" rows="3" id="tweetText" type="text"
											name="tweetText" required="true" />
										<form:errors path="tweetText" cssClass="valid-feedback" />
									</div>
								</div>
								<div class="row justify-content-center">
									<div class="col-sm-12">
										<input class="btn btn-secondary btn-sm btn-block" type="submit" value="Add" />
									</div>
								</div>
							</form:form>
						</div>
					</c:when>
				</c:choose>

				<!-- tweets list -->
				<c:forEach items="${tweets }" var="tweet">
					<!-- show each tweet in new div -->
					<div class="container-box-child">
						<div class="tweet-div">
							<div class="tweet-user">
								<a class="btn btn-info" href="<c:url value='/user/${tweet.user.id}'></c:url>">${tweet.user.fullName}</a>
							</div>
							<div class="tweet-date">${tweet.created}</div>
						</div>
						<div class="tweet-text">${tweet.tweetText}</div>
						<div class="tweet-div">
							<!-- buttons for comments: show comments and add comment -->
							<div class="tweet-comments-button" data-id="${tweet.id}" data-type="GET">
								<i class="btn btn-light far fa-comments">&nbsp; <!-- Comment counter (if can't find set value=0) --> <c:set
										var="counter" scope="session" value="${0}" /> <c:forEach items="${commentsQuantity }" var="c">
										<c:if test="${c.key == tweet.id}">
											<c:set var="counter" scope="session" value="${c.value}" />
										</c:if>
									</c:forEach> <c:out value="(${counter})" /></i>
							</div>
							<!-- buttons for tweet update, delete -->
							<c:if test="${user.id == tweet.user.id}">
								<div class="tweet-buttons">
									<a href="<c:url value='/tweet/update/${tweet.id}'></c:url>"><i class="btn btn-light far fa-edit"></i> </a><a
										href="<c:url value='/tweet/delete/${tweet.id}'></c:url>"><i class="btn btn-light far fa-trash-alt"></i></a>
								</div>
							</c:if>
							<div class="tweet-buttons tweet-add-comment">
								<i class="btn btn-light fas fa-comment">+</i>
							</div>
						</div>
						<!-- form: add comment -->
						<form class="add-comment-form" style="display: none">
							<div class="form-row">
								<div class="form-group col-md-12">
									<textarea class="form-control is-valid" name="commentText" rows="3" required></textarea>
									<form:errors path="commentText" cssClass="valid-feedback" />
								</div>
							</div>
							<div class="row justify-content-center">
								<div class="col-sm-12">
									<p class="tweet-submit-comment btn btn-secondary btn-sm btn-block" data-tweet-id="${tweet.id}"
										data-user-id="${user.id}" data-type="POST" />
									Add comment
									</p>
								</div>
							</div>
						</form>
						<!-- show comments -->
						<div class="comments-list" style="display: none">
							<ul class="comments-list"></ul>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>


