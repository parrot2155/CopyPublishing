<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>K-Digital Training</title>
<link rel="shortcut icon" type="image/x-icon" href="./src/logo.ico">
<link rel="stylesheet" href="./2s.css?after">
<script type="text/javascript" src="./MLP.js"></script>
</head>
<body>
	<header class="header">
		<div class="headFline">
			<div class="inner">
				<div class="logo">
					<a href="board?command=list"><img src="./logo_mlp.png"
						alt="MLP 로고"></a>
				</div>
				<div class="search">
					<input type="text" placeholder="관심있는 주제, 키워드로 검색해보세요. 🔍"
						class="searchBox">
				</div>
				<div class="top-icons">
					<img src="./src/addfeed.png" alt="피드작성" class="write-feed"> <img
						src="./src/notice.png" alt="알림" class="icon"> <img
						src="./src/id.png" alt="프로필" class="icon profile">
				</div>
			</div>
		</div>
		<div class="headSline">
			<div class="inner">
				<nav class="top-menu">
					<ul>
						<li><a href="">카테고리</a></li>
						<li><a href="./llist.jsp">피드</a></li>
						<li><a href="./discoverWidget.html">지식콘텐츠</a></li>
						<li><a href="./campusConnect.html">캠퍼스</a></li>
						<li><a href="#">나의학습</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<div class="container">
		<div id="modalOverlay" class="modal-overlay" style="display: none;">
			<div id="feedForm" class="feed-form-modal">
				<form action="board" method="post">
					<div class="feed-header">
						<input type="hidden" name="command" value="insert"> <input
							type="text" id="feedTitle" placeholder="제목을 입력해주세요."
							class="feed-input-title" name="title"> <input type="text"
							id="feedKeywords" placeholder="키워드를 입력해주세요. (엔터로 여러 키워드 등록 가능)"
							class="feed-input-keywords" name="keyword">
						<p class="copyright-notice"></p>
					</div>
					<div class="feed-editor">
						<div class="editor-toolbar">
							<button class="toolbar-icon">
								<img src="./src/image-icon.png" alt="이미지">
							</button>
							<button class="toolbar-icon">
								<img src="./src/link-icon.png" alt="링크">
							</button>
							<button class="toolbar-icon">
								<img src="./src/video-icon.png" alt="비디오">
							</button>
							<button class="toolbar-icon">
								<img src="./src/text-icon.png" alt="텍스트">
							</button>
							<button class="toolbar-icon">
								<img src="./src/list-icon.png" alt="리스트">
							</button>
							<button class="toolbar-icon">
								<img src="./src/code-icon.png" alt="코드">
							</button>
							<button class="toolbar-icon">
								<img src="./src/more-icon.png" alt="더보기">
							</button>
							<span class="info-icon">i</span>
						</div>
						<textarea id="feedContent"
							placeholder="타인의 저작물을 무단 인용하는 경우 저작권 침해에 해당할 수 있으니, 저작권 준수를 부탁드립니다."
							class="feed-content-textarea" name="content"></textarea>
					</div>
					<div class="feed-settings">
						<label class="feed-schedule-label"> <input type="checkbox"
							id="scheduleFeed"> 피드 예약 설정 <span class="info-icon">i</span>
						</label>
					</div>
					<div class="feed-footer">
						<select id="feedVisibility" class="feed-visibility-select">
							<option value="public">공개 범위를 선택해주세요</option>
							<option value="private">비공개</option>
							<option value="friends">친구 공개</option>
						</select> <label class="feed-share-label"> <input type="checkbox"
							id="allowAllShare"> 전체 공유 허용
						</label>
						<button id="cancelFeed" class="btn-cancel">취소</button>
						<input type="submit" id="submitFeed" class="btn-submit" value="등록">
					</div>
				</form>
			</div>
		</div>
		<div class="left">
			<div class="profile-box">
				<img src="#" alt="프로필 이미지" class="profile-img">
				<div class="profile-info">
					<strong>${sessionScope.loginUser.name}</strong>
					<p>
						[현대이지웰] Java 풀스택 개발자 아카데미<br>5회차 · K-디지털
					</p>
					<button class="profile-edit">프로필 수정</button>
				</div>
			</div>
			<div class="profile-box">
				<h2>전체피드</h2>
				<ul>
					<li>공지사항</li>
					<li>과제</li>
					<li>자료실</li>
					<li>Q&A</li>
				</ul>
			</div>
			<div class="profile-box">
				<h2>친구 추천</h2>
				<ul>
					<li>공지사항</li>
					<li>과제</li>
					<li>자료실</li>
					<li>Q&A</li>
				</ul>
			</div>
		</div>
		<div class="right">
			<h2>전체 피드</h2>
			<div class="profile-box">
				<input type="text" placeholder="검색어를 입력해주세요" class="feedsearchBox"
					value="${search}"> <select id="sortOrder"
					class="sort-order-select">
					<option value="desc" ${sort == 'desc' ? 'selected' : ''}>최신순</option>
					<option value="asc" ${sort == 'asc' ? 'selected' : ''}>오래된순</option>
				</select>
			</div>
			<c:forEach var="feed" items="${list}">
				<div class="feed" data-regdate="${feed.regdate}">
					<h3>[${feed.title}]</h3>
					<p>
						<strong>${feed.name}</strong> | ${feed.regdate}
					</p>
					<p>${feed.content}</p>

					<!-- 댓글 및 대댓글 출력 -->
					<div class="comment-section">
						<c:forEach var="comment" items="${commentMap[feed.no]}">
							<div class="comment">
								<strong>${comment.commenter}</strong>: ${comment.content} <span
									class="comment-date">(${comment.regdate})</span>
								<!-- 대댓글(답글) 버튼 -->
								<button type="button" class="reply-btn"
									onclick="toggleReplyForm('${comment.commentId}')">답글</button>

								<!-- 대댓글 입력 폼 (숨김) -->
								<form action="board" method="post" class="reply-form"
									id="replyForm${comment.commentId}"
									style="display: none; margin-left: 32px;">
									<input type="hidden" name="command"
										value="comment_reply_insert"> <input type="hidden"
										name="feedId" value="${feed.no}"> <input type="hidden"
										name="parentId" value="${comment.commentId}"> <input
										type="text" name="content" class="comment-input"
										placeholder="대댓글을 입력하세요">
									<button type="submit" class="comment-btn">답글 등록</button>
								</form>

								<!-- 대댓글 목록 출력 -->
								<c:forEach var="reply" items="${replyMap[comment.commentId]}">
									<div class="comment reply" style="margin-left: 32px;">
										<strong>${reply.commenter}</strong>: ${reply.content} <span
											class="comment-date">(${reply.regdate})</span>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>

					<!-- 댓글 등록 폼 -->
					<form action="board" method="post" class="comment-form">
						<input type="hidden" name="command" value="comment_insert">
						<input type="hidden" name="feedId" value="${feed.no}"> <input
							type="text" name="content" class="comment-input"
							placeholder="댓글을 입력하세요">
						<button type="submit" class="comment-btn">댓글 등록</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="floating-buttons">
		<button class="bookmark-btn" title="북마크">
			<img src="./src/bookmark.png" alt="북마크">
		</button>
		<button class="top-btn" title="상단 이동" onclick="scrollToTop();">
			↑<br>TOP
		</button>
	</div>
	<script>
		window.toggleReplyForm = function(commentId) {
			//alert("폼 토글: replyForm" + commentId);
			var form = document.getElementById('replyForm' + commentId);
			if (form) {
				form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block'
						: 'none';
			}
		}
	</script>
</body>
</html>
