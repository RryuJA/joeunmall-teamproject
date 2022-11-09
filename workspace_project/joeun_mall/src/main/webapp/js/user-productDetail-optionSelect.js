$('#optionSelect').change(function() {
	var state=$('#optionSelect').val();

	if (  state == '2' ) {
        console.log("선택된 옵션: " + $('#optionSelect').val());
	}
});