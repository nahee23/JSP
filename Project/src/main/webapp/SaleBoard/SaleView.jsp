<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양도게시판-상세보기</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<!-- 공통 링크 -->
	<br>
<main class="container">
    <header class="py-5">
        <h2> ${dto.title} </h2>
    </header>
    <div class="row g-5">
        <section class="col-md-3 col-lg-4 order-md-last">
            <aside>
                <p>${ dto.name }</p>
                <p>작성자 이메일 주소</p>
                <p>${ dto.postdate }</p>
                <p>조회수 : ${ dto.visitcount }</p>
                <p>첨부파일 : <c:if test="${ not empty dto.ofile }">
            ${ dto.ofile }
            <a href="../mvcboard/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">
                [다운로드]
            </a>
            </c:if></p>
            <p>다운로드수 : ${ dto.downcount }</p>
            </aside>
        </section>

        <article id="article-content" class="col-md-9 col-lg-8">
        <table class="table table-bordered w-100">
        	<tr>
        		<th colspan="2" class="text-left">공연명 : </th>
        		
        	</tr>
        	<tr>
        		<td class="text-left">공연날짜 : </td>
        		<td class="text-left">자리등급 : </td>
        	</tr>
        	<tr>
        		<td class="text-left">가격 : </td>
        		<td class="text-left">자리수 : </td>
        	</tr>
        </table>
            <pre>${ dto.content }</pre>
            <c:if test="${ not empty dto.ofile and isImage eq true }">
        		<br><img src="../Uploads/${ dto.sfile }" style="max-width:100%;"/>
        	</c:if>
        </article>


    </div>
    </div>
<br><br>
    <div class="row 9-5">
        <sectoin>
            <form class="row g-3">

                <div class="col-md-9 col-lg-8">
                    <label for="articleComment" hidden>댓글</label>
                    <textarea class="form-control" id="articleComment" placeholder="댓글 쓰기.." rows="3"
                              required></textarea>
                </div>
                <div class="col-md-3 col-lg-4">
                    <label for="comment-submit" hidden>댓글 쓰기</label>
                    <button class="btn btn-primary" id="comment-submit" type="submit">등록</button>
                </div>

            </form>
            <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3">
                <li>
                    <form>
                        <input hidden class="article-id">
                        <div class="row">
                            <div class="row col-md-10 col-lg-9">
                                <strong>Jyc</strong>
                                <small>
                                    <time>2022-01-01</time>
                                </small>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                                    Lorem ipsum dolor sit amet
                                </p>
                            </div>
                        </div>
                    </form>
                </li>
               </ul>

    <!-- 하단 메뉴(버튼) -->
    <div class="row">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-danger me-md-2" type="button" onclick="location.href='../mvcboard/pass.do?mode=edit&idx=${ param.idx }';">
                구매하기
            </button>
          </div>
         </div>
         <br>
    <div class="row">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-primary me-md-2" type="button" onclick="location.href='../mvcboard/pass.do?mode=edit&idx=${ param.idx }';">
                수정하기
            </button>
            <button class="btn btn-primary me-md-2" type="button" onclick="location.href='../mvcboard/pass.do?mode=delete&idx=${ param.idx }';">
                삭제하기
            </button>
            <button class="btn btn-primary me-md-2" type="button" onclick="location.href='../mvcboard/list.do';">
                목록 바로가기
            </button>
        </div>
       </div>
      </sectoin>
      </div>
      </main>
      <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>