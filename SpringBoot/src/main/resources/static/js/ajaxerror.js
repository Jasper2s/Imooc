$.ajax({
	//注意路径写法，不可以直接写/err/getAjaxerror，否则会找不到该路径
	url:"/SpringBoot/err/getAjaxerror",
	type:"POST",
	async:false,
	success:function(data){
		if (data.status==200&&data.msg=="OK") {
			alert("SUCCESS");
		} else {
			alert("发生异常："+data.msg);
		}
	},
	error:function(response,ajaxOptions,thrownError){
		alert("error");
	}
});