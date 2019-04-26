$(document).ready(function(){
	
	$("#btn-login").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			url: "/login",
			type: "GET",
			data:{
				username: username,
				password: password,
			},
			success : function(result) {
				if (result == "success") {
					window.location.replace("/admin/dotthi");
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
	
	/*$("#btn-register").click(function(){
		var formdata = $("#form-register").serializeArray();
		json = {};
		$.each(formdata,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/register",
			type: "POST",
			data:{
				dataJson: JSON.stringify(json),
			},
			success : function(result) {
				if (result == "success") {
					location.reload(true);
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});*/
	
	$(".input-date-dob").datepicker({format: 'yyyy-mm-dd'});
	
	$("#dateExam").change(function(){
		$("#table-member tbody tr td").find("#check-council").prop('disabled', false);
	});
	
	$("#memberModal, #demandModal").on("hidden.bs.modal", function (e) {
		  $(this)
		    .find("input,textarea")
		       .val('')
		       .end()
		    .find("input[type=checkbox], input[type=radio]")
		       .prop("checked", "")
		       .end();
	});
	
	$(".examination-pagination").click(function(){
		var pagination = $(this).text();
		$.ajax({
			url: "/admin/dotthi",
			type: "GET",
			data: {
				pagination: pagination,
			},
			success : function() {
				location.reload(true)
			}
		})
	})
	
	$(".btn-register").click(function(){
		var $email = $('form input[name="email');
		var reg = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
		if ($(email).val()!= '' && !reg.test($(email).val()))
		{
		    alert('Email không đúng định dạng!!!');
		    return false;
		}
	});
	
	$(".btn-create-examination").click(function(){
		if($("#dateExam").val() != '' && $("#code").val() != ''){
			var arrMember = [];
			$.each($("input[name='council']:checked"), function(){
				arrMember.push($(this).val());
			});
		
			var formData = {
				dateExam : $("#dateExam").val(),
				code : $("#code").val(),
				content : $(".content").children("option:selected").val(),	
				members : arrMember
			};
			$.ajax({
				url: "/create-examination",
				type: "POST",
				data: {
					dataJson: JSON.stringify(formData),
				},
				success : function(result) {
					if (result == "success") {
						location.reload(true);
						console.log(result);
					}
				},
				error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			})
		}else{
			alert("Nhập đầy đủ thông tin !!!");
			return false;
		}
	});
	
	$(".btn-update-examination").click(function(){
		if($("#dateExam").val() != '' && $("#code").val() != ''){
			var arrMember = [];
			$.each($("input[name='council']:checked"), function(){
				arrMember.push($(this).val());
			});
		
			var formData = {
				idExamination : $("#id_examination").val(),
				dateExam : $("#dateExam").val(),
				code : $("#code").val(),
				members : arrMember
			};
			$.ajax({
				url: "/update-examination",
				type: "POST",
				data: {
					dataJson: JSON.stringify(formData),
				},
				success : function(result) {
					if (result == "success") {
						location.reload(true);
						console.log(result);
					}
				},
				error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			})
		}else{
			alert("Nhập đầy đủ thông tin !!!");
			return false;
		}
	});
	

	$(".btn-update-member").click(function(){
		var name = $(this).closest("tr").find(".name-member").text();
		var workunit = $(this).closest("tr").find(".workunit-member").text();
		var note = $(this).closest("tr").find(".note-member").text();
		//var rank = $(this).closest("tr").find(".rank-member").text();
		var id = $(this).closest("tr").find(".id-member").text(); 
		$("#form-member").find("#modal-name-member").val(name);
		$("#form-member").find("#modal-workunit-member").val(workunit);
		/*$(this).find(".rank-member").each(function(){
			 $(".rank-member option:selected").remove();
		});
		$("#form-member").find("#modal-rank-member option").filter(function() {
			return $(this).text() == rank;
		}).attr('selected', true);*/
		$("#form-member").find("#modal-note-member").val(note);
		$("#form-member").find("#modal-id-member").val(id);
	});
 
	$(".btn-save-member").click(function(){
		if($("#modal-name-member").val() == '' || $("#modal-workunit-member").val() == ''){
			alert("Nhập đầy đủ thông tin !!!");
			return false;
		}
		var formData = $("#form-member").serializeArray();
		json = {};
		$.each(formData, function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/create-member",
			type: "POST",
			data: {
				dataJson: JSON.stringify(json),
			},
			success : function(result) {
				if (result == "success") {
					location.reload(true);
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
	
	$(".btn-delete-member").click(function(){
		var id = $(this).closest("tr").find(".id-member").text();
		$.ajax({
			url: "/delete-member",
			type: "POST",
			data:{
				id: id,
			},
			success: function(){
				location.reload(true);
			}
		})
	});
	
	$(".btn-update-collectMoney").click(function(){

		var arrCandidateConfirm = "";
		$.each($("input[name='confirm']:checked"), function(){
			arrCandidateConfirm = arrCandidateConfirm + ($(this).val()) + " ";
		});
		$.ajax({
			url: "/confirm",
			type: "POST",
			data: {
				arrCandidateConfirm: arrCandidateConfirm,
			},
			success : function(result) {
				if (result == "success") {
					location.reload(true);
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
	
	$(".btn-update-demand").click(function(){
		var object = $(this).closest("tr").find(".object").text();
		var level = $(this).closest("tr").find(".level").text();
		var formal = $(this).closest("tr").find(".formal").text();
		var reviewCost = $(this).closest("tr").find(".reviewCost").text();
		var examCost = $(this).closest("tr").find(".examCost").text();
		var id = $(this).closest("tr").find(".id-demand").text(); 
		$("#form-demand").find("#modal-object").val(object);
		$("#form-demand").find("#modal-level").val(level);
		$("#form-demand").find("#modal-formal").val(formal);
		$("#form-demand").find("#modal-reviewCost").val(reviewCost);
		$("#form-demand").find("#modal-examCost").val(examCost);
		$("#form-demand").find("#modal-id-demand").val(id);
	});
	
	$(".btn-save-demand").click(function(){
		if($("#modal-object").val() == '' || $("#modal-level").val() == '' || $("#modal-formal").val() == '' || $("#modal-reviewCost").val() == '' || $("#modal-examCost").val() == ''){
			alert("Nhập đầy đủ thông tin !!!");
			return false;
		}
		var formcontent = $("#form-demand").serializeArray();
		json = {};
		$.each(formcontent, function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/create-demand",
			type: "POST",
			data: {
				dataJson: JSON.stringify(json),
			},
			success : function(result) {
				if (result == "success") {
					location.reload(true);
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
	
	$(".btn-delete-demand").click(function(){
		var id = $(this).closest("tr").find(".id-demand").text();
		$.ajax({
			url: "/delete-demand",
			type: "POST",
			data:{
				id: id,
			},
			success: function(){
				location.reload(true);
			}
		})
	});

	$(".btn-update-result").click(function(){
		var arrIdResult = [];
		var arrSightScores = [];
		var arrDrillScores = [];
		$(".table-result tbody tr td").find(".sightScores").each(function(){
			var id = $(this).closest("tr").find(".id").text();
			var drillScores = $(this).closest("tr").find(".drillScores").val();
			arrIdResult.push(id);
			arrSightScores.push($(this).val());
			arrDrillScores.push(drillScores);
		});
		var formData = {
			arrIdResult : arrIdResult,
			arrSightScores : arrSightScores,
			arrDrillScores : arrDrillScores,	
		};
		$.ajax({
			url: "/update-result",
			type: "POST",
			data: {
				dataJson: JSON.stringify(formData),
			},
			success : function(result) {
				if (result == "success") {
					location.reload(true);
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
	
	$(".btn-update-certificate").click(function(){
		var arrIdCertificate = [];
		var arrDiplomaNumber = [];
		var arrNotebookNumber = [];
		$(".table-certificate tbody tr td").find(".diplomaNumber").each(function(){
			var id = $(this).closest("tr").find(".id").text();
			var notebookNumber = $(this).closest("tr").find(".notebookNumber").val();
			arrIdCertificate.push(id);
			arrDiplomaNumber.push($(this).val());
			arrNotebookNumber.push(notebookNumber);
		});
		var formData = {
			arrIdCertificate : arrIdCertificate,
			arrDiplomaNumber : arrDiplomaNumber,
			arrNotebookNumber : arrNotebookNumber,	
		};
		$.ajax({
			url: "/update-certificate",
			type: "POST",
			data: {
				dataJson: JSON.stringify(formData),
			},
			success : function(result) {
				if (result == "success") {	
					location.reload(true);
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
	
    $(function() {
        $('ul.bodyleft li').on('click', function(){
              $(this).parent().find('li.categoryactive').removeClass('categoryactive');
              $(this).addClass('categoryactive');
        });
    });
})
