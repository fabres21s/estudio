jQuery(document).ready(function() {

});

function initActa(){
	console.log("init acta");
	tags = JSON.parse(jQuery("[id*='usuarios-sugeridos']").val());
	jQuery("[id*='inputresponsable']").autocomplete({
		 source: function(request, response) {
	        var results = jQuery.ui.autocomplete.filter(tags, request.term);
	        console.log("bbbbb");
	        response(results.slice(0, 10));
		 },
		 select: function(event, ui) {
			 jQuery("[id*='responsableacta']" ).val(ui.item.value);
			// jQuery("[id*='responsableactaimg']").attr('src', ui.item.image);
			 //jQuery("[id*='responsableactaimg']").attr('alt', ui.item.label);
			 jQuery("[id*='responsableactaimg']").attr('title', ui.item.label);
			 console.log("aaaaaa");
			 jQuery(this).val(''); 
			 return false;
		 }
	});


	//el auto complete de los participantes
	autocomplete("taskresponsiblename", "taskresponsibleid", "buttonaddresponsible");
	
	//participantes, creacion de actas
	autocomplete("participanttext", "participantid",  "buttonaddparticipant");
	
	//el autocomplete de los responsables en el popup de tareas
	initDialogTask();
	
	//popup de crear usuarios participantes
	initPopup('createparticipant', 'dialogParticipant', 160, 160);
	
	//popup de los adjuntos de la revision
	initPopup('listattachmentsrevision', 'popupar', 300, 300);
	
	//popup de tareas
	initPopup('createtask', 'popuptask', 500, 500);
	
	//popup de los adjuntos de las tareas
	initPopup('listattachmenttask', 'popupat', 300, 300);
	initPopup('listattachmenttask2', 'popupat2', 300, 300);
	
	console.log('termino exitosamente')
}

function initDialogTask(){
	jQuery("[id*='createtask']").button().on("click", function() {
		jQuery("[id*='enddate']").datepicker({ minDate: "0Y", maxDate: '+1Y', dateFormat: 'dd/mm/yy', showOtherMonths: true, selectOtherMonths: true, changeMonth: true, changeYear: true }, jQuery.datepicker.regional["es"]);
		jQuery("[id*='enddate']").attr('readonly','readonly');

	});

}

function initPopup(idBtn, idPopup, height, max_height) {
	
	console.log('inicializando '+idPopup);
	jQuery("[id*='"+idBtn+"']").button().on("click", function() {
		Liferay.component(idPopup).toggle();
		jQuery(".modal-body").attr('style', 'height: '+height+'px; max-height: '+max_height+'px;');
	});
}





function handleAjaxInitActa(data) {
	var status = data.status;
	switch(status) {
	case "success":
		initActa();
		break;
	}
}

function handleAjaxGuardarTarea(data) {
	var status = data.status;
	switch(status) {
	case "success":
		initActa();
		break;
	}
}

function handleAjaxSaveParticipant(data){
	console.log("El data js :: "+data.status);
	var status = data.status;
	
	switch(status) {
	case "success":
		console.log("Entró al case success :: "+data.status);
		initActa();
		break;
	}
	
}

function autocomplete (idInputText, idInputHidden, idButton) {
	
	tags = JSON.parse(jQuery("[id*='usuarios-sugeridos']").val());
	jQuery("[id*='"+idInputText+"']" ).autocomplete({
		 source: function(request, response) {
	        var results = jQuery.ui.autocomplete.filter(tags, request.term);
	        response(results.slice(0, 10));
		 },
		 select: function(event, ui) {
			 jQuery("[id*='"+idInputHidden+"']").val(ui.item.value);
			 jQuery(this).val(''); 
			 jQuery("[id*='"+idButton+"']").click();
			 return false;
		 }
	});
	

	
	
}

function closePopup(){
	/*el error estaba en el envío del parámetro, 
	 * TODO averiguar la razón y cuál es la forma correcta 
	 * de enviar un parámetro
	 */
	console.log("cerrando la ventana jhbjhg ");
	//Liferay.component('dialogParticipant').hide();//de todos modos, hay un problema con esto
	initActa();
}

function download(id) {
	console.log('downloading request to servlet '+id);
	  /*  var request=new XMLHttpRequest();
	    var stringParameter = "Something String"
	    request.open("POST", '/portal/esse-unika-actas-portlet/DownloadServlet?id='+id , true);
	    request.send("CUALQUIER COSA");
	    */
	window.open('/portal/esse-unika-actas-portlet/DownloadServlet?id='+id, "_self");
}
