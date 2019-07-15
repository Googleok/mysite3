		
var setPage = function(listCount, currentPage, contextPath){

		var listCount = listCount;
		var pageCount = (parseInt( listCount/ 10) + 1);
		var currentPage = currentPage;
		var endPage = (parseInt(pageCount/5 + 1) * 5);
		var displayPage = parseInt((currentPage + 4 ) / 5 ) * 5; 
		
		console.log("listCount", listCount);
		console.log("pageCount", pageCount);
		console.log("currentPage", currentPage);
		console.log("endPage", endPage);
		console.log("displayPage", displayPage);
		
		var pager =$('#pager');
		if(currentPage <= 5){
			pager.prepend('<li>◀</li>');		
		}else{
			pager.prepend('<li><a href="'+contextPath+'/board/list?page='+(displayPage-5)+'">◀</a></li>');
		}
		for (var i = displayPage-4; i <= displayPage; i++) {
			if(i==currentPage){
				pager.append('<li class="selected">'+i+'</li>')	
				continue;
			}else if(i>pageCount){
				pager.append('<li>'+i+'</li>')
				continue;
			}
			pager.append('<li><a href="'+contextPath+'/board/list?page='+i+'">'+i+'</a></li>');
		}
		if(displayPage < endPage){
			pager.append('<li><a href="'+contextPath+'/board/list?page='+(displayPage+1)+'">▶</a></li>');
		}else{
			pager.append('<li>▶</li>')
		}
}