function checkInput() {
    if (!(document.form1.text1.value == "") && !(document.form1.text2.value == "")) {
        $('button').addClass('safe');
    }
}

function rmClass() {
    $('button').removeClass('safe');
    $('button').removeClass('out');
}

function checkForm() {
    if(document.form1.text1.value == ""){
        $('button').addClass('out');
	    return false;
    }
    if(document.form1.text2.value == ""){
        $('button').addClass('out');
	    return false;
    }

	    return true;
}