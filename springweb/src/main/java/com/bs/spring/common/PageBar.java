package com.bs.spring.common;

public class PageBar {
	public static String makePageBar(int cPage, int numPerPage, int totalData, String uri) {

		int totalPage = (int) (Math.ceil((double) totalData / numPerPage));

		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		String pageBar = "<ul class='pagination justify-content-center'>";
		if (pageNo == 1) {
			pageBar += "<li class='page-item disabled'>";
			pageBar += "<a class='page-link' href='#'>이전</a>";
			pageBar += "</li>";
		} else {
			pageBar += "<li class='page-item'>";
			pageBar += "<a class='page-link' href='" + uri + "?cPage=" + (pageNo - 1) + "&numPerPage=" + numPerPage
					+ "'>이전</a>";
			pageBar += "</li>";
		}
		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item' disabled>";
				pageBar += "<a class='page-link' href='#'>" + pageNo + "</a>";
				pageBar += "</li>";
			} else {
				pageBar += "<li class='page-item'>";
				pageBar += "<a class='page-link' href='" + uri + "?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>"
						+ pageNo + "</a>";
				pageBar += "</li>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item' disabled>";
			pageBar += "<a class='page-link' href='#'>다음</a>";
			pageBar += "</li>";
		} else {
			pageBar += "<li class='page-item'>";
			pageBar += "<a class='page-link' href='" + uri + "?cPage=" + pageNo + "&numPerPage=" + numPerPage
					+ "'>다음</a>";
			pageBar += "</li>";
		}

		pageBar += "</ul>";

		return pageBar.toString();
	}
}
