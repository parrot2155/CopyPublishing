document.addEventListener('DOMContentLoaded', () => {
	// 요소 선택
	const writeFeedBtn = document.querySelector('.write-feed'); // 작성 버튼
	const modalOverlay = document.getElementById('modalOverlay'); // 모달 배경
	const feedForm = document.querySelector('#feedForm form'); // 폼 요소
	const feedContent = document.getElementById('feedContent'); // textarea
	const submitFeed = document.getElementById('submitFeed'); // 등록 버튼
	const topButton = document.querySelector('.top-btn'); // 상단 이동 버튼
	const searchBox = document.querySelector('.feedsearchBox'); // 검색창
	const sortOrderSelect = document.querySelector('.sort-order-select'); // 정렬 선택
	const feedContainer = document.querySelector('.right'); // 피드 영역

	// 피드 선택 (<c:forEach>로 생성된 피드)
	const getDynamicFeeds = () => Array.from(document.querySelectorAll('.feed')).filter(feed =>
		feed.querySelector('p strong') // <p><strong>${feed.name}</strong> | ${feed.regdate}</p>가 있는 피드
	);

	function debounce(func, wait) {
		let timeout;
		return function(...args) {
			clearTimeout(timeout);
			timeout = setTimeout(() => func.apply(this, args), wait);
		};
	}

	// 피드 정렬 함수 (${feed.regdate} 기준)
	function sortFeeds(order) {
		const dynamicFeeds = getDynamicFeeds();
		dynamicFeeds.sort((a, b) => {
			const dateA = new Date(a.dataset.regdate);
			const dateB = new Date(b.dataset.regdate);
			if (isNaN(dateA) || isNaN(dateB)) {
				console.warn('Invalid regdate format:', a.dataset.regdate, b.dataset.regdate);
				return 0; // 유효하지 않은 날짜는 순서 유지
			}
			return order === 'asc' ? dateA - dateB : dateB - dateA;
		});

		// 정렬된 동적 피드를 .profile-box 다음에 배치
		const profileBox = feedContainer.querySelector('.profile-box');
		dynamicFeeds.forEach(feed => feedContainer.insertBefore(feed, profileBox.nextSibling));
		console.log(`Sorted ${order === 'asc' ? 'ascending' : 'descending'} by regdate`);
	}

	// 피드 필터링 및 정렬
	function filterAndSortFeeds() {
		const searchTerm = searchBox.value.toLowerCase().trim();
		const sortOrder = sortOrderSelect.value;

		// 검색어로 피드 필터링
		const filteredFeeds = getDynamicFeeds().filter(feed => {
			const title = feed.querySelector('h3').textContent.toLowerCase();
			const content = feed.querySelectorAll('p')[1] ? feed.querySelectorAll('p')[1].textContent.toLowerCase() : '';
			return title.includes(searchTerm) || content.includes(searchTerm);
		});

		// 피드 표시/숨김 처리
		getDynamicFeeds().forEach(feed => {
			feed.style.display = filteredFeeds.includes(feed) ? 'block' : 'none';
		});

		//  ${feed.regdate} 기준 정렬
		sortFeeds(sortOrder);
	}

	// 피드 작성 모달 열기
	if (writeFeedBtn) {
		writeFeedBtn.addEventListener('click', () => {
			modalOverlay.style.display = 'flex';
			feedContent.focus();
		});
	}

	// 폼 제출 전 유효성 검사
	if (submitFeed) {
		submitFeed.addEventListener('click', (e) => {
			const content = feedContent.value.trim();
			if (content === '') {
				alert('내용을 입력해주세요.');
				e.preventDefault(); // 서버 제출 중단
				return;
			}
			feedForm.submit();
		});
	}

	// 모달 바깥 클릭 시 닫기
	if (modalOverlay) {
		modalOverlay.addEventListener('click', (e) => {
			if (e.target === modalOverlay) {
				modalOverlay.style.display = 'none';
			}
		});
	}

	// 상단 이동 버튼
	if (topButton) {
		topButton.addEventListener('click', () => {
			window.scrollTo({ top: 0, behavior: 'smooth' });
			if (feedContainer) {
				feedContainer.scrollTo({ top: 0, behavior: 'smooth' });
			}
		});
	}

	// 검색창 입력 이벤트
	if (searchBox) {
		searchBox.addEventListener('input', debounce(filterAndSortFeeds, 300));
	}

	// 정렬 선택 이벤트
	if (sortOrderSelect) {
		sortOrderSelect.addEventListener('change', filterAndSortFeeds);
	}

	// 초기 정렬 (${feed.regdate} 기준 최신순, 동적 피드만)
	sortFeeds('desc');

	// ★★★ 대댓글(답글) 폼 토글 함수 (JSP 스크립트 통합)
	window.toggleReplyForm = function(commentId) {
		var form = document.getElementById('replyForm' + commentId);
		if (form) {
			form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
		}
	};

	// 댓글/대댓글 input에서 엔터로 바로 등록 (★★ DOMContentLoaded 이후에 실행!)
	document.querySelectorAll(".comment-input").forEach(input => {
		input.addEventListener("keypress", function(e) {
			if (e.key === "Enter") {
				e.preventDefault();
				this.closest("form").submit();
			}
		});
	});
}); // <-- 반드시 닫혀 있어야 함!!